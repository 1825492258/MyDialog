package com.item.dialog.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.item.dialog.R;

/**
 * Created by Jie on 2017/12/21.
 * 网络加载的布局
 * 加载中 一个进度条
 * 加载失败 点击时可以再次加载
 * 数据为空
 */

public class LoadLayout extends FrameLayout {

    public LoadLayout(@NonNull Context context) {
        this(context, null);
    }

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化View
     */
    private View loadingView;
    private View errorView;
    private View emptyView;

    private void initView() {
        // 1. 加载LoadingView
        loadingView = View.inflate(getContext(), R.layout.load_ing, null);
        addView(loadingView);
        // 2.添加加载失败的View
        errorView = View.inflate(getContext(), R.layout.load_error, null);
        addView(errorView);
        // 3.添加空白界面
        emptyView = View.inflate(getContext(), R.layout.load_enpty, null);
        addView(emptyView);
        // 一开始就隐藏所有的View
        //  hideAll();
    }

    /**
     * 全部都隐藏
     */
    public void hideAll() {
        // 设置各界面不可见，同时让它们不重新Layout
        loadingView.setVisibility(View.INVISIBLE);
        emptyView.setVisibility(INVISIBLE);
        errorView.setVisibility(INVISIBLE);
    }

    /**
     * 显示加载进度
     */
    public void showLoadingView() {
        hideAll();
        Log.d("jiejie","----show");
        loadingView.setVisibility(VISIBLE);
    }

    /**
     * 显示数据为空的界面
     */
    public void showEmptyView() {
        hideAll();
        emptyView.setVisibility(VISIBLE);
    }

    /**
     * 显示错误的界面
     */
    public void showErrorView() {
        hideAll();
        errorView.setVisibility(VISIBLE);
    }

    /**
     * 对错误页面进行点击
     */
    public void setErrorListener(View.OnClickListener listener) {
        if (listener != null && errorView != null) {
            hideAll();
            errorView.setOnClickListener(listener);
        }
    }
}
