package com.example.conquermobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class Guide extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    private ImageView[] dots;
    private int[] ids = { R.id.iv1, R.id.iv2, R.id.iv3 };
    private Button start_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initViews();
        initDots();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.one, null));
        views.add(inflater.inflate(R.layout.two, null));
        views.add(inflater.inflate(R.layout.three, null));

        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setAdapter(vpAdapter);
        start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Guide.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        vp.setOnPageChangeListener(this);
    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {}

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {}

    @Override
    public void onPageSelected(int arg0) {
        for (int i = 0; i < ids.length; i++) {
            if (arg0 == i) {
                dots[i].setImageResource(R.drawable.login_point_selected);
            } else {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

}
