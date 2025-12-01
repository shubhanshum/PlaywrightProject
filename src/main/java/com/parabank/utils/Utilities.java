package com.parabank.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

	
	public static Object getConfigProperty(String key) {
		String data="";
		try {
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			Properties prop=new Properties();
			prop.load(file);
			data=prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
