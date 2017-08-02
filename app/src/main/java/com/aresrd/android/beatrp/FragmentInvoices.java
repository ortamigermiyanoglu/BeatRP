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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
public class FragmentInvoices extends Fragment {

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
    List<InvoiceSummaryModel> summaryList = null;
    Spinner cmbInvoiceTypes = null;
    Spinner cmbAccountTypesWithAll = null;
    Spinner cmbAccountsInvoice = null;
    View vw = null;
    int selectionInvoiceTypes = 0;
    int selectionAccountTypes = 0;
    int selectionAccount = 0;
    List<Customer> customers = new ArrayList<Customer>();
    List<Vendor> vendors = new ArrayList<Vendor>();

    public FragmentInvoices() {
        fragment = this;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restService = new RestService();
        context = getActivity();
        sessionManager = new SessionManager(context);
        vw = inflater.inflate(R.layout.fragment_invoices, container, false);
        cmbAccountTypesWithAll = (Spinner)vw.findViewById(R.id.cmbAccountTypesWithAll);
        cmbInvoiceTypes = (Spinner)vw.findViewById(R.id.cmbInvoiceTypes);
        cmbAccountsInvoice = (Spinner)vw.findViewById(R.id.cmbAccountsInvoice);
        String uData = sessionManager.GetValue("UserData");
        if(uData != null && uData.length() > 0){
            Gson gson = new Gson();
            userData = gson.fromJson(uData, UserData.class);
            token = userData.Token;
        }
        final Button btnBeginDateInvoice = (Button)vw.findViewById(R.id.btnBeginDateInvoice);
        final Button btnEndDateInvoice = (Button)vw.findViewById(R.id.btnEndDateInvoice);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR) + "";
        int mnth = c.get(Calendar.MONTH) + 1;
        month = (mnth + "").length() == 1 ? "0" + mnth : mnth + "";
        day = (c.get(Calendar.DAY_OF_MONTH) + "").length() == 1 ? "0" + c.get(Calendar.DAY_OF_MONTH) : c.get(Calendar.DAY_OF_MONTH) + "";
        // set current date into btnBeginDate
        btnBeginDateInvoice.setText(new StringBuilder()
                //Month is 0 based, just add 1
                .append(day).append(".")
                .append(month).append(".")
                .append(year));
        btnBeginDateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.SetValue("DateType",getResources().getString(R.string.begin_date_invoice));
                sessionManager.SetValue("Date", btnBeginDateInvoice.getText().toString());
                FragmentManager fm = getFragmentManager();
                FragmentDpDialog dialogFragment = new FragmentDpDialog ();
                dialogFragment.setTargetFragment(fragment, DATE_DIALOG_ID);
                dialogFragment.show(fm, getResources().getString(R.string.begin_date_invoice));
            }
        });
        // set current date into btnDateDate
        btnEndDateInvoice.setText(new StringBuilder()
                //Month is 0 based, just add 1
                .append(day).append(".")
                .append(month).append(".")
                .append(year));
        btnEndDateInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.SetValue("DateType",getResources().getString(R.string.end_date_invoice));
                sessionManager.SetValue("Date", btnEndDateInvoice.getText().toString());
                FragmentManager fm = getFragmentManager();
                FragmentDpDialog dialogFragment = new FragmentDpDialog ();
                dialogFragment.setTargetFragment(fragment, DATE_DIALOG_ID);
                dialogFragment.show(fm, getResources().getString(R.string.end_date_invoice));
            }

        });
        cmbInvoiceTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionInvoiceTypes = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectionInvoiceTypes = 0;
            }
        });
        final List<String> accountList = new ArrayList<String>();
        accountList.add(getResources().getString(R.string.all));
        restService.getService().GetAllVendors(token, new Callback<VendorListModel>() {
            @Override
            public void success(VendorListModel prm, Response response) {
                if(prm.IsSuccess){
                    vendors = prm.Data;
                    ArrayList<Vendor> aryVendors = new ArrayList<Vendor>(vendors);
                    for(int i=0; i < aryVendors.size(); i++){
                        accountList.add(aryVendors.get(i).Name);
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, accountList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    final Spinner cmbAccountsInvoice = (Spinner)vw.findViewById(R.id.cmbAccountsInvoice);
                    cmbAccountsInvoice.setAdapter(dataAdapter);
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
        restService.getService().GetAllCustomers(token, new Callback<CustomerListModel>() {
            @Override
            public void success(CustomerListModel prm, Response response) {
                if(prm.IsSuccess){
                    customers = prm.Data;
                    ArrayList<Customer> aryCustomers = new ArrayList<Customer>(customers);
                    for(int i=0; i < aryCustomers.size(); i++){
                        accountList.add(aryCustomers.get(i).Name);
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, accountList);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    final Spinner cmbAccountsInvoice = (Spinner)vw.findViewById(R.id.cmbAccountsInvoice);
                    cmbAccountsInvoice.setAdapter(dataAdapter);
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
        cmbAccountTypesWithAll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionAccountTypes = position;
                List<String> list = new ArrayList<String>();
                if(position == 0){//All
                    list.add(getResources().getString(R.string.all));
                    if(customers!=null){
                        for(int i=0;i<customers.size();i++){
                            list.add(customers.get(i).Name);
                        }
                    }
                    if(vendors!=null){
                        for(int i=0;i<vendors.size();i++){
                            list.add(vendors.get(i).Name);
                        }
                    }
                }
                else if(position == 1){//Customer
                    if(customers!=null){
                        for(int i=0;i<customers.size();i++){
                            list.add(customers.get(i).Name);
                        }
                    }
                }
                else if(position == 2){//Vendor
                    if(vendors!=null){
                        for(int i=0;i<vendors.size();i++){
                            list.add(vendors.get(i).Name);
                        }
                    }
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cmbAccountsInvoice.setAdapter(dataAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                List<String> list = new ArrayList<String>();
                list.add(getResources().getString(R.string.all));
                if(customers!=null){
                    for(int i=0;i<customers.size();i++){
                        list.add(customers.get(i).Name);
                    }
                }
                if(vendors!=null){
                    for(int i=0;i<vendors.size();i++){
                        list.add(vendors.get(i).Name);
                    }
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cmbAccountsInvoice.setAdapter(dataAdapter);
            }
        });
        cmbAccountsInvoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectionAccount = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectionAccount = 0;
            }
        });
        Button btnInvoiceSearch = (Button)vw.findViewById(R.id.btnInvoiceSearch);
        btnInvoiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long accountId = 0;
                int customersCount = customers.size();
                int vendorsCount = vendors.size();
                if(selectionAccountTypes == 0){//All
                    if(selectionAccount > 0){
                        if(selectionAccount <= customersCount + 1){
                            Customer customer = customers.get(selectionAccount - 1);
                            accountId = customer.Id;
                        }
                        else if(selectionAccount <= customersCount + vendorsCount + 1 && selectionAccount >= customersCount + 1){
                            Vendor vendor = vendors.get(selectionAccount - customersCount - 1);
                            accountId = vendor.Id;
                        }
                    }
                    else{
                        accountId = 0;
                    }
                }
                else if(selectionAccountTypes == 1){//Customers
                    if(selectionAccount > 0){
                        Customer customer = customers.get(selectionAccount - 1);
                        accountId = customer.Id;
                    }
                    else{
                        accountId = 0;
                    }
                }
                else if(selectionAccountTypes == 2){//Vendors
                    if(selectionAccount > 0){
                        Vendor vendor = vendors.get(selectionAccount - 1);
                        accountId = vendor.Id;
                    }
                    else{
                        accountId = 0;
                    }
                }
                Button btnBeginDate = (Button)vw.findViewById(R.id.btnBeginDateInvoice);
                Button btnEndDate = (Button)vw.findViewById(R.id.btnEndDateInvoice);
                InvoiceListRequestModel ilrm = new InvoiceListRequestModel();
                ilrm.AccountId = accountId;
                String[] aryBegin = btnBeginDateInvoice.getText().toString().split("\\.");
                ilrm.BeginDateDay = aryBegin[0];
                ilrm.BeginDateMonth = aryBegin[1];
                ilrm.BeginDateYear = aryBegin[2];
                EditText txtInvoiceNumber = (EditText)vw.findViewById(R.id.txtInvoiceNumber);
                ilrm.DocumentNumber = txtInvoiceNumber.getText().toString().length() > 0 ? txtInvoiceNumber.getText().toString() : "";
                String[] aryEnd = btnEndDateInvoice.getText().toString().split("\\.");
                ilrm.EndDateDay = aryEnd[0];
                ilrm.EndDateMonth = aryEnd[1];
                ilrm.EndDateYear = aryEnd[2];
                ilrm.InvoiceType = selectionInvoiceTypes;
                ilrm.Token = token;
                //Default
                restService.getService().GetInvoiceSummaryList(ilrm, new Callback<InvoiceSummaryListModel>() {
                    @Override
                    public void success(InvoiceSummaryListModel prm, Response response) {
                        if(prm.IsSuccess){
                            summaryList = prm.Data;
                            ArrayList<InvoiceSummaryModel> ary = new ArrayList<InvoiceSummaryModel>(summaryList);
                            InvoiceAdapter adapter = new InvoiceAdapter(context, ary);
                            ListView listView = (ListView)getView().findViewById(R.id.lvInvoices);
                            if(listView.getHeaderViewsCount() == 0){
                                View header = inflater.inflate(R.layout.layout_invoice_header, listView, false);
                                listView.addHeaderView(header);
                            }
                            listView.setAdapter(adapter);
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
            }
        });
        // Inflate the layout for this fragment
        return vw;
    }
}
