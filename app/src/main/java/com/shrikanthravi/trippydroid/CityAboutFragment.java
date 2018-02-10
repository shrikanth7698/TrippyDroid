package com.shrikanthravi.trippydroid;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import at.blogc.android.views.ExpandableTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityAboutFragment extends Fragment{

    ImageView map;
    public CityAboutFragment() {
        // Required empty public constructor
    }

    ImageView cp1,cp2,cp3,cp4;
    ExpandableTextView cityDescTV;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_about, container, false);

        cityDescTV = (ExpandableTextView) view.findViewById(R.id.CityDesc);
        map = (ImageView) view.findViewById(R.id.map);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/map.jpg").into(map);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        //cityDescTV.setTypeface(font);
        fontChanger.replaceFonts((ViewGroup)view);
        cityDescTV.setCollapseInterpolator(new AccelerateDecelerateInterpolator());
        cityDescTV.setExpandInterpolator(new AccelerateDecelerateInterpolator());

        cityDescTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityDescTV.toggle();
            }
        });

        cp1 = (ImageView) view.findViewById(R.id.CityPhoto1);
        cp2 = (ImageView) view.findViewById(R.id.CityPhoto2);
        cp3 = (ImageView) view.findViewById(R.id.CityPhoto3);
        cp4 = (ImageView) view.findViewById(R.id.CityPhoto4);

        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/udaipur1.jpg").into(cp1);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/udaipur2.jpg").into(cp2);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/udaipur3.jpeg").into(cp3);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/udaipur4.jpg").into(cp4);
        return view;
    }

}
