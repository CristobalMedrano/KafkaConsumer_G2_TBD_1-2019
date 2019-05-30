package com.tbd.consumer.model;

import twitter4j.GeoLocation;

import java.util.Date;

public class TweetDate {

    private Long id;
    private String name;
    private String text;
    private Integer like;
    private Integer followers;
    private GeoLocation geoLocation;
    private String userLocation;
    private Integer retweet;
    private Date publicationDate;

    public TweetDate() {
        super();
    }

    public TweetDate(Long id, String name, String text, Integer like, Integer followers, GeoLocation geoLocation, String userLocation, Integer retweet, Date publicationDate) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.like = like;
        this.followers = followers;
        this.geoLocation = geoLocation;
        this.userLocation = userLocation;
        this.retweet = retweet;
        this.publicationDate = publicationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
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

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
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
        return "{'id':"+ getId() +", 'name':'"+ getName() +"', 'text':'"+ getText() +"', 'like':"+ getLike() +", 'followers':"+ getFollowers() +", 'geoLocation':'"+ getGeoLocation() +"', 'userLocation':'"+ getUserLocation() +"', 'retweet':"+ getRetweet() +"}";
    }
}
