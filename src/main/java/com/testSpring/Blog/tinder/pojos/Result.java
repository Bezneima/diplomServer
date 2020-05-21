
package com.testSpring.Blog.tinder.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("instagram")
    @Expose
    private Instagram instagram;
    @SerializedName("facebook")
    @Expose
    private Facebook facebook;
    @SerializedName("spotify")
    @Expose
    private Spotify spotify;
    @SerializedName("distance_mi")
    @Expose
    private Integer distanceMi;
    @SerializedName("content_hash")
    @Expose
    private String contentHash;
    @SerializedName("s_number")
    @Expose
    private Integer sNumber;
    @SerializedName("teaser")
    @Expose
    private Teaser teaser;
    @SerializedName("teasers")
    @Expose
    private List<Teaser_> teasers = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instagram getInstagram() {
        return instagram;
    }

    public void setInstagram(Instagram instagram) {
        this.instagram = instagram;
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public Spotify getSpotify() {
        return spotify;
    }

    public void setSpotify(Spotify spotify) {
        this.spotify = spotify;
    }

    public Integer getDistanceMi() {
        return distanceMi;
    }

    public void setDistanceMi(Integer distanceMi) {
        this.distanceMi = distanceMi;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    public Integer getSNumber() {
        return sNumber;
    }

    public void setSNumber(Integer sNumber) {
        this.sNumber = sNumber;
    }

    public Teaser getTeaser() {
        return teaser;
    }

    public void setTeaser(Teaser teaser) {
        this.teaser = teaser;
    }

    public List<Teaser_> getTeasers() {
        return teasers;
    }

    public void setTeasers(List<Teaser_> teasers) {
        this.teasers = teasers;
    }

}
