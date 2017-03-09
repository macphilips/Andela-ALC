package com.rmhub.andela_alc.helper;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.rmhub.andela_alc.callback.ResultCallback;
import com.rmhub.andela_alc.interfaces.SearchQuery;

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


    public static void search(SearchQuery query, ResultCallback callback) {
        String params = "q=" + query.getSearchQuery()
                //+ "&access_token=" + ACCESS_TOKEN
                + "&per_page=20";
        search(String.format("%s?%s", query.getURL(), params), callback);

    }

    @Nullable
    public static void search(String url_string, ResultCallback callback) {
        URL url;
        String result = "";
        BufferedReader in;
        try {
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
            if (limit != null && !TextUtils.isEmpty(limit))
                callback.getHeader().limit(Integer.parseInt(limit));

            if (remain != null && !TextUtils.isEmpty(remain))
                callback.getHeader().remaining(Integer.parseInt(remain));

            if (link != null && !TextUtils.isEmpty(link))
                callback.getHeader().link(link);

            Log.d("", String.valueOf(code));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        } catch (NumberFormatException ignored) {
        }

        Log.d("sendPostHttpRequest", String.valueOf(result));
    }

    public static void loadFullUserProfile(User user) {
        URL url;
        String result = "";
        BufferedReader in;
        try {
            Log.d("sending post request", "url=" + user.getUrl());
            url = new URL(user.getUrl());
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            ParseResult.userResult(result, user);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            result = null;
        } catch (NumberFormatException ignored) {
        }

        Log.d("sendPostHttpRequest", String.valueOf(result));
    }

}
