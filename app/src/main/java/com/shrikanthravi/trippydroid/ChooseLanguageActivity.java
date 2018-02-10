package com.shrikanthravi.trippydroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ChooseLanguageActivity extends AppCompatActivity {

    int enFlag = 0,hiFlag=0,spFlag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_choose_language);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));
        ImageView bgImage = (ImageView) findViewById(R.id.ChooseLanguageBackgroundImageView);
        ImageView check = (ImageView) findViewById(R.id.checkImageView);
        //Picasso.with(getApplicationContext()).load("file:///android_asset/rajasthan_background_image.jpg").into(bgImage);
        final Button en,hi,sp;
        en  = (Button) findViewById(R.id.EnglishButton);
        hi = (Button) findViewById(R.id.HindiButton);
        sp = (Button) findViewById(R.id.SpanishButton);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enFlag=1;
                hiFlag=0;
                spFlag=0;
                en.setBackground(getResources().getDrawable(R.drawable.selected_background));
                hi.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
                sp.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
            }
        });

        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enFlag=0;
                hiFlag=1;
                spFlag=0;
                en.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
                hi.setBackground(getResources().getDrawable(R.drawable.selected_background));
                sp.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
            }
        });

        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enFlag=0;
                hiFlag=0;
                spFlag=1;
                en.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
                hi.setBackground(getResources().getDrawable(R.drawable.rounded_corners));
                sp.setBackground(getResources().getDrawable(R.drawable.selected_background));
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enFlag==1){
                    Intent next = new Intent(ChooseLanguageActivity.this,OtpActivity.class);
                    next.putExtra("Lang","english");
                    startActivity(next);
                }
                else{
                    if(hiFlag==1){

                        Intent next = new Intent(ChooseLanguageActivity.this,OtpActivity.class);
                        next.putExtra("Lang","hindi");
                        startActivity(next);
                    }
                    else{
                        if(spFlag==1){

                            Intent next = new Intent(ChooseLanguageActivity.this,OtpActivity.class);
                            next.putExtra("Lang","spanish");
                            startActivity(next);
                        }
                    }
                }
            }
        });

    }
}
