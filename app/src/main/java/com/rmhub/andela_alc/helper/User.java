package com.rmhub.andela_alc.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MOROLANI on 3/4/2017
 * <p>
 * owm
 * .
 */

public class User implements Parcelable {
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
    private String username, avatarURL, gravatarID, url, htmlURL, followersURL, subscriptionsURL,
            organizationsURL, reposURL, receivedEventsURL, type, score, name, company, location,
            email, bio, blog, gistsURL, followingURL;
    private boolean hireable;
    private int numOfFollowers;
    private int publicRepos;
    private int numOfFollowing;
    private int publicGists;
    private int id;

    public User() {

    }

    protected User(Parcel in) {
        username = in.readString();
        avatarURL = in.readString();
        gravatarID = in.readString();
        url = in.readString();
        htmlURL = in.readString();
        followersURL = in.readString();
        subscriptionsURL = in.readString();
        organizationsURL = in.readString();
        reposURL = in.readString();
        receivedEventsURL = in.readString();
        type = in.readString();
        score = in.readString();
        name = in.readString();
        company = in.readString();
        location = in.readString();
        email = in.readString();
        bio = in.readString();
        blog = in.readString();
        gistsURL = in.readString();
        followingURL = in.readString();
        hireable = in.readByte() != 0;
        numOfFollowers = in.readInt();
        publicRepos = in.readInt();
        numOfFollowing = in.readInt();
        publicGists = in.readInt();
        id = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatarURL);
        dest.writeString(gravatarID);
        dest.writeString(url);
        dest.writeString(htmlURL);
        dest.writeString(followersURL);
        dest.writeString(subscriptionsURL);
        dest.writeString(organizationsURL);
        dest.writeString(reposURL);
        dest.writeString(receivedEventsURL);
        dest.writeString(type);
        dest.writeString(score);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(location);
        dest.writeString(email);
        dest.writeString(bio);
        dest.writeString(blog);
        dest.writeString(gistsURL);
        dest.writeString(followingURL);
        dest.writeByte((byte) (hireable ? 1 : 0));
        dest.writeInt(numOfFollowers);
        dest.writeInt(publicRepos);
        dest.writeInt(numOfFollowing);
        dest.writeInt(publicGists);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", gravatarID='" + gravatarID + '\'' +
                ", url='" + url + '\'' +
                ", htmlURL='" + htmlURL + '\'' +
                ", followersURL='" + followersURL + '\'' +
                ", subscriptionsURL='" + subscriptionsURL + '\'' +
                ", organizationsURL='" + organizationsURL + '\'' +
                ", reposURL='" + reposURL + '\'' +
                ", receivedEventsURL='" + receivedEventsURL + '\'' +
                ", type='" + type + '\'' +
                ", score='" + score + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", hireable='" + hireable + '\'' +
                ", bio='" + bio + '\'' +
                ", numOfFollowers=" + numOfFollowers +
                ", publicRepos=" + publicRepos +
                ", numOfFollowing=" + numOfFollowing +
                ", publicGists=" + publicGists +
                '}';
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatar) {
        this.avatarURL = avatar;
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

    public boolean getHireable() {
        return hireable;
    }

    public void setHireable(boolean hireable) {
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

    public String getFollowersURL() {
        return followersURL;
    }

    public void setFollowersURL(String followers) {
        this.followersURL = followers;
    }

    public String getSubscriptionsURL() {
        return subscriptionsURL;
    }

    public void setSubscriptionsURL(String subscriptions) {
        this.subscriptionsURL = subscriptions;
    }

    public String getOrganizationsURL() {
        return organizationsURL;
    }

    public void setOrganizationsURL(String organizations) {
        this.organizationsURL = organizations;
    }

    public String getReposURL() {
        return reposURL;
    }

    public void setReposUrl(String repos) {
        this.reposURL = repos;
    }

    public String getReceivedEventsURL() {
        return receivedEventsURL;
    }

    public void setReceivedEventsURL(String receivedEvents) {
        this.receivedEventsURL = receivedEvents;
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

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getGistsURL() {
        return gistsURL;
    }

    public void setGistsURL(String gistsURL) {
        this.gistsURL = gistsURL;
    }

    public String getFollowingURL() {
        return followingURL;
    }

    public void setFollowingURL(String followingURL) {
        this.followingURL = followingURL;
    }
}
