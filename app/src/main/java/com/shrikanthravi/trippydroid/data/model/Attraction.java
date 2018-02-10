package com.shrikanthravi.trippydroid.data.model;

/**
 * Created by shrikanthravi on 02/12/17.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Attraction {

    @SerializedName("name")

    private String name;
    @SerializedName("Vicinity")
    private String vicinity;

    @SerializedName("highs")
    private List<String> highslist;

    @SerializedName("PlaceID")

    private String placeID;
    @SerializedName("timeclose")

    private Integer timeclose;
    @SerializedName("lat")

    private Double lat;
    @SerializedName("audiourls")

    private String audiourl;
    @SerializedName("Google_name")

    private String googleName;
    @SerializedName("timeopen")

    private Integer timeopen;
    @SerializedName("imgurls")

    private List<String> imgurls = null;
    @SerializedName("lng")

    private Double lng;
    @SerializedName("desc")

    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public Integer getTimeclose() {
        return timeclose;
    }

    public void setTimeclose(Integer timeclose) {
        this.timeclose = timeclose;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAudiourl() {
        return audiourl;
    }

    public void setAudiourl(String audiourl) {
        this.audiourl = audiourl;
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public Integer getTimeopen() {
        return timeopen;
    }

    public void setTimeopen(Integer timeopen) {
        this.timeopen = timeopen;
    }

    public List<String> getImgurls() {
        return imgurls;
    }

    public void setImgurls(List<String> imgurls) {
        this.imgurls = imgurls;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Attraction(String name, String vicinity, String placeID, Integer timeclose, Double lat, String audiourl, String googleName, Integer timeopen, List<String> imgurls, Double lng, String desc) {
        this.name = name;
        this.vicinity = vicinity;
        this.placeID = placeID;
        this.timeclose = timeclose;
        this.lat = lat;
        this.audiourl = audiourl;
        this.googleName = googleName;
        this.timeopen = timeopen;
        this.imgurls = imgurls;
        this.lng = lng;
        this.desc = desc;
    }

    public List<String> getHighslist() {
        return highslist;
    }

    public void setHighslist(List<String> highslist) {
        this.highslist = highslist;
    }

    public Attraction(String name, String imgurl) {
        this.name = name;
        List<String> list = new ArrayList<>();
        list.add(imgurl);
        this.imgurls = list;
    }
}
