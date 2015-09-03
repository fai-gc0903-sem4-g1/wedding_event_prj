/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebook.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 *
 * @author SON
 */
public class FBConnection {
    public static final String FB_APP_ID = "1604300546376987";
	public static final String FB_APP_SECRET = "2951336ef374df4f51bd3727ddd7b133";
	public static final String REDIRECT_URI = "http://localhost:8080/wedding_event_prj/account/FBRedirect";
	
	static String accessToken = "";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + FB_APP_ID + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "utf-8") + "&scope=email";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/oauth/accesstoken?" + 
					"client_id=" + FB_APP_ID + 
					"&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "utf-8") +
					"&client_secret=" + FB_APP_SECRET + 
					"&code=" + code;
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {
		if ("".equals(accessToken)) {
			URL fbGraphUrl;
			try {
				fbGraphUrl = new URL(getFBGraphUrl(code));
				URLConnection fbConnection;
				StringBuilder b = new StringBuilder();

				fbConnection = fbGraphUrl.openConnection();
				BufferedReader in;
				in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null)
					b.append(inputLine + "\n");
				in.close();
				accessToken = b.toString();
				System.out.println(accessToken);
				if (accessToken.startsWith("{"))
					throw new RuntimeException("ERROR: Access token invalid: " + accessToken);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return accessToken;
	}

	public static void main(String[] args) {
		System.out.println(new FBConnection().getFBAuthUrl());
	}
}
