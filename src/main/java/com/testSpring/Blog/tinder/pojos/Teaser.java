
package com.testSpring.Blog.tinder.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Teaser {

    @SerializedName("string")
    @Expose
    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
