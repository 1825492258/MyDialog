package com.antonioleiva.mvpexample.app.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestActivity extends Activity {
    private DropDownMenu test;
    private String headers[] = {"选择时间点", "选择时间段"};
    private List<View> popupViews = new ArrayList<>();
    private ListDropDownAdapter oneAdapter;
    private ListDropDownAdapter twoAdapter;
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        test = (DropDownMenu) findViewById(R.id.test);
        initView();
    }

    private void initView() {
        final ListView oneView = new ListView(this);
        oneAdapter = new ListDropDownAdapter(this, Arrays.asList(citys));
        oneView.setDividerHeight(0);
        oneView.setAdapter(oneAdapter);

        final ListView twoView = new ListView(this);
        twoAdapter = new ListDropDownAdapter(this, Arrays.asList(ages));
        twoView.setDividerHeight(0);
        twoView.setAdapter(twoAdapter);
        oneView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                oneAdapter.setCheckItem(position);
                test.setTabText(position == 0 ? headers[0] : citys[position]);
                test.closeMenu();
            }
        });
        twoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                twoAdapter.setCheckItem(position);
                test.setTabText(position == 0 ? headers[1] : ages[position]);
                test.closeMenu();
            }
        });
        popupViews.add(oneView);
        popupViews.add(twoView);
        //init context view
        TextView contentView = new TextView(this);
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //contentView.setText("内容显示区域");
        //contentView.setGravity(Gravity.CENTER);
        // contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        // init dropdownview
        test.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }
}
