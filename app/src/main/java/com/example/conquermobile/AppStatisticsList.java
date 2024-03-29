package com.example.conquermobile;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppStatisticsList extends AppCompatActivity {
    private int style;
    private long totalTime;
    private int totalTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_statistics_list);
        this.style = StatisticsInfo.DAY;

        Button buttonday = (Button) findViewById(R.id.daybuttonlist);
        buttonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.DAY) {
                    style = StatisticsInfo.DAY;
                    onResume();
                }
            }
        });
        Button buttonweek = (Button) findViewById(R.id.weekbuttonlist);
        buttonweek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.WEEK) {
                    style = StatisticsInfo.WEEK;
                    onResume();
                }
            }
        });
        Button buttonmonth = (Button) findViewById(R.id.monthbuttonlist);
        buttonmonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.MONTH) {
                    style = StatisticsInfo.MONTH;
                    onResume();
                }
            }
        });
        Button buttonyear = (Button) findViewById(R.id.yearbuttonlist);
        buttonyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(style != StatisticsInfo.YEAR) {
                    style = StatisticsInfo.YEAR;
                    onResume();
                }
            }
        });
    }

    private void SetButtonColor() {
        Button buttonday = (Button) findViewById(R.id.daybuttonlist);
        Button buttonmonth = (Button) findViewById(R.id.monthbuttonlist);
        Button buttonyear = (Button) findViewById(R.id.yearbuttonlist);
        Button buttonweek = (Button) findViewById(R.id.weekbuttonlist);

        buttonday.setTextColor(Color.WHITE);
        buttonmonth.setTextColor(Color.WHITE);
        buttonweek.setTextColor(Color.WHITE);
        buttonyear.setTextColor(Color.WHITE);

        switch (style) {
            case StatisticsInfo.DAY:
                buttonday.setTextColor(Color.GREEN);
                break;
            case StatisticsInfo.MONTH:
                buttonmonth.setTextColor(Color.GREEN);
                break;
            case StatisticsInfo.WEEK:
                buttonweek.setTextColor(Color.GREEN);
                break;
            case StatisticsInfo.YEAR:
                buttonyear.setTextColor(Color.GREEN);
                break;
        }
    }

    //每次重新进入界面的时候加载listView
    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        SetButtonColor();

        List<Map<String,Object>> datalist = null;

        StatisticsInfo statisticsInfo = new StatisticsInfo(this,this.style);
        totalTime = statisticsInfo.getTotalTime();
        totalTimes = statisticsInfo.getTotalTimes();
        datalist = getDataList(statisticsInfo.getShowList());

        ListView listView = (ListView)findViewById(R.id.AppStatisticsList);
        SimpleAdapter adapter = new SimpleAdapter(this,datalist,R.layout.inner_list,
                new String[]{"label","info","times","icon"},
                new int[]{R.id.label,R.id.info,R.id.times,R.id.icon});
        listView.setAdapter(adapter);

        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object o, String s) {
                if(view instanceof ImageView && o instanceof Drawable){

                    ImageView iv=(ImageView)view;
                    iv.setImageDrawable((Drawable)o);
                    return true;
                }
                else return false;
            }
        });
    }

    private List<Map<String,Object>> getDataList(ArrayList<AppInformation> ShowList) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("label","全部应用");
        map.put("info","运行时间: " + DateUtils.formatElapsedTime(totalTime / 1000));
        map.put("times","本次开机操作次数: " + totalTimes);
        map.put("icon",R.drawable.use);
        dataList.add(map);
        Log.d("HQS", "appInformation "+ShowList.size());
        for(AppInformation appInformation : ShowList) {
            map = new HashMap<>();
            map.put("label",appInformation.getLabel());
            map.put("info","运行时间: " + DateUtils.formatElapsedTime(appInformation.getUsedTimebyDay() / 1000));
            map.put("times","本次开机操作次数: " + appInformation.getTimes());
            map.put("icon",appInformation.getIcon());
            dataList.add(map);
        }

        return dataList;
    }

}
