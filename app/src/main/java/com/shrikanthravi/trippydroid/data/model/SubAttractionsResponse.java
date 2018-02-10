package com.shrikanthravi.trippydroid.data.model;

/**
 * Created by shrikanthravi on 03/12/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubAttractionsResponse {

    @SerializedName("resp")

    private List<Resp> resp = null;
    @SerializedName("success")

    private Boolean success;

    public List<Resp> getResp() {
        return resp;
    }

    public void setResp(List<Resp> resp) {
        this.resp = resp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}