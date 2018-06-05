package com.IOTDrive.util;

import com.dropbox.core.DbxAccountInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;

import java.util.Date;

import static com.IOTDrive.util.GetDropBoxConn.getDropBoxConnection;

public class CheckStorage {

    public long getDropboxSize() throws DbxException {
        long dbxSizeRemaining = 0;
        long sizeOfpendrive = 32; // value to be fetched from the code by Mangesh.
        DbxClient dbxClient = getDropBoxConnection.client;
        DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo(); //The dbxClient is mentioned in the Demo.java file
        // in GB :)
        long shared = dbxAccountInfo.quota.shared / 1024 / 1024 / 1024;
        long normal = dbxAccountInfo.quota.normal / 1024 / 1024 / 1024;
        long total  = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
        dbxSizeRemaining = total - normal - shared;
        if(dbxSizeRemaining < sizeOfpendrive ) return 0;
        return dbxSizeRemaining;
    }

    public Date getTimeStampInfo(){
        return new Date();
    }
}
