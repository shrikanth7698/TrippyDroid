package com.shrikanthravi.trippydroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.NO_ID;

public class AttractionActivity extends AppCompatActivity {

    public static String Desc;
    public static String AudioUrl;
    public static ArrayList<String> ImgUrls;
    public static ArrayList<String> HighsList;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_attraction);


        Intent myIntent = getIntent();
        String AttractionName = myIntent.getStringExtra("AttractionName");
        Desc = myIntent.getStringExtra("Desc");
        ImgUrls = myIntent.getStringArrayListExtra("AttractionImgUrls");
        AudioUrl = myIntent.getStringExtra("AudioUrl");
        HighsList = myIntent.getStringArrayListExtra("HighsList");
        TextView AttractionTV = (TextView) findViewById(R.id.AttractionName);
        AttractionTV.setText(AttractionName);
        ImageView AttractionImage = (ImageView) findViewById(R.id.AttractionBgImage);
        Picasso.with(getApplicationContext()).load(ImgUrls.get(0).toString()).into(AttractionImage);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/product_san_regular.ttf");
        FontChanger fontChanger = new FontChanger(font);
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AttractionAboutFragment(), "About");
        adapter.addFragment(new AttractionDiscoverFragment(), "Discover");
        adapter.addFragment(new AttractionTicketsFragment(),"Tickets");

        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        SimpleTabProvider tabProvider = new SimpleTabProvider(this,R.layout.custom_tab,R.id.CustomTabText,R.id.CustomTabImage);
        viewPagerTab.setCustomTabView(tabProvider);
        viewPagerTab.setViewPager(viewPager);
    }

    private static class SimpleTabProvider implements SmartTabLayout.TabProvider {

        private final LayoutInflater inflater;
        private final int tabViewLayoutId;
        private final int tabViewTextViewId;
        private final int tabViewImageViewId;
        private final Typeface font;
        private SimpleTabProvider(Context context, int layoutResId, int textViewId, int ImageViewId) {
            inflater = LayoutInflater.from(context);
            tabViewLayoutId = layoutResId;
            tabViewTextViewId = textViewId;
            tabViewImageViewId = ImageViewId;
            font = Typeface.createFromAsset(context.getAssets(), "fonts/product_san_regular.ttf");
        }

        @Override
        public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
            View tabView = null;
            TextView tabTitleView = null;
            ImageView tabImageView = null;
            if (tabViewLayoutId != NO_ID) {
                tabView = inflater.inflate(tabViewLayoutId, container, false);
            }

            if (tabViewTextViewId != NO_ID && tabView != null && tabViewImageViewId != NO_ID) {
                tabTitleView = (TextView) tabView.findViewById(tabViewTextViewId);
                tabImageView = (ImageView) tabView.findViewById(tabViewImageViewId);
            }

            if (tabTitleView == null && TextView.class.isInstance(tabView) && ImageView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
                tabImageView = (ImageView) tabView;
            }

            if (tabTitleView != null && tabImageView != null) {
                tabTitleView.setText(adapter.getPageTitle(position));
                tabTitleView.setTypeface(font);
                switch (adapter.getPageTitle(position).toString()){
                    case "About":
                        tabImageView.setBackgroundResource(R.drawable.explore);
                        break;
                    case "Discover":
                        tabImageView.setBackgroundResource(R.drawable.locations);
                        break;
                    case "Tickets":
                        tabImageView.setBackgroundResource(R.drawable.ticket);
                    default:
                        break;
                }
            }

            return tabView;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
