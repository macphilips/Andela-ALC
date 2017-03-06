package com.rmhub.andela_alc;

import java.util.ArrayList;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

public class SearchResult {

    private int totalCount;
    private boolean resultIncomplete;
    private ArrayList<User> users = new ArrayList<>();

    public void parseResult(String result) {
        ParseResult.searchResult(result, this);
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setResultIncomplete(boolean resultIncomplete) {
        this.resultIncomplete = resultIncomplete;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isResultIncomplete() {
        return resultIncomplete;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public static class Header {
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

        public void setStatus(int status) {
            this.status = status;
        }


        public void setLink(String link) {
            this.link = link;
            parseLink();
        }

        private void parseLink() {

        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getRemaining() {
            return remaining;
        }

        public void setRemaining(int remaining) {
            this.remaining = remaining;
        }
    }
}
