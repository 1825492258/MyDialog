package com.item.dialog.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.item.dialog.R;
import com.item.dialog.service.update.UpdateDownloadListener;
import com.item.dialog.service.update.UpdateManager;

import java.io.File;

public class UpdateService extends Service {

    /**
     * 服务器固定地址
     */
    private static final String APK_URL_TITLE = "http://www.imooc.com/mobile/mukewang.apk";
    /**
     * 文件存放路经
     */
    private String filePath;
    /**
     * 文件下载地址
     */
    private String apkUrl;
    //获取NotificationManager实例
    private NotificationManager notificationManager;
    private Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        filePath = Environment.getExternalStorageDirectory() + "/dialog/dialog.apk";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        apkUrl = APK_URL_TITLE;
        notifyUser("开始下载", "开始下载", 0);
        startDownLoad();
        return super.onStartCommand(intent, flags, startId);
    }

    // 开始下载
    private void startDownLoad() {
        UpdateManager.getInstance().startDownload(apkUrl, filePath, new UpdateDownloadListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onPrepared(long contentLength, String downloadUrl) {

            }

            @Override
            public void onProgressChanged(int progress, String downloadUrl) {
                notifyUser("正在下载", "正在下载", progress);
            }

            @Override
            public void onPaused(int progress, int completeSize, String downloadUrl) {
                notifyUser("下载失败", "下载失败，请检查网络连接或SD卡存储空间", 0);
                deleteApkFile();
                stopSelf(); // 停掉服务自身
            }

            @Override
            public void onFinished(int completeSize, String downloadUrl) {
                notifyUser("下载完成", "下载完成", 100);
                stopSelf(); // 停掉服务自身
                startActivity(getInstallApkIntent());
            }

            @Override
            public void onFailure() {
                notifyUser("下载失败", "下载失败，请检查网络连接或SD卡存储空间", 0);
                deleteApkFile();
                stopSelf(); // 停掉服务自身
            }
        });
    }

    private void notifyUser(String tickerMsg, String message, int progress) {
        notifyThatExceedLv21(tickerMsg, message, progress);
    }

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

    /**
     * 下载完成，安装
     */
    private Intent getInstallApkIntent() {
        File apkfile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 删除无用apk文件
     */
    private boolean deleteApkFile() {
        File apkFile = new File(filePath);
        if (apkFile.exists() && apkFile.isFile()) {
            return apkFile.delete();
        }
        return false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
