package com.shrikanthravi.trippydroid;


import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.taishi.library.Indicator;

import java.util.ArrayList;

import at.blogc.android.views.ExpandableTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionAboutFragment extends Fragment {


    public AttractionAboutFragment() {
        // Required empty public constructor
    }
    int audioFlag=0;
    MediaPlayer mediaplayer;
    ImageView AP1,AP2,AP3,AP4,map,playIcon;
    TextView playPauseTV;
    ExpandableTextView desc;
    Indicator musicIndiactor;
    RelativeLayout playPauseRL;
    ArrayList<TextView> highTvList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attraction_about, container, false);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)view);
        map = (ImageView) view.findViewById(R.id.map);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/map.jpg").into(map);
        AP1 = (ImageView) view.findViewById(R.id.AttractionPhoto1);
        AP2 = (ImageView) view.findViewById(R.id.AttractionPhoto2);
        AP3 = (ImageView) view.findViewById(R.id.AttractionPhoto3);
        AP4 = (ImageView) view.findViewById(R.id.AttractionPhoto4);
        highTvList = new ArrayList<>();
        highTvList.add((TextView) view.findViewById(R.id.high1));
        highTvList.add((TextView) view.findViewById(R.id.high2));
        highTvList.add((TextView) view.findViewById(R.id.high3));
        highTvList.add((TextView) view.findViewById(R.id.high4));
        highTvList.add((TextView) view.findViewById(R.id.high5));
        highTvList.add((TextView) view.findViewById(R.id.high6));
        highTvList.add((TextView) view.findViewById(R.id.high7));
        highTvList.add((TextView) view.findViewById(R.id.high8));
        highTvList.add((TextView) view.findViewById(R.id.high9));
        for(int i=0;i<9;i++){
            highTvList.get(i).setText(AttractionActivity.HighsList.get(i));
        }
        playIcon = (ImageView) view.findViewById(R.id.playIcon);
        desc = (ExpandableTextView) view.findViewById(R.id.AttractionDesc);
        playPauseTV = (TextView) view.findViewById(R.id.playPauseTextView);
        musicIndiactor = (Indicator) view.findViewById(R.id.MusicIndicator);
        playPauseRL = (RelativeLayout) view.findViewById(R.id.PlayPauseRelativeLayout);
        mediaplayer = new MediaPlayer();
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaplayer.setDataSource(AttractionActivity.AudioUrl);
            mediaplayer.prepare();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        playPauseRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(audioFlag==0){
                    musicIndiactor.setVisibility(View.VISIBLE);
                    playIcon.setVisibility(View.GONE);
                    playPauseTV.setText("Pause");
                    mediaplayer.start();
                    audioFlag=1;
                }
                else{

                    musicIndiactor.setVisibility(View.GONE);
                    playIcon.setVisibility(View.VISIBLE);
                    playPauseTV.setText("Play");
                    mediaplayer.pause();
                    audioFlag=0;
                }


            }
        });
        Picasso.with(getActivity().getApplicationContext()).load(AttractionActivity.ImgUrls.get(0)).into(AP1);
        Picasso.with(getActivity().getApplicationContext()).load(AttractionActivity.ImgUrls.get(1)).into(AP2);
        Picasso.with(getActivity().getApplicationContext()).load(AttractionActivity.ImgUrls.get(2)).into(AP3);
        Picasso.with(getActivity().getApplicationContext()).load(AttractionActivity.ImgUrls.get(3)).into(AP4);
        desc.setText(AttractionActivity.Desc);
        desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc.toggle();
            }
        });
        return view;
    }

}
