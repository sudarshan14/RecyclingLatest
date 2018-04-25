package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by 10608780 on 24-04-2018.
 */

public class ConnectivityChangeReciever extends BroadcastReceiver {

    public ConnectivityChangeReciever(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"Connectivity Changed", Toast.LENGTH_LONG).show();

        if(intent.getAction() == "android.intent.action.CONFIGURATION_CHANGED"){
            Toast.makeText(context, "config changes", Toast.LENGTH_LONG).show();
        }
        if(intent.getAction() =="android.intent.action.BATTERY_LOW"){
            Toast.makeText(context, "battery low", Toast.LENGTH_LONG).show();
        }
        if(intent.getAction() =="android.intent.action.CONFIGURATION_CHANG"){
            Toast.makeText(context, "CONFIGURATION_CHANG", Toast.LENGTH_LONG).show();
        }
    }
}
