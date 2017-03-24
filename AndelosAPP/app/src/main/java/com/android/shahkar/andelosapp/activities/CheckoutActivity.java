package com.android.shahkar.andelosapp.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.shahkar.andelosapp.R;
import com.android.shahkar.andelosapp.adapters.OrderAdapter;
import com.android.shahkar.andelosapp.database.OrderDataSource;
import com.android.shahkar.andelosapp.fragments.TopbarFragment;
import com.android.shahkar.andelosapp.models.Order;
import com.android.shahkar.andelosapp.network.UserConnectivity;
import com.android.shahkar.andelosapp.utils.ApplicationConstant;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    public static TextView txt_totalPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TopbarFragment topbarFragment = new TopbarFragment();
        getFragmentManager().beginTransaction().add(R.id.topbar_fragment_container
                , topbarFragment).commit();

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Ubuntu-Medium.ttf");
        TextView txt_review_order = (TextView) findViewById(R.id.txt_reviewOrder);
        txt_review_order.setTypeface(face);

        OrderDataSource ds=new OrderDataSource(this);
        List<Order> lst_orders = ds.getOrderList();
        OrderAdapter adapter = new OrderAdapter(lst_orders, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_orders);
        recyclerView.setAdapter(adapter);

        txt_totalPrice = (TextView) findViewById(R.id.txt_totalPrice);
        txt_totalPrice.setTypeface(face);
               double total = 0;
        for (Order o : lst_orders) {
            total += (o.getPrice() * o.getMenuItemCount());
        }
        if (total > 0)
            txt_totalPrice.setText("Total Price: $ " + String.valueOf(total));

        Button btn_finalize_order = (Button) findViewById(R.id.btn_finalize_order);
        btn_finalize_order.setTypeface(face);

        UserConnectivity userConnectivity = new UserConnectivity(this);
        final boolean loginStatus = userConnectivity.isLoggedIn();
        if (loginStatus)
            btn_finalize_order.setText("Finalize Your Order");
        else
            btn_finalize_order.setText("Login to Continue");

        btn_finalize_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginStatus) {
                    CallDeliveryDetail();
                } else {
                    Intent loginIntent = new Intent(CheckoutActivity.this, LoginActivity.class);
                    startActivityForResult(loginIntent, ApplicationConstant.LOGIN_REQUEST);
                }
            }
        });

    }

    private void CallDeliveryDetail() {
        Intent DeliveryDetailIntent = new Intent(CheckoutActivity.this, DeliveryDetailActivity.class);
        startActivity(DeliveryDetailIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ApplicationConstant.LOGIN_REQUEST) {
            if (resultCode == RESULT_OK) {
                CallDeliveryDetail();
            }
        }
    }

}
