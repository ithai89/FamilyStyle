package com.rcji.familystyle.locu;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Looper;
import android.widget.TextView;

import com.rcji.familystyle.JSON;

public class Locu {
	/*
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
	} */
	public static String API_KEY = "fed017c95941aecf151b324e8b61d286a6cda99d";
	public static void query(double lat, double lng, final TextView tv) {
		String query = lat+","+lng;
	    String response = "";
		final String URL = "http://api.locu.com/v1_0/venue/search/?category=restaurant&location="+query+"&radius=1600&api_key="+API_KEY;
		//final String URL = "http://api.locu.com/v1_0/venue/search/?api_key=" + API_KEY;
		

		HttpClient client = new DefaultHttpClient();
		Thread t = new Thread() {
			public void run() {
				Looper.prepare();
				HttpClient client = new DefaultHttpClient();
				//HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
				HttpResponse resp;
				ArrayList<String> arr = new ArrayList<String>();
				//JSONObject jsonpost = new JSONObject();
				//JSONArray jarray = new JSONArray();
				try {
					//Log.i("About to create a new http post");
					//HttpGet get = new HttpGet("http://api.locu.com/v1_0/venue/search/?api_key=fed017c95941aecf151b324e8b61d286a6cda99d");
					HttpGet get = new HttpGet(URL);
					get.addHeader("accept", "application/json");
					
					//StringEntity se = new StringEntity(jsonpost.toString());
					//se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
					//post.setEntity(se);
					//Log.i("About to execute httppost");
					resp = client.execute(get);
					//Log.i("HTTPResponse: " + resp);
					JSONObject responseObject = JSON.getJSONObject(resp.getEntity().getContent());
					//Log.i("Net Call Success");
					JSONArray objects = (JSONArray) responseObject.get("objects");
					//String restaurantNames = "";
					
					//while ()
					for (int i = 0; i<objects.length(); i ++){
						arr.add(objects.getJSONObject(i).getString("name"));
					}
					
					final ArrayList<String> arr1 = arr;
					tv.post(new Runnable() {
						
						public void run() {
							String tv_str = "";
					        for (String str: arr1) {
					        	tv_str = tv_str + str + "\n";
					        }
							tv.setText(tv_str);
							
						}
					});
					
					//callback.onSuccess(responseObject);
				} catch(Exception e) {
					e.printStackTrace();
					//callback.onFailure(e.getMessage());
				}
				Looper.loop();
			}
		};
		t.start();
/*
		   try {
		       client.executeMethod(method);
		       if (method.getStatusCode() == HttpStatus.SC_OK) {
		           response = method.getResponseBodyAsString();
		           System.out.println("Response = " + response);
		       }
		   } catch (IOException e) {
		       e.printStackTrace();
		   } finally {
		       method.releaseConnection();
		   }
*/
		   //PARSE "response" INTO JSON OBJECT

		   //INSERT JSON MANIPULATION TO GAIN NAME (possibly also lat/long of restaurant)
	   

	
	}

	
}
