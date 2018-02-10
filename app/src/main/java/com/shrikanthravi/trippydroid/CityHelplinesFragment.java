package com.shrikanthravi.trippydroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CityHelplinesFragment extends Fragment {

    public CityHelplinesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_city_helplines, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)view);
        CardView Fire,Police,Office,Railways;
        Fire = view.findViewById(R.id.FireCard);
        Police = view.findViewById(R.id.PoliceCard);
        Office = view.findViewById(R.id.TouristOfficeCard);
        Railways = view.findViewById(R.id.RailwayCard);
        Fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "101";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_CALL, call);
                startActivity(surf);
            }
        });
        Police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "100";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_CALL, call);
                startActivity(surf);
            }
        });
        Office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "02942411535";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_CALL, call);
                startActivity(surf);
            }
        });
        Railways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "131";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_CALL, call);
                startActivity(surf);
            }
        });
        return view;
    }





}
