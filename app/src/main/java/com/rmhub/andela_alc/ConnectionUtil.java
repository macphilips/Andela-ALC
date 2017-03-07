package com.rmhub.andela_alc;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

public class ConnectionUtil {

    public static final String API_URL = "https://api.github.com";
    public static final String ACCESS_TOKEN = "176e63e80634a10cf8e05ee4dd17e91c0231ecb5";


    public static String search(SearchQuery query, SearchResultCallback callback) {
        java.net.URL url;
        String result = "";
        BufferedReader in;
        try {
            String params = "q=" + query.getSearchQuery()
                    //+ "&access_token=" + ACCESS_TOKEN
                    ;
            String url_string = String.format("%s?%s", query.getURL(), params);

            Log.d("sending post request", "url=" + url_string);
            url = new URL(url_string);
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String link = conn.getHeaderField("Link");
            String limit = conn.getHeaderField("X-RateLimit-Limit");
            String remain = conn.getHeaderField("X-RateLimit-Remaining");
            String code = conn.getRequestMethod();
            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            callback.searchResult(result);
            callback.getHeader().limit(Integer.parseInt(limit));
            callback.getHeader().remaining(Integer.parseInt(remain));
            callback.getHeader().link(link);
            Log.d("", String.valueOf(code));

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        } catch (NumberFormatException ignored) {

        }

        Log.d("sendPostHttpRequest", String.valueOf(result));
        return result;

    }

}
