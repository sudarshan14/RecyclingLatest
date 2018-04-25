package sudarshan.bhatt.recycling;

import android.app.PictureInPictureParams;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class PipActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView mPlayer;
    Button play, pause;
//    PictureInPictureParams.Builder param = new PictureInPictureParams.Builder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);

   //     mPlayer = findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mPlayer);
        mPlayer.setMediaController(mediaController);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.vid_bigbuckbunny;
        //  String path = getResources().getResourcePackageName(R.raw.vid_bigbuckbunny);
        Log.d("path", path);
        if (path != null && !path.equals(""))
            mPlayer.setVideoURI(Uri.parse(path));
        mPlayer.requestFocus();
   //     play = findViewById(R.id.play);
   //     pause = findViewById(R.id.pause);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);


//        mPlayer.setV
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Rational aspectRatio = new Rational(mPlayer.getWidth(), mPlayer.getHeight());
//        param.setAspectRatio(aspectRatio).build();
//        enterPictureInPictureMode(param.build());
    }

    public void onClick(View v) {

        if (v == play) {
            if (mPlayer != null && !mPlayer.isPlaying()) {

                mPlayer.start();
            }
        }
        if (v == pause) {
            if (mPlayer != null && mPlayer.isPlaying()) {

                mPlayer.stopPlayback();
            }
        }

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

//    @Override
//    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
//        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
//    }
}
