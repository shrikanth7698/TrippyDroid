package com.shrikanthravi.trippydroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shrikanthravi.trippydroid.data.model.Attraction;
import com.shrikanthravi.trippydroid.data.model.NearbyRequest;
import com.shrikanthravi.trippydroid.data.model.NearbyResponse;
import com.shrikanthravi.trippydroid.data.model.remote.APIService;
import com.shrikanthravi.trippydroid.data.model.remote.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityAttractionsFragment extends Fragment {


    public CityAttractionsFragment() {
        // Required empty public constructor
    }

    RecyclerView attractionRV;
    AttractionAdapter attractionAdapter;
    List<Attraction> attractionList;
    SwipeRefreshLayout swipeRefreshLayout;
    int refreshFlag=0;
    APIService mApiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_attractions, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        attractionRV = (RecyclerView) view.findViewById(R.id.NearbyAttractionsRV);
        attractionRV.setNestedScrollingEnabled(true);
        attractionList = new ArrayList<>();
        attractionAdapter = new AttractionAdapter(attractionList,getActivity().getApplicationContext());
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        attractionRV.setLayoutManager(verticalLayoutmanager);
        attractionRV.setAdapter(attractionAdapter);
        attractionRV.addOnItemTouchListener(
                new MyRecyclerItemClickListener(getActivity().getApplicationContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent next = new Intent(getActivity(),AttractionActivity.class);
                        next.putExtra("AttractionName",attractionList.get(position).getName());
                        ArrayList<String> imgUrls = new ArrayList<>(attractionList.get(position).getImgurls());
                        next.putStringArrayListExtra("AttractionImgUrls",imgUrls);
                        next.putExtra("AudioUrl",attractionList.get(position).getAudiourl());
                        next.putExtra("Desc",attractionList.get(position).getDesc());
                        next.putExtra("lat",attractionList.get(position).getLat());
                        next.putExtra("lng",attractionList.get(position).getLng());
                        getActivity().startActivity(next);
                    }
                })
        );
        mApiService = ApiUtils.getAPIService();
        Call call = mApiService.requestNearby(new NearbyRequest("English","24.594086", "73.729280","0"));
        swipeRefreshLayout.setRefreshing(true);
        call.enqueue(new Callback<NearbyResponse>() {
            @Override
            public void onResponse(Call<NearbyResponse> call, Response<NearbyResponse> response) {

                System.out.println("Nearby response code"+response.body());
                if (response.isSuccessful()) {

                    try {

                        attractionList.addAll(response.body().getList());
                        attractionAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        refreshFlag=1;
                    }

                    catch (Exception e) {
                        if (e.getMessage() != null) {

                        }
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<NearbyResponse> call, Throwable t) {
                //Log.e(TAG, "Unable to submit post to API.");
                System.out.println("Nearby request error");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                if(refreshFlag==1){
                    swipeRefreshLayout.setRefreshing(false);
                }
                else {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }
        });

        return view;
    }

}
