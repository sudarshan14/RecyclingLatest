package sudarshan.bhatt.recycling;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Service.DownloadImageService;
import Service.DownloadImagesService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sud.bhatt.retrofit.Api;
import sud.bhatt.retrofit.Data;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, CallBackInterface {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;
    private Button buttonStart;
    private Button buttonStop;
    private Button startFragment;
    private Button enterPip;
    private static final String TAG = "Fragment";
    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Log.d(TAG, "onCreate");
        recyclerView = findViewById(R.id.reycleView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        int id = getResources().getIdentifier("appicon", "id", this.getPackageName());
        adapter = new MyAdapter(this, new GenerateData().carDetails());
        recyclerView.setAdapter(adapter);
        int f = 0x00aa00;

        buttonStart = (Button) findViewById(R.id.play);
        buttonStop = (Button) findViewById(R.id.pause);
        startFragment = findViewById(R.id.loadFragment);
        enterPip = (findViewById(R.id.enterPip));
        //attaching onclicklistener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        startFragment.setOnClickListener(this);
        enterPip.setOnClickListener(this);
        loadImages();


    }

    private void loadImages() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
//Retrofit turns your HTTP API into a Java interface.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        Api api = retrofit.create(Api.class);
        Call<Data> listCall = api.loadFats();
        listCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                Log.d("retrofit", "" + response.body().getCategory());

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("retrofit", "" + t.toString());
            }
        });


    }


    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //start the service here

            startService(new Intent(this, DownloadImagesService.class).setAction("FS"));
        } else if (view == buttonStop) {
            //stop the service here
            stopService(new Intent(this, DownloadImageService.class));
        } else if (view == startFragment) {
//            Fragment sampleFragment = null;
            SampleFragment objSampleFragment;
            // use when back stack is required
//            switch (manager.getBackStackEntryCount()) {
//
//                case 0:
//                    sampleFragment = new SampleFragment();
//                    break;
//                case 1:
//                    sampleFragment = new SampleFragment2();
//                    break;
//                case 2:
//                    sampleFragment = new SampleFragment3();
//                    break;
//                default:
//                    sampleFragment = new SampleFragment();
//                    break;
//            }

            // if there is no backstack

//            sampleFragment = manager.findFragmentById(R.id.FrangmentLoader);
//            if (sampleFragment instanceof SampleFragment) {
//                sampleFragment = new SampleFragment2();
//            } else if (sampleFragment instanceof SampleFragment2) {
//                sampleFragment = new SampleFragment3();
//            } else if (sampleFragment instanceof SampleFragment3) {
//                sampleFragment = new SampleFragment();
//            } else {
//                objSampleFragment = new SampleFragment();
//                objSampleFragment.setCallBackInterface(this);
//            }
            objSampleFragment = new SampleFragment();
            objSampleFragment.setCallBackInterface(this);

            transaction = manager.beginTransaction();
            //   transaction.isAddToBackStackAllowed();
            transaction.add(R.id.FrangmentLoader, objSampleFragment, "sampleFragment");
            //    transaction.addToBackStack("sampleFragment");
            transaction.commit();
        } else if (view == enterPip) {
            startActivity(new Intent(MainActivity.this, PipActivity.class));

        }
    }

//    @Override
//    public void onBackPressed() {
//
//        Fragment fragment = manager.findFragmentById(R.id.FrangmentLoader);
//        if (fragment != null) {
//
//            transaction = manager.beginTransaction();
//            transaction.remove(fragment);
//            transaction.commit();
//
//        } else
//            super.onBackPressed();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onresume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPaunse");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    @Override
    public void callBackMethod() {
     //   Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
        SampleFragment2 objSampleFragment = new SampleFragment2();
        // objSampleFragment.setCallBackInterface(this);

        transaction = manager.beginTransaction();
        //   transaction.isAddToBackStackAllowed();
        transaction.replace(R.id.FrangmentLoader, objSampleFragment, "objSampleFragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
