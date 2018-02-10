package com.shrikanthravi.trippydroid.data.model.remote;

/**
 * Created by shrikanthravi on 02/12/17.
 */

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://ec2-user@ec2-13-127-48-191.ap-south-1.compute.amazonaws.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
