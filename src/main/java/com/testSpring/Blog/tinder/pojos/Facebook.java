
package com.testSpring.Blog.tinder.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Facebook {

    @SerializedName("common_connections")
    @Expose
    private List<Object> commonConnections = null;
    @SerializedName("connection_count")
    @Expose
    private Integer connectionCount;
    @SerializedName("common_interests")
    @Expose
    private List<Object> commonInterests = null;

    public List<Object> getCommonConnections() {
        return commonConnections;
    }

    public void setCommonConnections(List<Object> commonConnections) {
        this.commonConnections = commonConnections;
    }

    public Integer getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(Integer connectionCount) {
        this.connectionCount = connectionCount;
    }

    public List<Object> getCommonInterests() {
        return commonInterests;
    }

    public void setCommonInterests(List<Object> commonInterests) {
        this.commonInterests = commonInterests;
    }

}
