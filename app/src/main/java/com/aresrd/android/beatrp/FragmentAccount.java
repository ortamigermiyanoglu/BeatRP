package com.aresrd.android.beatrp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends Fragment {

    Context context = null;
    RestService restService = null;
    SessionManager sessionManager = null;
    UserData userData = null;
    String token = "";
    private String year;
    private String month;
    private String day;
    static final int DATE_DIALOG_ID = 999;
    Fragment fragment = null;
    List<Customer> customers = null;
    List<Vendor> vendors = null;
    Spinner cmbAccountTypes = null;
    Spinner cmbAccounts = null;
    View vw = null;
    int selection = 0;
    int pstn = 0;

    public FragmentAccount() {
        fragment = this;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restService = new RestService();
        context = getActivity();
        sessionManager = new SessionManager(context);
        vw = inflater.inflate(R.layout.fragment_account, container, false);
        cmbAccountTypes = (Spinner)vw.findViewById(R.id.cmbAccountTypes);
        cmbAccounts = (Spinner)vw.findViewById(R.id.cmbAccounts);
        String uData = sessionManager.GetValue("UserData");
        if(uData != null && uData.length() > 0){
            Gson gson = new Gson();
            userData = gson.fromJson(uData, UserData.class);
            token = userData.Token;
        }
        //Default
        restService.getService().GetAllCustomers(token, new Callback<CustomerListModel>() {
            @Override
            public void success(CustomerListModel prm, Response response) {
                if(prm.IsSuccess){
                    customers = prm.Data;
                    Spinner cmbAccounts = (Spinner)vw.findViewById(R.id.cmbAccounts);
                    List<String> list = new ArrayList<String>();
                    for(int i=0;i<prm.Data.size();i++){
                        list.add(prm.Data.get(i).Name);
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cmbAccounts.setAdapter(dataAdapter);
                }
                else{
                    CharSequence text = prm.ErrorMessage;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                CharSequence text = getResources().getString(R.string.service_error);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        //OnItemSelected
        cmbAccountTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = position;
                if(position == 0){//Customer
                    restService.getService().GetAllCustomers(token, new Callback<CustomerListModel>() {
                        @Override
                        public void success(CustomerListModel prm, Response response) {
                            if(prm.IsSuccess){
                                customers = prm.Data;
                                List<String> list = new ArrayList<String>();
                                for(int i=0;i<prm.Data.size();i++){
                                    list.add(prm.Data.get(i).Name);
                                }
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                cmbAccounts.setAdapter(dataAdapter);
                            }
                            else{
                                CharSequence text = prm.ErrorMessage;
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            CharSequence text = error.getMessage();
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    });
                }
                else if(position == 1){
                    restService.getService().GetAllVendors(token, new Callback<VendorListModel>() {
                        @Override
                        public void success(VendorListModel prm, Response response) {
                            if(prm.IsSuccess){
                                vendors = prm.Data;
                                Spinner cmbAccounts = (Spinner)vw.findViewById(R.id.cmbAccounts);
                                List<String> list = new ArrayList<String>();
                                for(int i=0;i<prm.Data.size();i++){
                                    list.add(prm.Data.get(i).Name);
                                }
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                cmbAccounts.setAdapter(dataAdapter);
                            }
                            else{
                                CharSequence text = prm.ErrorMessage;
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            CharSequence text = error.getMessage();
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                restService.getService().GetAllCustomers(token, new Callback<CustomerListModel>() {
                    @Override
                    public void success(CustomerListModel prm, Response response) {
                        if(prm.IsSuccess){
                            customers = prm.Data;
                            Spinner cmbAccounts = (Spinner)vw.findViewById(R.id.cmbAccounts);
                            List<String> list = new ArrayList<String>();
                            for(int i=0;i<prm.Data.size();i++){
                                list.add(prm.Data.get(i).Name);
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            cmbAccounts.setAdapter(dataAdapter);
                        }
                        else{
                            CharSequence text = prm.ErrorMessage;
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        CharSequence text = error.getMessage();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });
            }
        });
        final Button btnBeginDate = (Button)vw.findViewById(R.id.btnBeginDate);
        final Button btnEndDate = (Button)vw.findViewById(R.id.btnEndDate);
        cmbAccounts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pstn = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                CharSequence text = getResources().getString(R.string.nothing_selected_at_account);
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR) + "";
        int mnth = c.get(Calendar.MONTH) + 1;
        month = (mnth + "").length() == 1 ? "0" + mnth : mnth + "";
        day = (c.get(Calendar.DAY_OF_MONTH) + "").length() == 1 ? "0" + c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH) + "";

        // set current date into btnBeginDate
        btnBeginDate.setText(new StringBuilder()
                //Month is 0 based, just add 1
                .append(day).append(".")
                .append(month).append(".")
                .append(year));
        btnBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.SetValue("DateType",getResources().getString(R.string.begin_date));
                sessionManager.SetValue("Date", btnBeginDate.getText().toString());
                FragmentManager fm = getFragmentManager();
                FragmentDpDialog dialogFragment = new FragmentDpDialog ();
                dialogFragment.setTargetFragment(fragment, DATE_DIALOG_ID);
                dialogFragment.show(fm, getResources().getString(R.string.begin_date));
            }
        });
        // set current date into btnDateDate
        btnEndDate.setText(new StringBuilder()
                //Month is 0 based, just add 1
                .append(day).append(".")
                .append(month).append(".")
                .append(year));
        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.SetValue("DateType",getResources().getString(R.string.end_date));
                sessionManager.SetValue("Date", btnEndDate.getText().toString());
                FragmentManager fm = getFragmentManager();
                FragmentDpDialog dialogFragment = new FragmentDpDialog ();
                dialogFragment.setTargetFragment(fragment, DATE_DIALOG_ID);
                dialogFragment.show(fm, getResources().getString(R.string.end_date));
            }

        });
        Button btnAccountSearch = (Button)vw.findViewById(R.id.btnAccountSearch);
        btnAccountSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long accountId = 0;
                if(selection == 0){//Customers
                    Customer customer = customers.get(pstn);
                    accountId = customer.Id;
                }
                else if(selection == 1){//Vendors
                    Vendor vendor = vendors.get(pstn);
                    accountId = vendor.Id;
                }
                Button btnBeginDate = (Button)vw.findViewById(R.id.btnBeginDate);
                Button btnEndDate = (Button)vw.findViewById(R.id.btnEndDate);
                AccountFlowRequestModel afrm = new AccountFlowRequestModel();
                afrm.AccountId = accountId;
                String[] aryBegin = btnBeginDate.getText().toString().split("\\.");
                afrm.BeginDateDay = aryBegin[0];
                afrm.BeginDateMonth = aryBegin[1];
                afrm.BeginDateYear = aryBegin[2];
                String[] aryEnd = btnEndDate.getText().toString().split("\\.");
                afrm.EndDateDay = aryEnd[0];
                afrm.EndDateMonth = aryEnd[1];
                afrm.EndDateYear = aryEnd[2];
                afrm.Token = token;
                restService.getService().GetAccountFlows(afrm, new Callback<AccountFlowListModel>() {
                    @Override
                    public void success(AccountFlowListModel prm, Response response) {
                        if(prm.IsSuccess){
                            List<AccountFlowModel> accounts = prm.Data;
                            ArrayList<AccountFlowModel> ary = new ArrayList<AccountFlowModel>(accounts);
                            AccountFlowAdapter adapter = new AccountFlowAdapter(context, ary);
                            ListView listView = (ListView)getView().findViewById(R.id.lvAccountFlows);
                            if(listView.getHeaderViewsCount() == 0){
                                View header = inflater.inflate(R.layout.layout_accountflow_header, listView, false);
                                listView.addHeaderView(header);
                            }
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
            }
        });
        // Inflate the layout for this fragment
        return vw;
    }
}
