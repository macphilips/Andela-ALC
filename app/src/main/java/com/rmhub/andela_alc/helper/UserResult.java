package com.rmhub.andela_alc.helper;

import android.util.Log;

import com.rmhub.andela_alc.callback.ResultCallback;
import com.rmhub.andela_alc.interfaces.HeaderInfo;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

public class UserResult implements ResultCallback {

    private int totalCount;
    private boolean resultIncomplete;
    private ArrayList<User> users = new ArrayList<>();
    private UserSearchResultHeader header = new UserSearchResultHeader();

    @Override
    public String toString() {
        return "UserResult{" +
                "totalCount=" + totalCount +
                ", resultIncomplete=" + resultIncomplete +
                ", users=" + users +
                ", header=" + header +
                '}';
    }

    public void searchResult(String result) {
        ParseResult.searchResult(result, this);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isResultIncomplete() {
        return resultIncomplete;
    }

    public void setResultIncomplete(boolean resultIncomplete) {
        this.resultIncomplete = resultIncomplete;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public UserSearchResultHeader getHeader() {
        return header;
    }

    public void setHeader(UserSearchResultHeader header) {
        this.header = header;
    }

    public static class UserSearchResultHeader implements HeaderInfo {
        private int status;
        private String link;
        private int limit, remaining;

        private String next, last;

        public String getNext() {
            return next;
        }

        public String getLast() {
            return last;
        }

        public int getStatus() {
            return status;
        }

        public void status(int status) {
            this.status = status;
        }

        public void link(String link) {
            this.link = link;
            parseLink();
            Log.d("UserResult", "next=" + next);
            Log.d("UserResult", " last=" + last);
        }

        private void parseLink() {
            StringTokenizer token = new StringTokenizer(link, ",");
            while (token.hasMoreElements()) {
                String nextToken = token.nextToken();
                String[] split = nextToken.split(";");
                if (split[1].contains("next")) {
                    next = split[0].replace('<', ' ').replace('>', ' ').trim();
                } else if (split[1].contains("last")) {
                    last = split[0].replace('<', ' ').replace('>', ' ').trim();
                }
            }
        }

        public int getLimit() {
            return limit;
        }

        public void limit(int limit) {
            this.limit = limit;
        }

        public int getRemaining() {
            return remaining;
        }

        public void remaining(int remaining) {
            this.remaining = remaining;
        }

        @Override
        public String toString() {
            return "UserSearchResultHeader{" +
                    "status=" + status +
                    ", link='" + link + '\'' +
                    ", limit=" + limit +
                    ", remaining=" + remaining +
                    ", next='" + next + '\'' +
                    ", last='" + last + '\'' +
                    '}';
        }
    }
}
