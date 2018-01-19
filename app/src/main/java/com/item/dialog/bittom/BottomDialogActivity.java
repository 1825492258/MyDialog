package com.item.dialog.bittom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.item.dialog.R;
import com.item.dialog.view.MyPopupWindow;
import com.item.dialog.view.SelectDialog;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Random;

public class BottomDialogActivity extends AppCompatActivity {
    private ExpandableLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_dialog);
        mLayout = findViewById(R.id.text_expand);
        if(mLayout.isExpanded()){
            mLayout.collapse();
        }else {
            mLayout.expand(true);
        }
        // 点击的弹窗
        findViewById(R.id.btn_bottom_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });

        findViewById(R.id.btn_bottom_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTopDialog();
            }
        });
        findViewById(R.id.btn_bottom_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPopupWindow p = new MyPopupWindow(BottomDialogActivity.this);
                p.show(view);
            }
        });
    }

    private void showPop() {

    }

    private void showTopDialog() {
        SelectDialog dialog = new SelectDialog(this);
        dialog.show();
    }

    private void showDialog() {
        Selector selector = new Selector(this, 4);

        selector.setDataProvider(new DataProvider() {
            @Override
            public void provideData(int currentDeep, int preId, DataReceiver receiver) {
                //根据tab的深度和前一项选择的id，获取下一级菜单项
                Log.i("jiejie", "provideData: currentDeep >>> " + currentDeep + " preId >>> " + preId);
                receiver.send(getData());
            }
        });
        selector.setSelectedListener(new Selector.SelectedListener() {
            @Override
            public void onAddressSelected(ArrayList<ISelectAble> selectAbles) {
                String result = "";
                for (ISelectAble selectAble : selectAbles) {
                    result += selectAble.getName() + " ";
                }
                //Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        });

        BottomDialog dialog = new BottomDialog(this);
        dialog.init(this, selector);
        dialog.show();
    }

    private ArrayList<ISelectAble> getData() {
        ArrayList<ISelectAble> data = new ArrayList<>();
        int count = new Random().nextInt(15) + 1;
        for (int j = 0; j < 5; j++) {
            final int finalJ = j;
            data.add(new ISelectAble() {
                @Override
                public String getName() {
                    return "随机" + finalJ;
                }

                @Override
                public int getId() {
                    return finalJ;
                }

                @Override
                public Object getArg() {
                    return this;
                }
            });
        }
        return data;
    }
}
