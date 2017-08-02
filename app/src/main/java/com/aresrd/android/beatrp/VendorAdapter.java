package com.aresrd.android.beatrp;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Ramadan on 31.12.2016.
 */

public class VendorAdapter extends ArrayAdapter<Vendor> {
    SessionManager sessionManager = null;
    Vendor vendor = null;


    public VendorAdapter(Context context, ArrayList<Vendor> vendors) {
        super(context, 0, vendors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VendorHolder holder = null;
        // Get the data item for this position
        vendor = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_lv_vendor, parent, false);
            holder = new VendorHolder();

            holder.txtVendorName = (TextView)convertView.findViewById(R.id.lblVendorNameList);
            holder.txtVendorBalance = (TextView)convertView.findViewById(R.id.lblVendorBalanceList);


            holder.layout1 = (LinearLayout) convertView.findViewById(R.id.vNamePartLayout);
            holder.layout2 = (LinearLayout) convertView.findViewById(R.id.vBalancePartLayout);


            if (position%2==1){
                holder.layout1.setBackgroundResource(R.drawable.lv_customer_background_even_item);
                holder.layout2.setBackgroundResource(R.drawable.lv_customer_background_even_item);
            } else {
                holder.layout1.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
                holder.layout2.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
            }




            convertView.setTag(holder);
        } else {
            holder = (VendorHolder) convertView.getTag();
        }


        holder.txtVendorName.setText(vendor.Name);
        sessionManager = new SessionManager(getContext());
        String uData = sessionManager.GetValue("UserData");
        Gson gson = new Gson();
        UserData userData = gson.fromJson(uData,UserData.class);
        String langCode = sessionManager.GetValue("LangCode");
        String splitChar = langCode.equals("TR") ? "," : ".";
        String balance = vendor.Balance + "";
        String splitter = balance.contains(",") ? "\\," : "\\.";
        String[] parts = balance.split(splitter);
        String firstPart = parts[0];
        String secondPart = parts[1];
        if(secondPart.length() == 1){
            balance = firstPart + splitChar + secondPart + "0";
        } else if(secondPart.length() >= 2){
            balance = firstPart + splitChar + secondPart.substring(0,2);
        }
        holder.txtVendorBalance.setText(balance);
        //Click event is being registered
        RelativeLayout rowVendor = (RelativeLayout)convertView.findViewById(R.id.rowVendor);
        final String vendorId = vendor.Id + "";
        final String vendorJson = gson.toJson(vendor);
        final String header = ((MenuActivity)getContext()).getResources().getString(R.string.vendor_detail);
        rowVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentVendorEdit fragment = new FragmentVendorEdit();
                FragmentTransaction fragmentTransaction = ((MenuActivity)getContext()).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                sessionManager.SetValue("header", header);
                sessionManager.SetValue("vendorId", vendorId);
                sessionManager.SetValue("vendor", vendorJson);
                //sessionManager.SetValue("MenuItem", R.id.vendors + "");
            }
        });
        return convertView;
    }

    public static class VendorHolder{
        TextView txtVendorName;
        TextView txtVendorBalance;
        LinearLayout layout1, layout2;
    }


}
