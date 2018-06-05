package com.IOTDrive.util;

import com.dropbox.core.*;
import com.mongodb.*;

import java.io.IOException;
import java.util.Locale;

public class GetDropBoxConn {
	static String APP_KEY = "";
	static String APP_SECRET = "";
	static String ACESS_TOKEN = "";
	public static DbxClient client = null;
	public static boolean isDelete=false;
	public static GetDropBoxConn getDropBoxConnection;

	private GetDropBoxConn() {

	}

	public static GetDropBoxConn getInstance() throws IOException {

		getDropBoxConnection = new GetDropBoxConn();
		if (client == null) {
			GetCredentials getCredentails = GetCredentials.getInstance();

			if (getCredentails.APP_KEY == null) {
				getCredentails.setCredentials();
			}
			APP_KEY = GetCredentials.APP_KEY;
			APP_SECRET = GetCredentials.APP_SECRET;


			ACESS_TOKEN = getAccesToken();

			if(!ACESS_TOKEN.isEmpty()){
				DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

				System.out.println(Locale.getDefault().toString());
				DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0",
						Locale.getDefault().toString());
				DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
				String authorizeUrl = webAuth.start();
				client = new DbxClient(config, ACESS_TOKEN);
				try {
					System.out.println("Linked account: " + client.getAccountInfo().displayName);
					DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
					System.out.println("Files in the root path:");
					for (DbxEntry child : listing.children) {
						System.out.println("	" + child.name + ": " + child.toString());
					}
				} catch (DbxException e) {
					// TODO Auto-generated catch block
					client=null;
					return null;
				}
			}
		}

		return getDropBoxConnection;
	}

	private static String getAccesToken(){
		String accessToken="";
		try{
			DB db = GetMongoConnection.getConnection();
			DBCollection collection = db.getCollection("Rasberry");
			BasicDBObject doc = new BasicDBObject();
			DBCursor cursor = collection.find();

			while(cursor.hasNext()){
				DBObject dobj = cursor.next();

				accessToken=(String)dobj.get("inputAccessToken");
				isDelete=(boolean)dobj.get("deleteCheck");
			}
		}catch(Exception e){

		}
		return accessToken;
	}

}

