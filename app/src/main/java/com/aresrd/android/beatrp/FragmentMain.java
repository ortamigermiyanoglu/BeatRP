package com.aresrd.android.beatrp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Anthony on 16-01-25.
 */
public class FragmentMain extends ListFragment {


    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container);
        setListAdapter(new MenuListAdapter(R.layout.row_menu_action_item, getActivity(), MenuActionItem.values()));
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (id==0){
            Toast.makeText(getActivity(), "this is my Toast message!!! =)",
                    Toast.LENGTH_LONG).show();
        } else if (id==1){
            FragmentCollection fragment = new FragmentCollection();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);
            fragmentTransaction.addToBackStack("addCollection");
            fragmentTransaction.commit();
        } else if (id==2){
            FragmentPayment fragment = new FragmentPayment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);
            fragmentTransaction.addToBackStack("addPayment");
            fragmentTransaction.commit();
        } else if (id==3){
            FragmentCost fragment = new FragmentCost();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);
            fragmentTransaction.addToBackStack("addExpense");
            fragmentTransaction.commit();
        } else if (id==4){
            FragmentCustomers newFragment = new FragmentCustomers();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,newFragment);
            fragmentTransaction.addToBackStack("addCustomers");
            fragmentTransaction.commit();

        } else if (id==5){
            FragmentVendors fragment = new FragmentVendors();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);
            fragmentTransaction.addToBackStack("addVendorss");
            fragmentTransaction.commit();;
        } else if (id==6){
            FragmentAccount fragment = new FragmentAccount();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);
            fragmentTransaction.addToBackStack("addAccount");
            fragmentTransaction.commit();
        } else if(id==7) {
            FragmentInvoices fragment = new FragmentInvoices();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);
            fragmentTransaction.addToBackStack("addInvoices");
            fragmentTransaction.commit();
        }

    }
}
