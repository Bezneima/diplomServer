
package com.testSpring.Blog.tinder.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("crop_info")
    @Expose
    private CropInfo cropInfo;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("processedFiles")
    @Expose
    private List<ProcessedFile> processedFiles = null;
    @SerializedName("last_update_time")
    @Expose
    private String lastUpdateTime;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("extension")
    @Expose
    private String extension;
    @SerializedName("webp_qf")
    @Expose
    private List<Integer> webpQf = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CropInfo getCropInfo() {
        return cropInfo;
    }

    public void setCropInfo(CropInfo cropInfo) {
        this.cropInfo = cropInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ProcessedFile> getProcessedFiles() {
        return processedFiles;
    }

    public void setProcessedFiles(List<ProcessedFile> processedFiles) {
        this.processedFiles = processedFiles;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public List<Integer> getWebpQf() {
        return webpQf;
    }

    public void setWebpQf(List<Integer> webpQf) {
        this.webpQf = webpQf;
    }

}
