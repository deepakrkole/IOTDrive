package com.IOTDrive.util;

import java.io.File;

public class CheckPenDrives {
    public boolean checkPD() {

        boolean isUSB = false;
        File file = new File("/media/");
        String[] directories = file.list();
        if (directories.length > 0) {
            isUSB = true;
        }
        return isUSB;
    }

    public String getPath() {
        File file = new File("/media/");
        String[] directories = file.list();

        String path = "/media/" + directories[0];
        return path;

    }

}