package com.item.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.item.dialog.R;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * Created by Jie on 2018/1/17.
 * SelectDialog
 */

public class SelectDialog extends Dialog {

    private DisplayMetrics dm; // 屏幕分辨率
    private Context context;
    private ExpandableLayout mLayout;
    public SelectDialog(@NonNull Context context) {
        super(context, R.style.top_dialog);
        this.context = context;
        dm = context.getResources().getDisplayMetrics();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_layout);
        // 设置Window样式
        mLayout = findViewById(R.id.expand_layout);
        if(!mLayout.isExpanded()){
            mLayout.expand();
        }
        Window dialogWindow = getWindow();
        assert dialogWindow != null;
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams params = dialogWindow.getAttributes();
        params.width = dm.widthPixels;
        params.height = dip2px(context, 288);
        params.y = 100;
        dialogWindow.setAttributes(params);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mLayout.isExpanded()){
            mLayout.collapse();
        }
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
