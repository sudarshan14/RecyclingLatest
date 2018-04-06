package sudarshan.bhatt.recycling;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 10608780 on 03-04-2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MyCarsDataModel> data;
    private ArrayAdapter<String> itemAdapter;
    public MyAdapter() {

    }

    public MyAdapter(Context context, ArrayList<MyCarsDataModel> data) {

        this.context = context;
        this.data = data;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iteView = LayoutInflater.from(context).inflate(R.layout.item_details_list, parent, false);

        MyViewHolder holder = new MyViewHolder(iteView);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {

        MyCarsDataModel model = data.get(position);

        holder.name.setText(model.getCarName());
        holder.model.setText(model.getCarModel());
        holder.price.setText(model.getCarPrice());
        holder.itemRecycleView.setAdapter(new MyItemAdapter());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView name, model, price;
        private RecyclerView itemRecycleView;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            model = view.findViewById(R.id.model);
            price = view.findViewById(R.id.price);
            itemRecycleView = view.findViewById(R.id.reycleViewItem);
            try {
                itemRecycleView.setLayoutManager(new LinearLayoutManager(new MyAdapter().context, LinearLayoutManager.HORIZONTAL, false));
         //       itemAdapter = new ArrayAdapter<String>(new MyAdapter().context,android.R.layout.simple_list_item_1,new GenerateData().stringArrayListData());
            } catch (Exception e) {
                Log.e("sud", e.toString());
            }
        }
    }
}
