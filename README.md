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

## 版本跟新时，开启服务在状态栏中展示更新进度
    /**
     * 展示Notification
     *
     * @param tickerMsg 标题
     * @param message   信息
     * @param progress  加载的进度
     */
    private void notifyThatExceedLv21(String tickerMsg, String message, int progress) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setSmallIcon(R.mipmap.ic_launcher); // 设置小图标 必须
        // 设置大图标
        notification.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.bg_message_imooc));
        notification.setContentTitle("MyTest"); // 设置标题 必须
        if (progress > 0 && progress < 100) {
            notification.setProgress(100, progress, false);
        } else {
            // false 可以将进度条隐藏
            notification.setProgress(0, 0, false);
            notification.setContentText(message); // 设置内容 必须
        }
        // 设置为true，点击该条通知会自动删除，false时只能通过滑动来删除
        notification.setAutoCancel(true);
        notification.setWhen(System.currentTimeMillis()); // 时间
        notification.setTicker(tickerMsg); // 设置状态栏开始动画的文字
        // 给Notification设置一个Action，设置点击通知后的操作（可以跳转Activity，打开Service，或发送广播）
        notification.setContentIntent(progress >= 100 ? getContentIntent() : PendingIntent.getActivity(this, 0,
                new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
        mNotification = notification.build();
        // 发送通知
        notificationManager.notify(0, mNotification);
    }

    private PendingIntent getContentIntent() {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, getInstallApkIntent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
        return contentIntent;
    }
