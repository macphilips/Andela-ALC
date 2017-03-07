package com.rmhub.andela_alc;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static final String API_URL = "https://api.github.com";

    public static final String ACCESS_TOKEN = "176e63e80634a10cf8e05ee4dd17e91c0231ecb5";

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

    public static String search(SearchQuery query, SearchResultCallback callback) {
        java.net.URL url;
        String result = "";
        BufferedReader in;
        try {
            String params = "q=" + query.getSearchQuery()
                    + "&access_token=" + ACCESS_TOKEN;
            String link = String.format("%s?%s", query.getURL(), params);

            Log.d("sending post request", "url=" + link);
            url = new URL(link);
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            //conn.setDoOutput(true);
            // conn.setRequestProperty("Authorization", "basic " + Base64.encode(String.valueOf("macphilips:" + ACCESS_TOKEN).getBytes(), Base64.NO_WRAP));
            conn.connect();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String links = conn.getHeaderField("Link");
            String limit = conn.getHeaderField("X-RateLimit-Limit");
            String remain = conn.getHeaderField("X-RateLimit-Remaining");
            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            callback.searchResult(result);
            callback.getHeader().limit(Integer.parseInt(limit));
            callback.getHeader().remaining(Integer.parseInt(remain));
            callback.getHeader().link(link);

            Log.d("sendPostHttpRequest", "link = " + String.valueOf(links) + " limit = " + String.valueOf(limit)
                    + " remain = " + String.valueOf(remain));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        } catch (NumberFormatException ignored) {

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
