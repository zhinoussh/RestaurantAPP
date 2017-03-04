package com.android.shahkar.andelosapp.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.activities.CheckoutActivity;
import com.android.shahkar.andelosapp.database.DataBaseHandler;
import com.android.shahkar.andelosapp.fragments.TopbarFragment;
import com.android.shahkar.andelosapp.models.Order;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private static List<Order> mOrders;
    private static Context mContext;
    private static DataBaseHandler dbHandler;

    public OrderAdapter(List<Order> mOrders, Context mContext) {
        this.mOrders = mOrders;
        this.mContext = mContext;
        dbHandler=new DataBaseHandler(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View itemView=inflater.inflate(R.layout.order_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, final int position) {

        Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/Ubuntu-Medium.ttf");
        holder.txt_RowNumber.setTypeface(face);
        holder.txt_OrderName.setTypeface(face);
        holder.txt_OrderNumber.setTypeface(face);
        holder.txt_OrderPrice.setTypeface(face);

        final Order orderItem=mOrders.get(position);
        final int OrderId=orderItem.getOrderId();

        holder.txt_RowNumber.setText(String.valueOf(position+1));
        holder.txt_OrderName.setText(orderItem.getMenuItemName());
        holder.txt_OrderNumber.setText(String.valueOf(orderItem.getMenuItemCount()));
        holder.txt_OrderPrice.setText("$ "+orderItem.getPrice());


        holder.btn_increment_orderNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_value=orderItem.getMenuItemCount()+1;
                if(new_value<ApplicationConstant.MAXNUMPICKER) {
                    dbHandler.Add_Subtract_OrderNumber(OrderId, 1);
                    mOrders.get(position).setMenuItemCount(new_value);
                    notifyItemChanged(position);
                    setTotalPrice();
                }
            }
        });
        holder.btn_decrement_orderNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_value=orderItem.getMenuItemCount()-1;
                if(new_value>=ApplicationConstant.MINNUMPICKER) {
                    dbHandler.Add_Subtract_OrderNumber(OrderId, -1);
                    mOrders.get(position).setMenuItemCount(new_value);
                    notifyItemChanged(position);
                    setTotalPrice();
                }
            }
        });
        holder.btn_remove_orderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.RemoveOrderItem(OrderId);
                mOrders.remove(position);
                notifyItemRemoved(position);
                setTotalPrice();
            }
        });

    }

    private void setTotalPrice() {
        double total = 0;
        for (Order o : mOrders) {
            total += (o.getPrice() * o.getMenuItemCount());
        }
        String totalPrice="";
        if (total > 0)
            totalPrice="Total Price: $ " + String.valueOf(total);

        CheckoutActivity.txt_totalPrice.setText(totalPrice);

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
        public Button btn_increment_orderNum;
        public Button btn_decrement_orderNum;
        public Button btn_remove_orderItem;

        public ViewHolder(View itemView) {
            super(itemView);

            txt_RowNumber= (TextView) itemView.findViewById(R.id.txt_rowNumber);
            txt_OrderName= (TextView) itemView.findViewById(R.id.txt_orderName);
            txt_OrderNumber= (TextView) itemView.findViewById(R.id.txt_orderNumber);
            txt_OrderPrice= (TextView) itemView.findViewById(R.id.txt_orderPrice);

            btn_increment_orderNum= (Button) itemView.findViewById(R.id.btn_increment_orderNum);
            btn_decrement_orderNum= (Button) itemView.findViewById(R.id.btn_decrement_orderNum);
            btn_remove_orderItem= (Button) itemView.findViewById(R.id.btn_delete_orderItem);
        }


    }
}
