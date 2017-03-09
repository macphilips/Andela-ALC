package com.rmhub.andela_alc.helper;

import com.rmhub.andela_alc.interfaces.SearchQuery;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by MOROLANI on 3/6/2017
 * <p>
 * owm
 * .
 */

public class UserSearchQuery implements SearchQuery {

    private String query, type, in, repos, location, language, created, followers;

    private UserSearchQuery(String query, String type, String in, String repos, String location, String language, String created, String followers) {
        this.query = query;
        this.type = type;
        this.in = in;
        this.repos = repos;
        this.location = location;
        this.language = language;
        this.created = created;
        this.followers = followers;
    }

    public String getURL() {
        return ConnectionUtil.API_URL + "/search/users";
    }

    public String getSearchQuery() {
        try {
            StringBuilder builder = new StringBuilder();

            String encodeSpace = URLEncoder.encode(" ", "UTF-8");

            if (query != null) {
                builder.append(query).append(encodeSpace);
            }
            if (type != null) {
                builder.append("type:").append(query).append(encodeSpace);
            }
            if (in != null) {
                builder.append("in:").append(in).append(encodeSpace);
            }
            if (repos != null) {
                builder.append("repos:").append(repos).append(encodeSpace);
            }
            if (location != null) {
                builder.append("location:").append(location).append(encodeSpace);
            }
            if (language != null) {
                builder.append("language:").append(language).append(encodeSpace);
            }
            if (created != null) {
                builder.append("created:").append(created).append(encodeSpace);
            }
            if (followers != null) {
                builder.append("followers:").append(followers).append(encodeSpace);
            }
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static class QueryBuilder {
        private String query, type, in, repos, location, language, created, followers;

        public UserSearchQuery builder() {
            return new UserSearchQuery(query, type, in, repos, location, language, created, followers);
        }

        public QueryBuilder setQuery(String query) {
            this.query = query;
            return this;
        }

        public QueryBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public QueryBuilder setIn(String in) {
            this.in = in;
            return this;
        }

        public QueryBuilder setRepos(String repos) {
            this.repos = repos;
            return this;
        }

        public QueryBuilder setLocation(String location) {
            this.location = location;
            return this;
        }

        public QueryBuilder setLanguage(String language) {
            this.language = language;
            return this;
        }

        public QueryBuilder setCreated(String created) {
            this.created = created;
            return this;
        }

        public QueryBuilder setFollowers(long followers) {
            this.followers = String.valueOf(followers);
            return this;
        }

    }
}
