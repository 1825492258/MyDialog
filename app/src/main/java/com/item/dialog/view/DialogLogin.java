package com.item.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.item.dialog.R;


/**
 * Created by Jie on 2017/12/25.
 * 弹窗
 */

public class DialogLogin extends Dialog implements View.OnClickListener {
    // @BindView(R.id.item_title)
    private TextView mTitle;
    //@BindView(R.id.item_msg)
    private TextView mMessage;
    // @BindView(R.id.item_cancel)
    private TextView mCancel;
    //  @BindView(R.id.item_sure)
    private TextView mEnsure;
    private String title; // 弹窗标题
    private String message; // 标题信息
    private String cancelText; // 左侧文字 取消
    private String sureText; // 右侧文字 确定
    private IDialogEnsure mDialogEnsure;

    public DialogLogin(@NonNull Context context) {
        super(context, R.style.SheetDialogStyle);
        setCanceledOnTouchOutside(false); // 点击外部弹窗不消失
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        mTitle = findViewById(R.id.item_title);
        mMessage = findViewById(R.id.item_msg);
        mCancel = findViewById(R.id.item_cancel);
        mEnsure = findViewById(R.id.item_sure);
        mTitle.setText(getTitle());
        mMessage.setText(getMessage());
        mCancel.setText(getCancelText());
        mEnsure.setText(getSureText());
        mCancel.setOnClickListener(this);
        mEnsure.setOnClickListener(this);
    }

    public String getCancelText() {
        return cancelText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public String getSureText() {
        return sureText;
    }

    public void setSureText(String sureText) {
        this.sureText = sureText;
    }

    private String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDialogEnsure(IDialogEnsure mDialogEnsure) {
        this.mDialogEnsure = mDialogEnsure;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_cancel:
                if (mDialogEnsure != null) {
                    mDialogEnsure.onCancel();
                    dismiss();
                }
                break;
            case R.id.item_sure:
                if (mDialogEnsure != null) {
                    mDialogEnsure.onEnsure();
                    dismiss();
                }
                break;
        }
    }

    /**
     * 定义一个接口，点击确定时进行回调
     */
    public interface IDialogEnsure {
        void onEnsure(); // 确定

        void onCancel(); // 取消
    }
}
