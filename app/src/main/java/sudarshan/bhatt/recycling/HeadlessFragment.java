package sudarshan.bhatt.recycling;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;


public class HeadlessFragment extends Fragment {

    public static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";
    TaskStatusCallback mStatusCallback;
    BackgroundTask mBackgroundTask;
    boolean isTaskExecuting = false;

    public static interface TaskStatusCallback{

        void onPreExecute();

        void onProgressUpdate(int progress);

        void onPostExecute();

        void onCancelled();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mStatusCallback = (TaskStatusCallback)activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }


    @Override
    public void onDetach() {
        super.onDetach();

        mStatusCallback = null;
    }

    private class BackgroundTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            if (mStatusCallback != null)
                mStatusCallback.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            int progress = 0;
            while (progress < 100 && !isCancelled()) {
                progress++;
                publishProgress(progress);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (mStatusCallback != null)
                mStatusCallback.onPostExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (mStatusCallback != null)
                mStatusCallback.onProgressUpdate(values[0]);
        }

        @Override
        protected void onCancelled(Void result) {
            if (mStatusCallback != null)
                mStatusCallback.onCancelled();
        }

    }

    public void startBackgroundTask() {
        if (!isTaskExecuting) {
            mBackgroundTask = new BackgroundTask();
            mBackgroundTask.execute();
            isTaskExecuting = true;
        }
    }

    public void cancelBackgroundTask() {
        if (isTaskExecuting) {
            mBackgroundTask.cancel(true);
            isTaskExecuting = false;
        }
    }

    public void updateExecutingStatus(boolean isExecuting) {
        this.isTaskExecuting = isExecuting;
    }
}
