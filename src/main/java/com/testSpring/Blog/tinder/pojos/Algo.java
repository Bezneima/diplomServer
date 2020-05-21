
package com.testSpring.Blog.tinder.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Algo {

    @SerializedName("width_pct")
    @Expose
    private Double widthPct;
    @SerializedName("x_offset_pct")
    @Expose
    private Double xOffsetPct;
    @SerializedName("height_pct")
    @Expose
    private Double heightPct;
    @SerializedName("y_offset_pct")
    @Expose
    private Double yOffsetPct;

    public Double getWidthPct() {
        return widthPct;
    }

    public void setWidthPct(Double widthPct) {
        this.widthPct = widthPct;
    }

    public Double getXOffsetPct() {
        return xOffsetPct;
    }

    public void setXOffsetPct(Double xOffsetPct) {
        this.xOffsetPct = xOffsetPct;
    }

    public Double getHeightPct() {
        return heightPct;
    }

    public void setHeightPct(Double heightPct) {
        this.heightPct = heightPct;
    }

    public Double getYOffsetPct() {
        return yOffsetPct;
    }

    public void setYOffsetPct(Double yOffsetPct) {
        this.yOffsetPct = yOffsetPct;
    }

}
