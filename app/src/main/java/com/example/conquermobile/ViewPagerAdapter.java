package com.example.conquermobile;


import android.content.Context;
import android.view.View;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views;
    private Context context;

    public ViewPagerAdapter(List<View> views, Context context) {

        this.views = views;
        this.context = context;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {

        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {

        ((ViewPager) container).addView(views.get(position));

        return views.get(position);

    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

}
