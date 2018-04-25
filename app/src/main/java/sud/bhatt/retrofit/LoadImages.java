package sud.bhatt.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sudarshan.bhatt.recycling.R;

public class LoadImages extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);

        listView = findViewById(R.id.listViewHeroes);
        loadImages();
    }


    private void loadImages() {
//Retrofit turns your HTTP API into a Java interface.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Data> listCall = api.loadFats();
        listCall.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                Log.d("1", "" + response.body());

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.d("1", "" + t.toString());
            }
        });


    }
}
