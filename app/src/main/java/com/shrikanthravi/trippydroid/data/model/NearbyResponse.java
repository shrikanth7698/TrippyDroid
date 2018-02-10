package com.shrikanthravi.trippydroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shrikanthravi on 02/12/17.
 */

public class NearbyResponse {

    @SerializedName("about")
    private About about;

    @SerializedName("resp")

    private List<Attraction> list;
    @SerializedName("success")

    private Boolean success;

    public List<Attraction> getList() {
        return list;
    }

    public void setResp(List<Attraction> list) {
        this.list = list;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public About getAbout() {
        return about;
    }

    public void setAbout(About about) {
        this.about = about;
    }

}
