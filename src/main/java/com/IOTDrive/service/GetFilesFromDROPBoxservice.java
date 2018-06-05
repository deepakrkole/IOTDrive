package com.IOTDrive.service;
import com.IOTDrive.dto.FilesDTO;
import com.IOTDrive.util.GetDropBoxConn;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class GetFilesFromDROPBoxservice {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	HashMap<String,FilesDTO> filesInDropBox=new HashMap<>();
    DbxClient client = null;
	public HashMap<String,FilesDTO>getFilesFromDropBox(){
		HashMap<String,FilesDTO>listOfFiles=new HashMap<String,FilesDTO>();
		try{
			GetDropBoxConn getDropBoxConnection = GetDropBoxConn
					.getInstance();
			
			if(GetDropBoxConn.client!=null && getDropBoxConnection!=null ){
			client = getDropBoxConnection.client;
			DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
			setAttributes(listing);
			}
		}catch (Exception e) {

		}
		
		return filesInDropBox;
		
	}

	public void setAttributes(DbxEntry.WithChildren listing){
		for (DbxEntry child : listing.children)
		{
			if(child.isFolder()){
				printSubfolders("/"+child.name, client);
			}
			if(child.isFile()){
				FilesDTO fileDTO=new FilesDTO();
				fileDTO.setModifiedTime(dateFormat.format(child.asFile().lastModified));
				fileDTO.setName(child.name);
				fileDTO.setPath(child.path);
				filesInDropBox.put(child.path, fileDTO);
			}
		}
	}
	
	
	public  void printSubfolders(String path,DbxClient client){
		Date date = new Date();
		DateFormat dateFormat1 = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, Locale.US);
		try {
			DbxEntry.WithChildren listing = client.getMetadataWithChildren(path);
			setAttributes(listing);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

