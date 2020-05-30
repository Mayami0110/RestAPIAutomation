package com.qa.API.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	
	public static String strAbsolutepath = new File("").getAbsolutePath() +"\\";
	public static Properties prop;
	String filepath;
	
	public TestBase()
	{
		prop = new Properties();
		
		String filepath = strAbsolutepath + "src\\main\\java\\com\\qa\\API\\config\\config.properties";
		
		try {
			FileInputStream fis = new FileInputStream(filepath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
