package com.rmhub.andela_alc;

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
        return "";
    }

    public String getSearchQuery() {
        StringBuilder builder = new StringBuilder();
        if (query != null) {
            builder.append(query).append(" ");
        }
        if (type != null) {
            builder.append("type:").append(query).append(" ");
        }
        if (in != null) {
            builder.append("in:").append(in).append(" ");
        }
        if (repos != null) {
            builder.append("repos:").append(repos).append(" ");
        }
        if (location != null) {
            builder.append("location:").append(location).append(" ");
        }
        if (language != null) {
            builder.append("language:").append(language).append(" ");
        }
        if (created != null) {
            builder.append("created:").append(created).append(" ");
        }
        if (followers != null) {
            builder.append("followers:").append(followers).append(" ");
        }
        return builder.toString();
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
