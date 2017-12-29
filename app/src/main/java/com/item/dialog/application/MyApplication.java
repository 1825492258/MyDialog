package com.item.dialog.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Jie on 2017/12/21.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
