# MyDialog
Dialog Demo

## 加载布局封装
### 在网络请求的时候先展示加载界面，如果请求失败展示失败界面并点击可以再次加载
    public class LoadLayout extends FrameLayout {

        public LoadLayout(@NonNull Context context) {
            this(context, null);
        }

        public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        /**
         * 初始化View
         */
        private View loadingView;
        private View errorView;
        private View emptyView;

        private void initView() {
            // 1. 加载LoadingView
            loadingView = View.inflate(getContext(), R.layout.load_ing, null);
            addView(loadingView);
            // 2.添加加载失败的View
            errorView = View.inflate(getContext(), R.layout.load_error, null);
            addView(errorView);
            // 3.添加空白界面
            emptyView = View.inflate(getContext(), R.layout.load_enpty, null);
            addView(emptyView);
            // 一开始就隐藏所有的View
            //  hideAll();
        }

        /**
         * 全部都隐藏
         */
        public void hideAll() {
            // 设置各界面不可见，同时让它们不重新Layout
            loadingView.setVisibility(View.INVISIBLE);
            emptyView.setVisibility(INVISIBLE);
            errorView.setVisibility(INVISIBLE);
        }

        /**
         * 显示加载进度
         */
        public void showLoadingView() {
            hideAll();
            Log.d("jiejie","----show");
            loadingView.setVisibility(VISIBLE);
        }

        /**
         * 显示数据为空的界面
         */
        public void showEmptyView() {
            hideAll();
            emptyView.setVisibility(VISIBLE);
        }

        /**
         * 显示错误的界面
         */
        public void showErrorView() {
            hideAll();
            errorView.setVisibility(VISIBLE);
        }

        /**
         * 对错误页面进行点击
         */
        public void setErrorListener(View.OnClickListener listener) {
            if (listener != null && errorView != null) {
                hideAll();
                errorView.setOnClickListener(listener);
            }
        }
    }

### 版本跟新时，开启服务在状态栏中展示更新进度
![Notification](image/two.png)

### SmoothProgressBar的使用
### https://github.com/castorflex/SmoothProgressBar

### PopupWindow的使用
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
### 自定义弹窗
![Dialog](image/one.png)
![Dialog](image/ones.png)

    <style name="SheetDialogStyle" parent="@android:style/Theme.Dialog">
       <item name="android:windowBackground">@android:color/transparent</item>
       <item name="android:windowContentOverlay">@null</item>
       <item name="android:windowIsFloating">true</item>
       <item name="android:windowFrame">@null</item>
       <item name="android:backgroundDimEnabled">true</item>
       <item name="android:windowNoTitle">true</item>
       <item name="android:windowIsTranslucent">true</item>
     </style>
     <style name="custom_dialog" parent="@android:style/Theme.Dialog">
       <item name="android:windowFrame">@null</item>
       <item name="android:windowIsFloating">true</item>
       <item name="android:windowIsTranslucent">true</item>
       <item name="android:windowNoTitle">true</item>
       <item name="android:background">@android:color/transparent</item>
       <item name="android:windowBackground">@android:color/transparent</item>
       <item name="android:windowContentOverlay">@null</item>
       <item name="android:backgroundDimEnabled">false</item>
       <item name="android:backgroundDimAmount">0.6</item>
      </style>

### Apps 别人写的一个MVP demo