package Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
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
import sudarshan.bhatt.recycling.R;

/**
 * Created by 10608780 on 16-04-2018.
 */

public class DownloadImageService extends IntentService {

    public  DownloadImageService(){
        super("DownloadImageService");
    }


    private MediaPlayer player;
    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;
    private int totalFileSize;


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.appicon)
                .setContentTitle("Downloading File")
                .setContentText("...")
                .setAutoCancel(true);
        notificationManager.notify(0, notificationBuilder.build());

        initDownload();
    }

    private void initDownload() {

        Retrofit obj = new Retrofit.Builder()
                .baseUrl("https://thefaceart.com/").build();

        RetrofitInterface retrofitInterface = obj.create(RetrofitInterface.class);
        Call<ResponseBody> request = retrofitInterface.downoadFile();

        try{

            downloadFile(request.execute().body());

        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    private void downloadFile(ResponseBody body) throws IOException{

        int count;
        byte [] data = new byte[1024*4];
        long fileSize = body.contentLength();
        InputStream bis = new BufferedInputStream(body.byteStream(),1024*8);
        File outPutFile = new File(getCacheDir()+"jiggi.JPG");
        OutputStream bos = new FileOutputStream(outPutFile);
        long total = 0;
        long startTime = System.currentTimeMillis();
        int timeCount = 1;
        while ((count = bis.read(data)) != -1){
            total +=count;

            totalFileSize = (int)(fileSize/ (Math.pow(1024,2)));
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


    private  void sendNotification(Download download){
       notificationBuilder.setProgress(100,download.getProgress(),false);
        notificationBuilder.setContentText("Downloading file "+ download.getCurrentFileSize() +"/"+totalFileSize +" MB");
        notificationManager.notify(0, notificationBuilder.build());

    }


//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {

//        intent.getIntExtra("index", 999);
//
//        String music = intent.getStringExtra("music");
//        if (!music.equals("")) {
//
//            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//            player.setLooping(true);
//            player.start();
//        } else {
//
//
//        }
        //start sticky means service will be explicity started and stopped

//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(android.support.v4.R.drawable.notification_bg)
//                .setContentTitle("Downloading File")
//                .setContentText("...")
//                .setAutoCancel(true);
//        notificationManager.notify(0, notificationBuilder.build());
////        startForeground("ABC",
////                notification);
////        startF
//        initDownload();
//        return START_REDELIVER_INTENT;
//    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
//        Intent intent = new Intent("com.android.ServiceStopped");
//        sendBroadcast(intent);
        notificationManager.cancel(0);
    }

    private void onDownloadComplete(){

        Download download = new Download();
        download.setProgress(100);
     //   sendIntent(download);

        notificationManager.cancel(0);
        notificationBuilder.setProgress(0,0,false);
        notificationBuilder.setContentText("File Downloaded");
        notificationManager.notify(0, notificationBuilder.build());

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
