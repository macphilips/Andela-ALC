package com.rmhub.andela_alc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MOROLANI on 3/4/2017
 * <p>
 * owm
 * .
 */

public class User implements Parcelable{
    private String username, avatar, gravatarID, url, htmlURL, followers, subscriptions,
            organizations, repos, receivedEvents, type, score;
    private String name, company, location, email, hireable, bio;
    private int numOfFollowers, publicRepos, numOfFollowing, publicGists;


    protected User(Parcel in) {
        username = in.readString();
        avatar = in.readString();
        gravatarID = in.readString();
        url = in.readString();
        htmlURL = in.readString();
        followers = in.readString();
        subscriptions = in.readString();
        organizations = in.readString();
        repos = in.readString();
        receivedEvents = in.readString();
        type = in.readString();
        score = in.readString();
        name = in.readString();
        company = in.readString();
        location = in.readString();
        email = in.readString();
        hireable = in.readString();
        bio = in.readString();
        numOfFollowers = in.readInt();
        publicRepos = in.readInt();
        numOfFollowing = in.readInt();
        publicGists = in.readInt();
    }

    public User() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatar);
        dest.writeString(gravatarID);
        dest.writeString(url);
        dest.writeString(htmlURL);
        dest.writeString(followers);
        dest.writeString(subscriptions);
        dest.writeString(organizations);
        dest.writeString(repos);
        dest.writeString(receivedEvents);
        dest.writeString(type);
        dest.writeString(score);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(hireable);
        dest.writeString(bio);
        dest.writeInt(numOfFollowers);
        dest.writeInt(publicRepos);
        dest.writeInt(numOfFollowing);
        dest.writeInt(publicGists);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public void setScore(String score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireable() {
        return hireable;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getNumOfFollowers() {
        return numOfFollowers;
    }

    public void setNumOfFollowers(int numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }

    public int getNumOfFollowing() {
        return numOfFollowing;
    }

    public void setNumOfFollowing(int numOfFollowing) {
        this.numOfFollowing = numOfFollowing;
    }

    public int getPublicGists() {
        return publicGists;
    }

    public void setPublicGists(int publicGists) {
        this.publicGists = publicGists;
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
