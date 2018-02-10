package com.shrikanthravi.trippydroid;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shrikanthravi.trippydroid.data.model.Attraction;
import com.shrikanthravi.trippydroid.data.model.Resp;
import com.shrikanthravi.trippydroid.data.model.SubAttractionsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shrikanthravi on 03/12/17.
 */

public class AttractionAdapter1 extends RecyclerView.Adapter<AttractionAdapter1.MyViewHolder> {
    private List<Resp> attractionList;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView attractionName;
        public ImageView attractionImage;

        public MyViewHolder(View view) {
            super(view);
            attractionName = (TextView) view.findViewById(R.id.AttractionName);
            attractionImage = (ImageView) view.findViewById(R.id.AttractionImage);

        }
    }


    public AttractionAdapter1(List<Resp> verticalList, Context context) {
        this.attractionList = verticalList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.attraction_row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/product_san_regular.ttf");
        holder.attractionName.setText(attractionList.get(position).getName().toString());
        holder.attractionName.setTypeface(font);
        Picasso.with(context).load(attractionList.get(position).getImurls().get(0)).into(holder.attractionImage);

    }

    @Override
    public int getItemCount() {
        return attractionList.size();
    }
}


