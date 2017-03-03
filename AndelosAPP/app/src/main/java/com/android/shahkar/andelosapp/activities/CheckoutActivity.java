package com.android.shahkar.andelosapp.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.OrderAdapter;
import com.android.shahkar.andelosapp.database.DataBaseHandler;
import com.android.shahkar.andelosapp.models.Order;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-Medium.ttf");
        TextView txt_title_rowNum= (TextView) findViewById(R.id.txt_title_rowNumber);
        TextView txt_title_orderName= (TextView) findViewById(R.id.txt_title_orderName);
        TextView txt_title_orderNumber= (TextView) findViewById(R.id.txt_title_orderNumber);
        TextView txt_title_orderPrice= (TextView) findViewById(R.id.txt_title_orderPrice);

        txt_title_rowNum.setTypeface(face);
        txt_title_orderName.setTypeface(face);
        txt_title_orderNumber.setTypeface(face);
        txt_title_orderPrice.setTypeface(face);

        DataBaseHandler dbHandler=new DataBaseHandler(this);
        List<Order> lst_orders=dbHandler.getOrderList();
        OrderAdapter adapter=new OrderAdapter(lst_orders,this);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_orders);
        recyclerView.setAdapter(adapter);
    }
}
