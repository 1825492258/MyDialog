package com.item.dialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.item.dialog.activity.LoadActivity;
import com.item.dialog.activity.TwoActivity;
import com.item.dialog.view.DialogLogin;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                startActivity(new Intent(this, LoadActivity.class));
                break;
            case R.id.btn_two:
                startActivity(new Intent(this, TwoActivity.class));
                break;
            case R.id.btn_three:
                initMyDialog();
                break;
        }
    }

   private DialogLogin mDialog;

    // 展示弹窗
    private void initMyDialog() {
        if (mDialog == null) {
            mDialog = new DialogLogin(this);
            mDialog.setTitle("我是标题");
            mDialog.setMessage("网络无法访问，请检查网络连接");
            mDialog.setCancelText("取消");
            mDialog.setSureText("去设置");
            mDialog.setDialogEnsure(new DialogLogin.IDialogEnsure() {
                @Override
                public void onEnsure() {

                }

                @Override
                public void onCancel() {

                }
            });
        }
        mDialog.show();
    }
}
