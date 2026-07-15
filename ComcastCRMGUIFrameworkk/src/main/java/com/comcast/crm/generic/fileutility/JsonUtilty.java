package com.comcast.crm.generic.fileutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.xdevapi.JsonParser;

public class JsonUtilty {
	public String getDataFromJsonFile(String key) throws IOException, ParseException  {
		FileReader fileR=new FileReader("./configAppData/appCommonData.json");
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fileR);
		//convert java object to json object using downcasting
		JSONObject map= (JSONObject)obj;
		//get the value from json file using key
	String data=(String) map.get(key);
	return data;
	}

}
