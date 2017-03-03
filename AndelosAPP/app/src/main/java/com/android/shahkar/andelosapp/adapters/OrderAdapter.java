package com.android.shahkar.andelosapp.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.models.Order;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> mOrders;
    private Context mContext;

    public OrderAdapter(List<Order> mOrders, Context mContext) {
        this.mOrders = mOrders;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View itemView=inflater.inflate(R.layout.order_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {

        Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/Ubuntu-Medium.ttf");
        holder.txt_RowNumber.setTypeface(face);
        holder.txt_OrderName.setTypeface(face);
        holder.txt_OrderNumber.setTypeface(face);
        holder.txt_OrderPrice.setTypeface(face);

        Order orderItem=mOrders.get(position);
        holder.txt_RowNumber.setText((position+1)+"");
        holder.txt_OrderName.setText(orderItem.getMenuItemName());
        holder.txt_OrderNumber.setText(orderItem.getMenuItemCount());
        holder.txt_OrderPrice.setText("$ "+orderItem.getPrice());
    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txt_RowNumber;
        public TextView txt_OrderNumber;
        public TextView txt_OrderName;
        public TextView txt_OrderPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_RowNumber= (TextView) itemView.findViewById(R.id.txt_rowNumber);
            txt_OrderName= (TextView) itemView.findViewById(R.id.txt_orderName);
            txt_OrderNumber= (TextView) itemView.findViewById(R.id.txt_orderNumber);
            txt_OrderPrice= (TextView) itemView.findViewById(R.id.txt_orderPrice);

        }
    }
}
