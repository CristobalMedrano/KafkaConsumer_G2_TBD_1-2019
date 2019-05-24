package com.citiaps.jawira.model;

public class Tweet {

    private Long id;
    private String name;
    private String text;
    private Integer like;
    private Integer followers;
    private twitter4j.GeoLocation geoLocation;
    private String userLocation;
    private Integer retweet;

    public Tweet() {
        super();
    }

    public Tweet(Long id, String name, String text, Integer like, Integer followers, twitter4j.GeoLocation geoLocation, String userLocation, Integer retweet) {
        super();
        this.setId(id);
        this.setName(name);
        this.setText(text);
        this.setLike(like);
        this.setFollowers(followers);
        this.setGeoLocation(geoLocation);
        this.setUserLocation(userLocation);
        this.setRetweet(retweet);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public twitter4j.GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(twitter4j.GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public Integer getRetweet() {
        return retweet;
    }

    public void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }

    @Override
    public String toString() {
        return "Tweet{id="+ getId() +", name="+ getName() +", text="+ getText() +", like="+ getLike() +", followers="+ getFollowers() +", geoLocation="+ getGeoLocation() +", userLocation="+ getUserLocation() +", retweet="+ getRetweet() +"}";
    }
}
