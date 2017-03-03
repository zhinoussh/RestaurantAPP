package com.android.shahkar.andelosapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

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

        DataBaseHandler dbHandler=new DataBaseHandler(this);
        List<Order> lst_orders=dbHandler.getOrderList();
        OrderAdapter adapter=new OrderAdapter(lst_orders,this);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_orders);
        recyclerView.setAdapter(adapter);
    }
}
