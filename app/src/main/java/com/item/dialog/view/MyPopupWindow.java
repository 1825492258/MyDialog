package com.item.dialog.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.item.dialog.R;

import net.cachapa.expandablelayout.ExpandableLayout;

/**
 * Created by Jie on 2018/1/17.
 */

public class MyPopupWindow implements PopupWindow.OnDismissListener {

    private Activity context;
    private PopupWindow popupWindow;
    private ExpandableLayout mLayout;
    private View view;
    public MyPopupWindow(Activity context) {
        this.context = context;
        // 自定义一个View来显示内容
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_select_layout, null);
            popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT, true);
            mLayout = contentView.findViewById(R.id.expand_layout);
            view = contentView.findViewById(R.id.view);
        }

        // 如果不设置PopupWindow的背景，无论点击外部区域还是Back键都无法隐藏弹窗
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOnDismissListener(this);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDismiss();
            }
        });
    }

    public void show(View view) {
        if (popupWindow != null) {
            // showAsDropDown(View anchor)：相对某个控件的位置（正左下方），无偏移
            // showAsDropDown(View anchor, int xoff, int yoff)：相对某个控件的位置，有偏移
            // showAtLocation(View parent, int gravity, int x, int
            // y)：相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
            // popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.TOP, 0, 100);
            popupWindow.showAsDropDown(view);
            // 设置setFocusable(true)，要不然点击弹窗其他地方以及返回键，弹窗都不会退出
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
           if(!mLayout.isExpanded()){
               mLayout.expand();
           }
        }
    }

    @Override
    public void onDismiss() {
       // mLayout.collapse();
        popupWindow.dismiss();
        if(mLayout.isExpanded()){
            mLayout.collapse();
        }
    }
}
