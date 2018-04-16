package sudarshan.bhatt.recycling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity  {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView.Adapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.reycleView);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(this, new GenerateData().carDetails());
        recyclerView.setAdapter(adapter);


    }


}
