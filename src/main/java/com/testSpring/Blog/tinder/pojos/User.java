
package com.testSpring.Blog.tinder.pojos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("jobs")
    @Expose
    private List<Object> jobs = null;
    @SerializedName("schools")
    @Expose
    private List<Object> schools = null;
    @SerializedName("show_gender_on_profile")
    @Expose
    private Boolean showGenderOnProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public List<Object> getJobs() {
        return jobs;
    }

    public void setJobs(List<Object> jobs) {
        this.jobs = jobs;
    }

    public List<Object> getSchools() {
        return schools;
    }

    public void setSchools(List<Object> schools) {
        this.schools = schools;
    }

    public Boolean getShowGenderOnProfile() {
        return showGenderOnProfile;
    }

    public void setShowGenderOnProfile(Boolean showGenderOnProfile) {
        this.showGenderOnProfile = showGenderOnProfile;
    }

}
