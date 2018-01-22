package com.item.dialog.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.item.dialog.R;

public class CustomActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        imageView = findViewById(R.id.image_show);
        findViewById(R.id.showDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRedPackerDialog();
            }
        });

        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnim();
            }
        });
    }

    /**
     * 展示红包的弹窗
     */
    private RedCustomDialog mDialog;

    private void showRedPackerDialog() {
        if (mDialog == null) {
            mDialog = new RedCustomDialog(this);
        }
        mDialog.setOnRedPackerListener(new RedCustomDialog.OnRedPackerListener() {
            @Override
            public void onCloseClick() {
                mDialog.dismiss();
            }

            @Override
            public void onOpenClick() {
                // 领取红包的接口
            }
        });
        mDialog.show();
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
    FrameAnimation mAnimation;

    private void startAnim() {
        mAnimation = new FrameAnimation(imageView, mImgResIds, 125, true);
        mAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
            @Override
            public void onAnimationStart() {
                Log.d("jiejie", "--------start");
            }

            @Override
            public void onAnimationEnd() {
                Log.d("jiejie", "--------end");
            }

            @Override
            public void onAnimationRepeat() {
                Log.d("jiejie", "---------repeat");
            }

            @Override
            public void onAnimationPause() {
                Log.d("jiejie", "----------onPause");
                imageView.setBackgroundResource(R.mipmap.icon_open_red_packet1);
            }
        });

    }
}
