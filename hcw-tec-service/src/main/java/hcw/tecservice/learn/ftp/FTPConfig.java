package hcw.tecservice.learn.ftp;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/3/23 14:01
 * Description:
 * Others:
 */
public class FTPConfig {

    private static String mServerAddress 	= "192.168.23.139";
    private static int mServerPort 			= 21;
    private static String mUserName 		= "ftp";
    private static String mPassword 		= "123456";
    private static String mRootPath 		= "/";
    private static String mSystem 			= "WINDOWS";

    private static final String KEY_SERVER 		= "server";
    private static final String KEY_PORT 		= "port";
    private static final String KEY_USERNAME 	= "username";
    private static final String KEY_PASSWORD 	= "password";
    private static final String KEY_ROOTPATH 	= "rootpath";
    private static final String KEY_SYSTEM 		= "system";

    public static String getServerAddress() {
        return mServerAddress;
    }

    public static int getServerPort() {
        return mServerPort;
    }

    public static String getUserName() {
        return mUserName;
    }

    public static String getPassword() {
        return mPassword;
    }

    public static String getRootPath() {
        return mRootPath;
    }

    public static String getSystem() {
        return mSystem;
    }
}
