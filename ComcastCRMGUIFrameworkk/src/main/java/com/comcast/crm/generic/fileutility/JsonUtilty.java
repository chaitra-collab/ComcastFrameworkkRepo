package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.mysql.cj.xdevapi.JsonParser;

public class JsonUtilty {
	public String getDataFromJsonFile(String key) throws FileNotFoundException {
		FileReader fileR =new FileReader("./configAppData/appCommonData.json");
		JsonParser parser=new JSONParser();
		Object obj=parser.parse(fileR);
		JSONObject map=(JSONObject)obj;
		String data= (String) map.get(key);
		return data;
				
	}

}
