package com.item.dialog.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.item.dialog.R;
import com.item.dialog.service.UpdateService;

public class TwoActivity extends AppCompatActivity implements View.OnClickListener {

    private AnimationDrawable anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        findViewById(R.id.btn_up).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        ImageView mLoadingView = findViewById(R.id.loading_view);
        anim = (AnimationDrawable) mLoadingView.getDrawable();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_up: // 版本更新
                Intent intent = new Intent(this, UpdateService.class);
                startService(intent);
                break;
            case R.id.btn_start: // 开启下方的动画
                 if(anim != null){
                     anim.stop();
                 }
                break;
        }
    }
}
