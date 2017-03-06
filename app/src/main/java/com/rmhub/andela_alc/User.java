package com.rmhub.andela_alc;

/**
 * Created by MOROLANI on 3/4/2017
 * <p>
 * owm
 * .
 */

public class User {
  private   String username, avatar, gravatarID, url, htmlURL, followers, subscriptions, organizations, repos,
            receivedEvents, type, score;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGravatarID() {
        return gravatarID;
    }

    public void setGravatarID(String gravatarID) {
        this.gravatarID = gravatarID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlURL() {
        return htmlURL;
    }

    public void setHtmlURL(String htmlURL) {
        this.htmlURL = htmlURL;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(String subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }

    public String getRepos() {
        return repos;
    }

    public void setRepos(String repos) {
        this.repos = repos;
    }

    public String getReceivedEvents() {
        return receivedEvents;
    }

    public void setReceivedEvents(String receivedEvents) {
        this.receivedEvents = receivedEvents;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = String.valueOf(score);
    }
}
