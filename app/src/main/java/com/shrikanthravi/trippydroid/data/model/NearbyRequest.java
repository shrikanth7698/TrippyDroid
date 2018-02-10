package com.shrikanthravi.trippydroid.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shrikanthravi on 02/12/17.
 */

public class NearbyRequest {

    private String lang;

    private String lat;

    private String lng;

    private String flag;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public NearbyRequest(String lang, String lat, String lng) {

        this.lang = lang;
        this.lat = lat;
        this.lng = lng;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public NearbyRequest(String lang, String lat, String lng, String flag) {
        this.lang = lang;
        this.lat = lat;
        this.lng = lng;
        this.flag = flag;
    }
}
