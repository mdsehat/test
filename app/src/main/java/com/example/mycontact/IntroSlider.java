package com.example.mycontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IntroSlider extends AppCompatActivity {
    SharePref pref;
    ViewPager viewPager;
    Button btnNext,btnSkip;
    LinearLayout Dots;
    List<Introslider_Fragment> slider = new ArrayList<>();
    Introslider_Fragment fragment = new Introslider_Fragment();
    int[] backgroundId = {Color.BLUE,Color.GREEN,Color.RED};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        getSupportActionBar().hide();
        statusBar();
        viewPager = findViewById(R.id.viewPager);
        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);
        Dots = findViewById(R.id.linearDot);
        pref = new SharePref(this);
        if (!pref.StartSlider()){
            LunchHome();
            finish();
        }
        initFragment();
        startAdpter();
        createDot(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1){
                    btnNext.setText("got it");
                    btnSkip.setVisibility(View.GONE);
                    LunchHome();
                    pref.setStart(false);
                }else{
                    btnNext.setText("next");
                    btnSkip.setVisibility(View.VISIBLE);
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LunchHome();
                pref.setStart(false);
            }
        });
    }

    private void initFragment() {
        if (slider == null) slider = new ArrayList<>();
        else slider.clear();
        for (int i=0;i<backgroundId.length;i++){
            slider.add(fragment.initFrag(backgroundId[i]));
        }
    }
    private void startAdpter() {
        sliderAdpter adpter = new sliderAdpter(getSupportFragmentManager());
        viewPager.setAdapter(adpter);
    }

    private void LunchHome() {
        startActivity(new Intent(this,MainActivity.class));
    }
    private class sliderAdpter extends FragmentPagerAdapter{

        public sliderAdpter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return slider.get(position);
        }

        @Override
        public int getCount() {
            return backgroundId.length;
        }
    }
    private void statusBar() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void createDot(int position){
        TextView []dot = new TextView[viewPager.getAdapter().getCount()];
        Dots.removeAllViews();
        for (int i=0;i<viewPager.getAdapter().getCount();i++){
            dot[i] = new TextView(this);
            dot[i].setText(Html.fromHtml("&#8226;"));
            dot[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
            if(i == position) dot[i].setTextColor(Color.WHITE);
            else dot[i].setTextColor(Color.GRAY);
            Dots.addView(dot[i]);
        }
    }
}