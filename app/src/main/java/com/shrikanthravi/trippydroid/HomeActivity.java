package com.shrikanthravi.trippydroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.shrikanthravi.trippydroid.data.model.Attraction;
import com.shrikanthravi.trippydroid.data.model.NearbyRequest;
import com.shrikanthravi.trippydroid.data.model.NearbyResponse;
import com.shrikanthravi.trippydroid.data.model.remote.APIService;
import com.shrikanthravi.trippydroid.data.model.remote.ApiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    ImageView JaiputBtn,UdaipurBtn,JodhpurBtn,JailsalmerBtn;

    RecyclerView attractionRV;
    AttractionAdapter attractionAdapter;
    List<Attraction> attractionList;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;
    int refreshFlag=0;
    APIService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
        UdaipurBtn = (ImageView) findViewById(R.id.UdaipurBtn);
        JaiputBtn = (ImageView) findViewById(R.id.JaipurBtn);
        JailsalmerBtn = (ImageView) findViewById(R.id.JaisalmerBtn);
        JodhpurBtn = (ImageView) findViewById(R.id.JodhpurBtn);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.SwipeRefreshLayout);
        String lang = getIntent().getStringExtra("Lang");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        Picasso.with(getApplicationContext()).load("file:///android_asset/udaipur.jpg").into(UdaipurBtn);
        Picasso.with(getApplicationContext()).load("file:///android_asset/jaipur.jpeg").into(JaiputBtn);
        Picasso.with(getApplicationContext()).load("file:///android_asset/jaisalmer.jpg").into(JailsalmerBtn);
        Picasso.with(getApplicationContext()).load("file:///android_asset/jodhpur.jpg").into(JodhpurBtn);
        UdaipurBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(HomeActivity.this,CityActivity.class);
                startActivity(next);
            }
        });
        attractionRV = (RecyclerView) findViewById(R.id.NearbyAttractionsRV);
        attractionRV.setNestedScrollingEnabled(true);
        attractionList = new ArrayList<>();
        attractionAdapter = new AttractionAdapter(attractionList,getApplicationContext());
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false);
        attractionRV.setLayoutManager(verticalLayoutmanager);
        attractionRV.setAdapter(attractionAdapter);
        attractionRV.addOnItemTouchListener(
                new MyRecyclerItemClickListener(this, new MyRecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent next = new Intent(HomeActivity.this,AttractionActivity.class);
                        next.putExtra("AttractionName",attractionList.get(position).getName());
                        ArrayList<String> imgUrls = new ArrayList<>(attractionList.get(position).getImgurls());
                        next.putStringArrayListExtra("AttractionImgUrls",imgUrls);
                        next.putExtra("AudioUrl",attractionList.get(position).getAudiourl());
                        next.putExtra("Desc",attractionList.get(position).getDesc());
                        next.putExtra("lat",attractionList.get(position).getLat());
                        next.putExtra("lng",attractionList.get(position).getLng());
                        ArrayList<String> highslist = new ArrayList<>(attractionList.get(position).getHighslist());
                        next.putStringArrayListExtra("HighsList",highslist);
                        startActivity(next);
                    }
                })
        );
        /*attractionList.add(new Attraction("City Palace","file:///android_asset/udaipur.jpg"));
        attractionList.add(new Attraction("City Palace","file:///android_asset/udaipur.jpg"));
        attractionList.add(new Attraction("City Palace","file:///android_asset/udaipur.jpg"));
        attractionList.add(new Attraction("City Palace","file:///android_asset/udaipur.jpg"));*/

        mApiService = ApiUtils.getAPIService();
        Call call = mApiService.requestNearby(new NearbyRequest(lang,"24.594086", "73.729280","1"));
        swipeRefreshLayout.setRefreshing(true);
        call.enqueue(new Callback<NearbyResponse>() {
            @Override
            public void onResponse(Call<NearbyResponse> call, Response<NearbyResponse> response) {

                System.out.println("Nearby response code"+response.code());
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,QrScanActivity.class));
            }
        });

    }
}
