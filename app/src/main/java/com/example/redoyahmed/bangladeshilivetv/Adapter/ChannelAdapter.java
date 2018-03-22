package com.example.redoyahmed.bangladeshilivetv.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.redoyahmed.bangladeshilivetv.Model.Latest_channels;
import com.example.redoyahmed.bangladeshilivetv.R;

import java.util.ArrayList;

public class ChannelAdapter extends Adapter<ChannelAdapter.ItemRowHolder> {
    private ArrayList<Latest_channels> dataList;
    private Context mContext;

    public class ItemRowHolder extends ViewHolder {
        public ImageView image;
        public LinearLayout lyt_parent;
        public TextView text;

        public ItemRowHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            lyt_parent = itemView.findViewById(R.id.rootLayout);
        }
    }

    public ChannelAdapter(Context context, ArrayList<Latest_channels> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ItemRowHolder(v);
    }

    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final Latest_channels singleItem = dataList.get(position);
        holder.text.setText(singleItem.getCategory_name());
        //Picasso.with(mContext).load(ApiClient.IMAGE_PATH + singleItem.getImage()).placeholder((int) R.drawable.header_logo).into(holder.image);
        //Picasso.get().load(ApiClient.IMAGE_PATH + singleItem.getImage()).placeholder(R.drawable.header_logo).into(holder.image);
        holder.lyt_parent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent(ChannelAdapter.this.mContext, SingleChannelActivity.class);
                intent.putExtra("Id", String.valueOf(singleItem.getId()));
                ChannelAdapter.this.mContext.startActivity(intent);*/
            }
        });
    }

    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }
}
