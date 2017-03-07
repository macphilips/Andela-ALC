package com.rmhub.andela_alc;

import android.support.annotation.NonNull;
import android.util.Log;

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
            Log.d("ParseResult", String.valueOf(userSearchResult));
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
                user.setAvatarURL(item.getString("avatar_url"));
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
                user.setFollowersURL(item.getString("followers_url"));
            }
            if (item.has("following_url")) {
                user.setFollowingURL(item.getString("following_url"));
            }

            if (item.has("gists_url")) {
                user.setGistsURL(item.getString("gists_url"));
            }
            if (item.has("subscriptions_url")) {
                user.setSubscriptionsURL(item.getString("subscriptions_url"));
            }
            if (item.has("organizations_url")) {
                user.setOrganizationsURL(item.getString("organizations_url"));
            }
            if (item.has("repos_url")) {
                user.setReposUrl(item.getString("repos_url"));
            }
            if (item.has("received_events_url")) {
                user.setReceivedEventsURL(item.getString("received_events_url"));
            }

            if (item.has("type")) {
                user.setType(item.getString("type"));
            }
            if (item.has("score")) {
                user.setScore(item.getDouble("score"));
            }

            if (item.has("name")) {
                user.setName(item.getString("name"));
            }
            if (item.has("company")) {
                user.setCompany(item.getString("company"));
            }

            if (item.has("blog")) {
                user.setBlog(item.getString("blog"));
            }
            if (item.has("location")) {
                user.setLocation(item.getString("location"));
            }
            if (item.has("email")) {
                user.setLocation(item.getString("email"));
            }
            if (item.has("hireable")) {
                user.setHireable(item.getBoolean("hireable"));
            }
            if (item.has("bio")) {
                user.setBio(item.getString("bio"));
            }
            if (item.has("public_repos")) {
                user.setPublicRepos(item.getInt("public_repos"));
            }
            if (item.has("public_gists")) {
                user.setPublicGists(item.getInt("public_gists"));
            }
            if (item.has("followers")) {
                user.setNumOfFollowers(item.getInt("followers"));
            }
            if (item.has("following")) {
                user.setNumOfFollowing(item.getInt("following"));
            }

            Log.d("ParseResult", String.valueOf(user));
            users.add(user);
        }
        return users;
    }
}
