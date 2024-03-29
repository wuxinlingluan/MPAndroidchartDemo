package com.example.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.util.ChartUtils;

public class MainActivity extends AppCompatActivity {

    private DynamicLineChartManager dynamicLineChartManager1;
    private DynamicLineChartManager dynamicLineChartManager2;
    private List<Integer> list = new ArrayList<>(); //数据集合
    private List<String> names = new ArrayList<>(); //折线名字集合
    private List<Integer> lablenames = new ArrayList<>(); //折线名字集合
    private List<Integer> colour = new ArrayList<>();//折线颜色集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // LineChart mChart1 = (LineChart) findViewById(R.id.dynamic_chart1);
        final LineChart mChart2 = (LineChart) findViewById(R.id.dynamic_chart2);
        //折线名字
        names.add("温度");
        names.add("压强");
        names.add("其他");
        //折线颜色
        colour.add(Color.CYAN);
        colour.add(Color.GREEN);
        colour.add(Color.BLUE);

     //   dynamicLineChartManager1 = new DynamicLineChartManager(mChart1, names.get(0), colour.get(0));

        dynamicLineChartManager2 = new DynamicLineChartManager(mChart2, names, colour);
        //死循环添加数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            //   dynamicLineChartManager1.setYAxis(100, 0, 10);

                            list.add((int) (Math.random() * 50) + 10);
                            list.add((int) (Math.random() * 80) + 10);
                            list.add((int) (Math.random() * 100));
                            lablenames.add((int) (Math.random() * 100));
                            dynamicLineChartManager2.setYAxis(100, 0, 10,lablenames);
                            dynamicLineChartManager2.addEntry(list);
                            list.clear();
                        }
                    });
                }
            }
        }).start();
    }

}
