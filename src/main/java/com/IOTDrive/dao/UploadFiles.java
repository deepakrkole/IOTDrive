package com.IOTDrive.dao;

import com.IOTDrive.util.GetDropBoxConn;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxWriteMode;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UploadFiles {

	public void uploadFilesInDropBox(HashMap<File, String> filesForDropBox) {

		try {
			GetDropBoxConn getDropBoxConnection = GetDropBoxConn
					.getInstance();
			DbxClient client = getDropBoxConnection.client;
			if(client!=null){
			Iterator filesToUpload = filesForDropBox.entrySet().iterator();
			while (filesToUpload.hasNext()) {
				Map.Entry pair = (Map.Entry) filesToUpload.next();
				String path = (String) pair.getValue();
				File file = (File) pair.getKey();
				FileInputStream inputStream = new FileInputStream(file);
				DbxEntry.File uploadedFile = client.uploadFile(path,
						DbxWriteMode.force(), file.length(), inputStream);
				
				System.out.println("Uploaded: " + uploadedFile.toString());
				if(GetDropBoxConn.isDelete){
					
					if(file.delete()){
						System.out.println("file deleted");
					}else{
						System.out.println("Not able to delete");
					}
				}
		
			}
			}
		} catch (Exception e) {

		}

	}

}

