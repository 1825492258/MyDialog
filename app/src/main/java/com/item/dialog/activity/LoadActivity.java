package com.item.dialog.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.item.dialog.R;
import com.item.dialog.view.LoadLayout;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoadActivity extends AppCompatActivity {
    private LoadLayout mLoadView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mLoadView = findViewById(R.id.loadLayout);
        textView = findViewById(R.id.textView);
        mLoadView.setErrorListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHttp();
            }
        });
        setHttp();
    }

    private void setHttp() {
        mLoadView.showLoadingView();
        RequestParams params = new RequestParams("https://www.baidu.com/s");
        // params.setSslSocketFactory(...); // 设置ssl
       // params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //  Toast.makeText(x.app(), result, Toast.LENGTH_LONG).show();
                Log.d("jiejie", "onSuccess " + result);
                mLoadView.hideAll();
                textView.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //   Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("jiejie","onError" + isOnCallback  + ex);
                mLoadView.showErrorView();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                // Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinished() {

            }
        });
    }

}
