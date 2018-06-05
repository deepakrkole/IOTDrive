package com.IOTDrive.scheduler;

import com.IOTDrive.dao.UploadFiles;
import com.IOTDrive.dto.FilesDTO;
import com.IOTDrive.service.GetFilesFromDROPBoxservice;
import com.IOTDrive.service.GetFilesFromPD;
import com.IOTDrive.service.UploadFileService;
import com.IOTDrive.util.CheckPenDrives;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Component
public class Scheduler {

	@Scheduled(fixedRate = 1000)
	public void synch() throws IOException {

		CheckPenDrives checkPd = new CheckPenDrives();
		if (checkPd.checkPD()) {
			String path = checkPd.getPath();
			UploadFiles upload = new UploadFiles();
			GetFilesFromPD getPDFiles = new GetFilesFromPD();
			GetFilesFromDROPBoxservice getDBFiles = new GetFilesFromDROPBoxservice();
			HashMap<File, String> getFilesFromPD = getPDFiles
					.getFilesFromPD(path);
			UploadFileService uploadFiles = new UploadFileService();
			HashMap<String, FilesDTO> filesFromDropBox = getDBFiles
					.getFilesFromDropBox();

			if (filesFromDropBox.size() > 0 && getFilesFromPD.size() > 0) {
				uploadFiles.uploadFileService(getFilesFromPD, filesFromDropBox);

				System.out.println("Pd Detected");
			} else {
				System.out.println("No files in PD to upload");
			}
		} else {

			System.out.println("No pd Found ");
		}
	}

}


