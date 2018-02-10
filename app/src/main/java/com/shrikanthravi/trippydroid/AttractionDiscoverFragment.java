package com.shrikanthravi.trippydroid;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shrikanthravi.ArDemo1.UnityPlayerActivity;
import com.shrikanthravi.trippydroid.data.model.Attraction;
import com.shrikanthravi.trippydroid.data.model.NearbyRequest;
import com.shrikanthravi.trippydroid.data.model.NearbyResponse;
import com.shrikanthravi.trippydroid.data.model.Resp;
import com.shrikanthravi.trippydroid.data.model.SubAttractionsResponse;
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
public class AttractionDiscoverFragment extends Fragment {


    public AttractionDiscoverFragment() {
        // Required empty public constructor
    }
    RecyclerView attractionRV;
    AttractionAdapter1 attractionAdapter;
    List<Resp> attractionList;
    SwipeRefreshLayout swipeRefreshLayout;
    int refreshFlag=0;
    APIService mApiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attraction_discover, container, false);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout);
        attractionRV = (RecyclerView) view.findViewById(R.id.SubAttractionsRV);
        attractionRV.setNestedScrollingEnabled(true);
        attractionList = new ArrayList<>();
        attractionAdapter = new AttractionAdapter1(attractionList,getActivity().getApplicationContext());
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        attractionRV.setLayoutManager(verticalLayoutmanager);
        attractionRV.setAdapter(attractionAdapter);
        attractionRV.addOnItemTouchListener(
                new MyRecyclerItemClickListener(getActivity().getApplicationContext(), new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if(position==13) {
                            getActivity().startActivity(new Intent(getActivity().getApplicationContext(), UnityPlayerActivity.class));
                        }
                    }
                })
        );
        mApiService = ApiUtils.getAPIService();
        Call call = mApiService.requestSub(new NearbyRequest("English","24.594086", "73.729280"));
        swipeRefreshLayout.setRefreshing(true);
        call.enqueue(new Callback<SubAttractionsResponse>() {
            @Override
            public void onResponse(Call<SubAttractionsResponse> call, Response<SubAttractionsResponse> response) {

                System.out.println("Nearby response code"+response.body());
                if (response.isSuccessful()) {

                    try {

                        attractionList.addAll(response.body().getResp());
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
            public void onFailure(Call<SubAttractionsResponse> call, Throwable t) {
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
