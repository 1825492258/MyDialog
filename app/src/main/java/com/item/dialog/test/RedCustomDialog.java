package com.item.dialog.test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.item.dialog.R;

/**
 * Created by Jie on 2018/1/22.
 * 抢红包的Dialog
 */

public class RedCustomDialog extends Dialog implements View.OnClickListener {

    private ImageView mIvCloset;
    private ImageView mIvAcatar;
    private TextView mTvName;
    private TextView mTvMsg;
    private ImageView mIvOpen;
    private Context context;
    private OnRedPackerListener mListener;

    public void setOnRedPackerListener(OnRedPackerListener mListener) {
        this.mListener = mListener;
    }

    private int[] mImgResIds = new int[]{
            R.mipmap.icon_open_red_packet1,
            R.mipmap.icon_open_red_packet2,
            R.mipmap.icon_open_red_packet3,
            R.mipmap.icon_open_red_packet4,
            R.mipmap.icon_open_red_packet5,
            R.mipmap.icon_open_red_packet6,
            R.mipmap.icon_open_red_packet7,
            R.mipmap.icon_open_red_packet7,
            R.mipmap.icon_open_red_packet8,
            R.mipmap.icon_open_red_packet9,
            R.mipmap.icon_open_red_packet4,
            R.mipmap.icon_open_red_packet10,
            R.mipmap.icon_open_red_packet11,
    };

    private FrameAnimation mFrameAnimation;

    public RedCustomDialog(@NonNull Context context) {
        super(context, R.style.custom_dialogs);
        this.context = context;
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_red_packet);
        initView();
    }

    private void initView() {
        mIvCloset = findViewById(R.id.iv_close);
        mIvAcatar = findViewById(R.id.iv_avatar);
        mTvName = findViewById(R.id.tv_name);
        mTvMsg = findViewById(R.id.tv_msg);
        mIvOpen = findViewById(R.id.iv_open);
        mIvCloset.setOnClickListener(this);
        mIvOpen.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close: // 关闭
                stopAnim();
                if (mListener != null) {
                    mListener.onCloseClick();
                }
                break;
            case R.id.iv_open: // 打开红包
                if (mFrameAnimation != null) {
                    // 吐过正在转动，则直接返回
                    return;
                }
                startAnim();
                if (mListener != null) {
                    mListener.onOpenClick();
                }
                break;
        }
    }

    private void startAnim() {
        mFrameAnimation = new FrameAnimation(mIvOpen, mImgResIds, 125, true);
        mFrameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                Log.i("", "start");
            }

            @Override
            public void onAnimationEnd() {
                Log.i("", "end");
            }

            @Override
            public void onAnimationRepeat() {
                Log.i("", "repeat");
            }

            @Override
            public void onAnimationPause() {
                mIvOpen.setBackgroundResource(R.mipmap.icon_open_red_packet1);
            }
        });
    }

    private void stopAnim() {
        if (mFrameAnimation != null) {
            mFrameAnimation.release();
            mFrameAnimation = null;
        }
    }

    public interface OnRedPackerListener {
        /**
         * 点击了关闭按钮
         */
        void onCloseClick();

        /**
         * 点击了打开红包的按钮
         */
        void onOpenClick();
    }
}
