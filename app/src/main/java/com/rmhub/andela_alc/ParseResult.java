package com.rmhub.andela_alc;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

class ParseResult {

    public static void searchResult(String result, UserSearchResult userSearchResult) {
        try {
            JSONObject resultObj = new JSONObject(result);
            if (resultObj.has("total_count")) {
                userSearchResult.setTotalCount(resultObj.getInt("total_count"));
            }
            if (resultObj.has("incomplete_results")) {
                userSearchResult.setResultIncomplete(resultObj.getBoolean("incomplete_results"));
            }
            if (resultObj.has("items")) {
                userSearchResult.setUsers(getUsers(resultObj));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private static ArrayList<User> getUsers(JSONObject resultObj) throws JSONException {
        ArrayList<User> users = new ArrayList<>();
        JSONArray items = resultObj.getJSONArray("items");
        for (int i = 0, n = items.length(); i < n; i++) {
            JSONObject item = items.getJSONObject(i);
            User user = new User();
            if (item.has("login")) {
                user.setUsername(item.getString("login"));
            }
            if (item.has("avatar_url")) {
                user.setAvatar(item.getString("avatar_url"));
            }
            if (item.has("gravatar")) {
                user.setGravatarID(item.getString("gravatar"));
            }
            if (item.has("url")) {

                user.setUrl(item.getString("url"));
            }
            if (item.has("html_url")) {

                user.setHtmlURL(item.getString("html_url"));
            }
            if (item.has("followers_url")) {

                user.setFollowers(item.getString("followers_url"));
            }
            if (item.has("subscriptions_url")) {

                user.setSubscriptions(item.getString("subscriptions_url"));
            }
            if (item.has("organizations_url")) {

                user.setOrganizations(item.getString("organizations_url"));
            }
            if (item.has("repos_url")) {

                user.setRepos(item.getString("repos_url"));
            }
            if (item.has("received_events_url")) {

                user.setReceivedEvents(item.getString("received_events_url"));
            }

            if (item.has("type")) {

                user.setType(item.getString("type"));
            }
            if (item.has("score")) {

                user.setScore(item.getDouble("score"));
            }
            users.add(user);
        }
        return users;
    }
}
