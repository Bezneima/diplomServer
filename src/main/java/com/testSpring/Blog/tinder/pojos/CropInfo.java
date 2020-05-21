
package com.testSpring.Blog.tinder.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CropInfo {

    @SerializedName("user")
    @Expose
    private User_ user;
    @SerializedName("algo")
    @Expose
    private Algo algo;
    @SerializedName("processed_by_bullseye")
    @Expose
    private Boolean processedByBullseye;
    @SerializedName("user_customized")
    @Expose
    private Boolean userCustomized;

    public User_ getUser() {
        return user;
    }

    public void setUser(User_ user) {
        this.user = user;
    }

    public Algo getAlgo() {
        return algo;
    }

    public void setAlgo(Algo algo) {
        this.algo = algo;
    }

    public Boolean getProcessedByBullseye() {
        return processedByBullseye;
    }

    public void setProcessedByBullseye(Boolean processedByBullseye) {
        this.processedByBullseye = processedByBullseye;
    }

    public Boolean getUserCustomized() {
        return userCustomized;
    }

    public void setUserCustomized(Boolean userCustomized) {
        this.userCustomized = userCustomized;
    }

}
