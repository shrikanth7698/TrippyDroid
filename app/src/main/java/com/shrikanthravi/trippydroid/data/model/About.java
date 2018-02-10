package com.shrikanthravi.trippydroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shrikanthravi on 02/12/17.
 */

public class About {
    @SerializedName("audiourl")
    private String audiourl;

    @SerializedName("imurls")
    private List<String> imurls = null;

    @SerializedName("desc")
    private String desc;

    public About(String audiourl, List<String> imurls, String desc) {
        this.audiourl = audiourl;
        this.imurls = imurls;
        this.desc = desc;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
    }

    public List<String> getImurls() {
        return imurls;
    }

    public void setImurls(List<String> imurls) {
        this.imurls = imurls;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
