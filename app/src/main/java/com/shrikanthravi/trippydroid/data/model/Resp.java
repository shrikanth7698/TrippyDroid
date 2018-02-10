package com.shrikanthravi.trippydroid.data.model;

/**
 * Created by shrikanthravi on 03/12/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Resp {

    @SerializedName("said")
    
    private Integer said;
    @SerializedName("name")
    
    private String name;
    @SerializedName("audiourls")
    
    private String audiourls;
    @SerializedName("imurls")
    
    private List<String> imurls = null;
    @SerializedName("ar")
    
    private Boolean ar;
    @SerializedName("aurl")
    
    private String aurl;
    @SerializedName("desc")
    
    private String desc;

    public Integer getSaid() {
        return said;
    }

    public void setSaid(Integer said) {
        this.said = said;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudiourls() {
        return audiourls;
    }

    public void setAudiourls(String audiourls) {
        this.audiourls = audiourls;
    }

    public List<String> getImurls() {
        return imurls;
    }

    public void setImurls(List<String> imurls) {
        this.imurls = imurls;
    }

    public Boolean getAr() {
        return ar;
    }

    public void setAr(Boolean ar) {
        this.ar = ar;
    }

    public String getAurl() {
        return aurl;
    }

    public void setAurl(String aurl) {
        this.aurl = aurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}



