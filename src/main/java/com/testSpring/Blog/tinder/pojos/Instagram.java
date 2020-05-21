
package com.testSpring.Blog.tinder.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instagram {

    @SerializedName("last_fetch_time")
    @Expose
    private String lastFetchTime;
    @SerializedName("completed_initial_fetch")
    @Expose
    private Boolean completedInitialFetch;
    @SerializedName("photos")
    @Expose
    private List<Photo_> photos = null;
    @SerializedName("media_count")
    @Expose
    private Integer mediaCount;

    public String getLastFetchTime() {
        return lastFetchTime;
    }

    public void setLastFetchTime(String lastFetchTime) {
        this.lastFetchTime = lastFetchTime;
    }

    public Boolean getCompletedInitialFetch() {
        return completedInitialFetch;
    }

    public void setCompletedInitialFetch(Boolean completedInitialFetch) {
        this.completedInitialFetch = completedInitialFetch;
    }

    public List<Photo_> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo_> photos) {
        this.photos = photos;
    }

    public Integer getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(Integer mediaCount) {
        this.mediaCount = mediaCount;
    }

}
