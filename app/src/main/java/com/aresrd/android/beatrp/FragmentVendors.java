package com.aresrd.android.beatrp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVendors extends Fragment {

    Context context = null;
    SessionManager sessionManager = null;
    RestService restService = null;

    public FragmentVendors() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restService = new RestService();
        context = getActivity();
        sessionManager = new SessionManager(context);
        String uData = sessionManager.GetValue("UserData");
        Gson gson = new Gson();
        UserData userData = gson.fromJson(uData,UserData.class);
        String token = userData.Token;
        restService.getService().GetAllVendors(token, new Callback<VendorListModel>() {
            @Override
            public void success(VendorListModel prm, Response response) {
                if(prm.IsSuccess){
                    List<Vendor> vendors = prm.Data;
                    ArrayList<Vendor> ary = new ArrayList<Vendor>(vendors);
                    VendorAdapter adapter = new VendorAdapter(context,ary);
                    ListView listView = (ListView)getView().findViewById(R.id.lvVendors);
                    View header = inflater.inflate(R.layout.layout_vendor_header, listView, false);
                    listView.addHeaderView(header);
                    listView.setAdapter(adapter);
                }
                else{
                    if(prm.ErrorMessage == "TokenExpired" || prm.ErrorMessage == "AuthFailed"){
                        sessionManager.SetValue("UserData","");
                        Intent intent = new Intent(context,LoginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        CharSequence text = prm.ErrorMessage;
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                CharSequence text = getResources().getString(R.string.service_error);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        View vieww =  inflater.inflate(R.layout.fragment_vendors, container, false);

        FloatingActionButton fab = (FloatingActionButton) vieww.findViewById(R.id.newVendor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentVendorEdit fragment = new FragmentVendorEdit();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack("addVendorEdit");
                fragmentTransaction.commit();
                String header = getResources().getString(R.string.new_vendor);
                sessionManager.SetValue("header", header);
                sessionManager.SetValue("vendorId","0");
                sessionManager.SetValue("MenuItem", MenuActionItem.ITEM6.ordinal() + "");
            }
        });

        return vieww;

    }

}
