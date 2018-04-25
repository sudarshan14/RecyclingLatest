package sudarshan.bhatt.recycling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import Service.DownloadImageService;

/**
 * Created by 10608780 on 24-04-2018.
 */

public class SampleFragment extends Fragment implements View.OnClickListener {
    String TAG = "Fragment";
    Button startDownLoad;
    CallBackInterface callBackInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.layout_sample_fragemt, container, false);

        startDownLoad = v.findViewById(R.id.hitMe);
        startDownLoad.setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        if(callBackInterface !=null){
            callBackInterface.callBackMethod();
            Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();
        }

//        Intent intent = new Intent(getActivity(), DownloadImageService.class);
//        intent.putExtra("index",0);
//        getActivity().startService(intent);
//
//
//        Toast.makeText(getActivity(),"clicked",Toast.LENGTH_LONG).show();

    }

    public void setCallBackInterface(CallBackInterface callBackInterface){
        this.callBackInterface = callBackInterface;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Yeiii");
        Log.d(TAG, "onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
