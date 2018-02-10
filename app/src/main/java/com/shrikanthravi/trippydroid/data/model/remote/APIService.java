package com.shrikanthravi.trippydroid.data.model.remote;

import com.shrikanthravi.trippydroid.data.model.NearbyRequest;
import com.shrikanthravi.trippydroid.data.model.NearbyResponse;
import com.shrikanthravi.trippydroid.data.model.SubAttractionsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by shrikanthravi on 02/12/17.
 */

public interface APIService {

    @Headers("Content-Type: application/json")
    @POST("/fetchAttractions/")
    Call<NearbyResponse> requestNearby(@Body NearbyRequest nearbyRequest);

    @POST("/fetchSubAttractions/")
    Call<SubAttractionsResponse> requestSub(@Body NearbyRequest nearbyRequest);
}
