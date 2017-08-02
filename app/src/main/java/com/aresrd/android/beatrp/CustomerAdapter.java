package com.aresrd.android.beatrp;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Ramadan on 28.12.2016.
 */

public class CustomerAdapter extends ArrayAdapter<Customer> {

    SessionManager sessionManager = null;
    Customer customer = null;
    Context mContext;
    int mLayoutResourceID;
    ArrayList<Customer> mCustomerData = null;

    public Paginator paging = new Paginator(getContext());

    public CustomerAdapter(Context context, int resource,  ArrayList<Customer> customers) {
        super(context, resource, customers);
        this.mContext = context;
        this.mLayoutResourceID = resource;
        this.mCustomerData = customers;
    }

    public Customer getItem(int position){
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomerHolder holder;
        View row = convertView;

        customer = getItem(position);
        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(mLayoutResourceID, parent, false);


            
            holder = new CustomerHolder();
            holder.txtCustomerName = (TextView)row.findViewById(R.id.lblCustomerNameList);
            holder.txtCustomerBalance = (TextView)row.findViewById(R.id.lblCustomerBalanceList);

            holder.layout1 = (LinearLayout) row.findViewById(R.id.cNamePartLayout);
            holder.layout2 = (LinearLayout) row.findViewById(R.id.cBalancePartLayout);


            if (position%2==1){
                holder.layout1.setBackgroundResource(R.drawable.lv_customer_background_even_item);
                holder.layout2.setBackgroundResource(R.drawable.lv_customer_background_even_item);
            } else {
                holder.layout1.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
                holder.layout2.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
            }

            row.setTag(holder);
        } else {
            holder = (CustomerHolder) row.getTag();
        }


        holder.txtCustomerName.setText(customer.Name);
        sessionManager = new SessionManager(getContext());
        String uData = sessionManager.GetValue("UserData");
        Gson gson = new Gson();
        UserData userData = gson.fromJson(uData,UserData.class);
        String langCode = sessionManager.GetValue("LangCode");
        String splitChar = langCode.equals("TR") ? "," : ".";
        String balance = customer.Balance+"";
        String splitter = balance.contains(",") ? "\\," : "\\.";
        String[] parts = balance.split(splitter);
        String firstPart = parts[0];
        String secondPart = parts[1];
        if(secondPart.length() == 1){
            balance = firstPart + splitChar + secondPart + "0";
        } else if(secondPart.length() >= 2){
            balance = firstPart + splitChar + secondPart.substring(0,2);
        }
        holder.txtCustomerBalance.setText(balance);





        //Click event is being registered
        RelativeLayout rowCustomer = (RelativeLayout)row.findViewById(R.id.rowCustomer);
        final String customerId = customer.Id + "";
        final String customerJson = gson.toJson(customer);
        final String header = ((MenuActivity)getContext()).getResources().getString(R.string.customer_detail);



        rowCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCustomerEdit fragment = new FragmentCustomerEdit();
                FragmentTransaction fragmentTransaction = ((MenuActivity)getContext()).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                sessionManager.SetValue("header", header);
                sessionManager.SetValue("customerId", customerId);
                sessionManager.SetValue("customer", customerJson);
                //sessionManager.SetValue("MenuItem", R.id.customers + "");
            }
        });
        return row;
    }

    public static class CustomerHolder{
        TextView txtCustomerName;
        TextView txtCustomerBalance;
        LinearLayout layout1, layout2;
    }
}