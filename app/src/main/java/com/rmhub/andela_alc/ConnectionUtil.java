package com.rmhub.andela_alc;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

public class ConnectionUtil {

    private static final String API_URL = "https://api.github.com";

    public static final String ACCESS_TOKEN = "963aacccc06edcd65d3ba3f4d025cfb84e75adb0";

    public static String getParams(HashMap<String, String> params) {
        try {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (builder.length() != 0)
                    builder.append("&");

                builder.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                builder.append("=");
                builder.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String sendHttpPostRequest(String URL, String params) {

        java.net.URL url;
        String result = "";
        PrintWriter out;
        BufferedReader in;
        try {
            Log.d("sending post request", URL + "?" + params);
            url = new URL(URL);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            out = new PrintWriter(connection.getOutputStream());
            out.println(params + "&");
            //out.flush();

            out.close();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = null;
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }

        Log.d("sendPostHttpRequest", String.valueOf(result));
        return result;

    }

    private String sendHttpGetRequest(String URL) {

        URL url;
        String result = "";
        try {

            Log.d("sending get request", URL);
            url = new URL(URL);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            result = null;
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        }
        Log.d("sendGetHttpRequest", String.valueOf(result));

        return result;


    }
}
