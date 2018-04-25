package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 10608780 on 16-04-2018.
 */

public class DownloadFinishReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "intent"+intent.getAction(), Toast.LENGTH_LONG).show();





    }
}
