package com.rcji.familystyle.locu;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.rcji.familystyle.JSON;

public class Locu {
	public static ArrayList<String> query(double lat, double lng) {
		String query = lat + "" + lng;
		String line = "";
		ArrayList<String> arr = new ArrayList<String>();
		try {
			URLEncoder.encode(query,"UTF-8");
	
	
			URL url = new URL("http://api.locu.com/v1_0/venue/search?category=restaurant&location"+query+"&radius=1600&api_key=a68c9df0160f8bdc3562f13bce397b1ef070730c");
			URLConnection conn = url.openConnection();
	
			//BufferedReader in = new BufferedReader(
			//new InputStreamReader(conn.getInputStream()));
			//line = in.readLine();
			JSONObject json = JSON.getJSONObject(conn.getInputStream());
			JSONArray objects = (JSONArray) json.get("objects");
			//String restaurantNames = "";
			
			//while ()
			for (int i = 0; i<objects.length(); i ++){
				arr.add(objects.getJSONObject(i).getString("name"));
			}
			//in.close();
		}
		catch (Exception e) {
			
		}
		return arr;
	}
}
