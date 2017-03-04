package com.android.shahkar.andelosapp.activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.OrderAdapter;
import com.android.shahkar.andelosapp.database.DataBaseHandler;
import com.android.shahkar.andelosapp.fragments.TopbarFragment;
import com.android.shahkar.andelosapp.models.Order;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TopbarFragment topbarFragment=new TopbarFragment();
        getFragmentManager().beginTransaction().add(R.id.topbar_fragment_container
                ,topbarFragment).commit();

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/Ubuntu-Medium.ttf");
        TextView txt_review_order= (TextView) findViewById(R.id.txt_reviewOrder);

        txt_review_order.setTypeface(face);

        DataBaseHandler dbHandler=new DataBaseHandler(this);
        List<Order> lst_orders=dbHandler.getOrderList();
        OrderAdapter adapter=new OrderAdapter(lst_orders,this);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_orders);
        recyclerView.setAdapter(adapter);

        Button btn_finalize_order= (Button) findViewById(R.id.btn_finalize_order);
        btn_finalize_order.setTypeface(face);
    }
}
