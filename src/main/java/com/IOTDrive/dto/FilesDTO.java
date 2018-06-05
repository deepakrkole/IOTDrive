package com.IOTDrive.dto;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

public class FilesDTO {
  public long getDropboxSize(DbxClient dbxClient, long sizeOfpendrive ) throws DbxException {
        long dbxSizeRemaining = 0;
        DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo(); //The dbxClient is mentioned in the Demo.java file
        // in GB :)
        long shared = dbxAccountInfo.quota.shared / 1024 / 1024 / 1024;
        long normal = dbxAccountInfo.quota.normal / 1024 / 1024 / 1024;
        long total  = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
        dbxSizeRemaining = total - normal - shared;

        if(dbxSizeRemaining < sizeOfpendrive ) return 0;
        return dbxSizeRemaining;
    }
}
