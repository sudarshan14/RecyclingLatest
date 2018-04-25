package Service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import sud.bhatt.retrofit.Download;
import sud.bhatt.retrofit.RetrofitInterface;
import sudarshan.bhatt.recycling.MainActivity;
import sudarshan.bhatt.recycling.R;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadImagesService extends Service {

    //    private MediaPlayer player;
//    private NotificationCompat.Builder notificationBuilder;
//    private NotificationManager notificationManager;
    private int totalFileSize;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(android.support.v4.R.drawable.notification_bg)
//                .setContentTitle("Downloading File")
//                .setContentText("...")
//                .setAutoCancel(true);
//        notificationManager.notify(0, notificationBuilder.build());

        Intent notificationIntent = new Intent(this, DownloadImagesService.class);
        notificationIntent.setAction("pause");
//        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        //  NotificationCompat.Builder notification = new NotificationCompat.Builder(this);

//        Notification notification = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.appicon)
//                .setContentTitle("Downloading File")
//                .setContentText("...").setContentIntent(pendingIntent)
//                .setAutoCancel(true).addAction(android.R.drawable.ic_media_previous, "Play",
//                        pendingIntent);
//        startForeground(0,
//                notification);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Title")
                .setTicker("Title")
                .setContentText("App running")
                .setSmallIcon(R.drawable.appicon)
                .addAction(android.R.drawable.ic_media_previous, "Play",
                        pendingIntent)
                .build();
        startForeground(101, notification);

        new Thread(new Runnable() {
            @Override
            public void run() {
                initDownload();
            }
        }).start();

        return START_STICKY;

    }

    private void initDownload() {

        Retrofit obj = new Retrofit.Builder()
                .baseUrl("https://thefaceart.com/").build();

        RetrofitInterface retrofitInterface = obj.create(RetrofitInterface.class);
        Call<ResponseBody> request = retrofitInterface.downoadFile();

        try {

            downloadFile(request.execute().body());

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

    private void downloadFile(ResponseBody body) throws IOException {

        int count;
        byte[] data = new byte[1024 * 4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(), 1024 * 8);
        File outPutFile = new File(getCacheDir(), "jiggi1.JPG");
        OutputStream bos = new FileOutputStream(outPutFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1) {
            total += count;

            totalFileSize = (int) (fileSize / (Math.pow(1024, 2)));
            double current = Math.round(total / (Math.pow(1024, 2)));
            int progress = (int) ((total * 100) / fileSize);
            long currentTime = System.currentTimeMillis() - startTime;
            Download download = new Download();
            download.setTotalFileSize(totalFileSize);
            if (currentTime > 1000 * timeCount) {

                download.setCurrentFileSize((int) current);
                download.setProgress(progress);
                sendNotification(download);
                timeCount++;
            }
            bos.write(count);
        }
        onDownloadComplete();
        bos.flush();
        bos.close();
        bis.close();
    }


    private void sendNotification(Download download) {
//        notificationBuilder.setProgress(100, download.getProgress(), false);
//        notificationBuilder.setContentText("Downloading file " + download.getCurrentFileSize() + "/" + totalFileSize + " MB");
//        notificationManager.notify(0, notificationBuilder.build());

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
//        Intent intent = new Intent("com.android.ServiceStopped");
//        sendBroadcast(intent);
//        notificationManager.cancel(0);
    }

    private void onDownloadComplete() {

        Download download = new Download();
        download.setProgress(100);
        //   sendIntent(download);

//        notificationManager.cancel(0);
//        notificationBuilder.setProgress(0, 0, false);
//        notificationBuilder.setContentText("File Downloaded");
//        notificationManager.notify(0, notificationBuilder.build());

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //   player.stop();
    }
}
