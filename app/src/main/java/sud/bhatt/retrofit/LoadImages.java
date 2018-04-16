package sud.bhatt.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import sudarshan.bhatt.recycling.R;

public class LoadImages extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_images);

        listView =  findViewById(R.id.listViewHeroes);
        loadImages();
    }


    private void loadImages() {
//Retrofit turns your HTTP API into a Java interface.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL + Api.CLASS)
                .build();
        Api api = retrofit.create(Api.class);
        Call<List<Data>> listCall = api.loadData();
        listCall.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {

                List<Data> list = response.body();
                String[] heroes = new String[list.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < list.size(); i++) {
                    heroes[i] = list.get(i).getName();
                }


                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
