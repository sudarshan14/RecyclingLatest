package Service;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;

/**
 * Created by 10608780 on 16-04-2018.
 */

public class DownloadImageService extends IntentService {

    public  DownloadImageService(){
        super("DownloadImageService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
