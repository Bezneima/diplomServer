
package com.testSpring.Blog.tinder.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spotify {

    @SerializedName("spotify_connected")
    @Expose
    private Boolean spotifyConnected;

    public Boolean getSpotifyConnected() {
        return spotifyConnected;
    }

    public void setSpotifyConnected(Boolean spotifyConnected) {
        this.spotifyConnected = spotifyConnected;
    }

}
