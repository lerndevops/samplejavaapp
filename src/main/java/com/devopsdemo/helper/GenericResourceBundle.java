package com.devopsdemo.helper;
//2nd push
// Push to main branch to trigger piepline in Jenkins
import java.util.Enumeration;
import java.util.ResourceBundle;

public class GenericResourceBundle {
	public static String getProperties(String source){
		ResourceBundle rb = ResourceBundle.getBundle("ResourceBundle");
		Enumeration <String> keys = rb.getKeys();
		String value="";
		while (keys.hasMoreElements()) {
			
			String key =  keys.nextElement();
			
			if(key.equalsIgnoreCase(source)){
				value = rb.getString(key);
			}
		}
		return value;
	}
	
}

