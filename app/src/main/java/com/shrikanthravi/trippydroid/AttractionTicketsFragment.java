package com.shrikanthravi.trippydroid;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionTicketsFragment extends Fragment {


    public AttractionTicketsFragment() {
        // Required empty public constructor
    }

    int chcount=0,adcount=1;
    ImageView aplus,aminus,cplus,cminus;
    ImageView img1,img2;
    TextView atv,ctv,totalTV;
    int chtval=100,adval=200;
    int total=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attraction_tickets, container, false);
        aplus = view.findViewById(R.id.adultAddBtn);
        aminus = view.findViewById(R.id.adultMinusBtn);
        cplus = view.findViewById(R.id.ChAddBtn);
        cminus = view.findViewById(R.id.chMinusBtn);
        atv = view.findViewById(R.id.adultTV);
        ctv = view.findViewById(R.id.childTv);
        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        totalTV = view.findViewById(R.id.total);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/paypal.jpg").into(img1);
        Picasso.with(getActivity().getApplicationContext()).load("file:///android_asset/upi.jpeg").into(img2);

        aplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    adcount++;
                    total+=adval;
                    totalTV.setText(String.valueOf(total));
                    atv.setText(String.valueOf(adcount));



            }
        });
        aminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adcount>1){
                    adcount--;
                    total-=adval;
                    totalTV.setText(String.valueOf(total));
                    atv.setText(String.valueOf(adcount));
                }
            }
        });
        cplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chcount++;
                total+=chtval;
                totalTV.setText(String.valueOf(total));
                ctv.setText(String.valueOf(chcount));
            }
        });
        cminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chcount>=1){
                    chcount--;
                    total-=chtval;
                    totalTV.setText(String.valueOf(total));
                    ctv.setText(String.valueOf(chcount));
                }
            }
        });
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)view);

        return view;
    }

}
