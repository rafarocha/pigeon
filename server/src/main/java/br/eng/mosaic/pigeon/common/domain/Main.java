package br.eng.mosaic.pigeon.common.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://localhost:8888/samplemapfriends");
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		String content = readFully( is );
		System.out.println( content );
		System.out.println( content.length() );
		
		readJSON( content );
	}
	
	public static String readFully(InputStream is) throws IOException {
		if (is != null) {
		    Writer writer = new StringWriter();
		
		    char[] buffer = new char[4096];
		    try {
		        Reader reader = new BufferedReader(
		                new InputStreamReader(is, "UTF-8"));
		        int n;
		        while ((n = reader.read(buffer)) != -1) {
		        	writer.write(buffer, 0, n);
		        }
		    } finally {
		        is.close();
		    }
		    return writer.toString();
		} else {        
		    return "";
		}
	}
	
	private static JSONArray getFriendsJSON(String content) throws JSONException {
		JSONObject obj = new JSONObject(content);
		String json = obj.getString("friends");
		return new JSONArray(json);
	}
	
	private static JSONObject getPeopleJSON( JSONArray array, int index ) throws JSONException {
		JSONObject obj = array.getJSONObject(index);
		String json = obj.getString("people");
		return new JSONObject(json);
	}
	
	private static double[] getCoordinates( String json ) throws JSONException {
		double[] coords = new double[2];
		JSONArray array = new JSONArray( json );
		JSONObject obj = array.getJSONObject(0);
		coords[0] = Double.parseDouble( obj.getJSONArray( "double" ).getString(0) );
		coords[1] = Double.parseDouble( obj.getJSONArray( "double" ).getString(1) );
		return  coords;
	}
	
	private static People getPeople(JSONObject obj) throws JSONException, ClientProtocolException, IOException {
		People people = new People();
		people.name = obj.getString("name");
		people.email = obj.getString("email");
		people.number = obj.getString("number"); 
		people.picture = getPicture( obj.getString("picture") );
		people.point = getCoordinates( obj.getString("coords") );
		return people;
	}
	
	private static void readJSON(String content) throws ClientProtocolException, IOException {
		try {
			JSONArray array = getFriendsJSON(content);
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = getPeopleJSON(array, i);
				People people = getPeople(obj);
				System.out.println( people.name + " : " + people.email 
						+ " : " + people.number + " : " + people.picture.length 
						+ " : " + people.point[0] + ", " + people.point[1]);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private static byte[] getPicture( String url ) throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream is = entity.getContent();
		return readFully(is, (int) entity.getContentLength());
	}
	
	private static byte[] readFully(InputStream stream, int length) throws IOException {
		byte[] bytes = new byte[length];
		int totalRead = 0;
		int lastRead = -1;
		do {
			lastRead = stream.read(bytes, totalRead, length);
			totalRead += lastRead;
		} while (lastRead > 0 && totalRead < length);
		return bytes;
	}

}

class People {
	public double[] point;
	public byte[] picture;
	public String name;
	public String email;
	public String number;
}