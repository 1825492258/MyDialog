package com.item.dialog.bittom;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.item.dialog.R;

/**
 * Created by Jie on 2018/1/16.
 * BottomDialog
 */

public class BottomDialog extends Dialog {

    private Selector selector;

    public BottomDialog(@NonNull Context context) {
        super(context, R.style.bottom_dialog);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public void init(Context context, Selector selector) {
        this.selector = selector;
        setContentView(selector.getView());
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = dip2px(context, 256);
        window.setAttributes(params);

        window.setGravity(Gravity.BOTTOM);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static BottomDialog show(Context context, Selector.SelectedListener listener) {
        BottomDialog dialog = new BottomDialog(context, R.style.bottom_dialog);
        dialog.selector.setSelectedListener(listener);
        dialog.show();

        return dialog;
    }
}
