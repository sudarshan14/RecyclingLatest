package sudarshan.bhatt.recycling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by 10608780 on 04-04-2018.
 */

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyItemViewHolder> {

    public MyItemAdapter() {

    }


    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        MyItemViewHolder holder = new MyItemViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        ArrayList<String> item =  new  GenerateData().stringArrayListData();
     //   item.get(position);
        holder.mTextView.setText( item.get(position));
    }

    @Override
    public int getItemCount() {
        return new GenerateData().stringArrayListData().size();
    }

    public static class MyItemViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyItemViewHolder(TextView itemView) {
            super(itemView);
            mTextView = itemView;
        }
    }

}
