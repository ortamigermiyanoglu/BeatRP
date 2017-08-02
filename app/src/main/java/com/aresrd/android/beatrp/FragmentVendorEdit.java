package com.aresrd.android.beatrp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVendorEdit extends Fragment {

    SessionManager sessionManager = null;
    Context context = null;
    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6
            ,layout7, layout8, layout9, layout10, layout11, layout12
            ,layout13, layout14, layout15;

    public FragmentVendorEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        sessionManager = new SessionManager(context);

        String vendorId = sessionManager.GetValue("vendorId");
        String header = sessionManager.GetValue("header");
        final View rootView = inflater.inflate(R.layout.fragment_vendor_edit, container, false);


        layout1 = (LinearLayout) rootView.findViewById(R.id.vEdit1);
        layout2 = (LinearLayout) rootView.findViewById(R.id.vEdit2);
        layout3 = (LinearLayout) rootView.findViewById(R.id.vEdit3);
        layout4 = (LinearLayout) rootView.findViewById(R.id.vEdit4);
        layout5 = (LinearLayout) rootView.findViewById(R.id.vEdit5);
        layout6 = (LinearLayout) rootView.findViewById(R.id.vEdit6);
        layout7 = (LinearLayout) rootView.findViewById(R.id.vEdit7);
        layout8 = (LinearLayout) rootView.findViewById(R.id.vEdit8);
        layout9 = (LinearLayout) rootView.findViewById(R.id.vEdit9);
        layout10 = (LinearLayout) rootView.findViewById(R.id.vEdit10);
        layout11 = (LinearLayout) rootView.findViewById(R.id.vEdit11);
        layout12 = (LinearLayout) rootView.findViewById(R.id.vEdit12);
        layout13 = (LinearLayout) rootView.findViewById(R.id.vEdit13);
        layout14 = (LinearLayout) rootView.findViewById(R.id.vEdit14);
        layout15 = (LinearLayout) rootView.findViewById(R.id.vEdit15);

        layout1.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout2.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout3.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout4.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout5.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout6.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout7.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout8.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout9.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout10.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout11.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout12.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout13.setBackgroundResource(R.drawable.lv_customer_background_odd_item);
        layout14.setBackgroundResource(R.drawable.lv_customer_background_even_item);
        layout15.setBackgroundResource(R.drawable.lv_customer_background_odd_item);








        //CustomerId
        TextView hdnVendorId = (TextView)rootView.findViewById(R.id.hdnVendorId);
        hdnVendorId.setText(vendorId);
        //Header
        TextView lblVendorName = (TextView)rootView.findViewById(R.id.lblVendorName);
        lblVendorName.setText(header);
        if(vendorId != "0"){
            String vndr = sessionManager.GetValue("vendor");
            Gson gson = new Gson();
            Vendor vendor = gson.fromJson(vndr,Vendor.class);
            //Name/Title
            EditText txtVendorName = (EditText)rootView.findViewById(R.id.txtVendorName);
            txtVendorName.setText(vendor.Name);
            //Vendor Code
            EditText txtVendorCode = (EditText)rootView.findViewById(R.id.txtVendorCode);
            txtVendorCode.setText(vendor.AccountHolderCode);
            //Tax Office
            EditText txtVendorTaxOffice = (EditText)rootView.findViewById(R.id.txtVendorTaxOffice);
            txtVendorTaxOffice.setText(vendor.TaxOffice);
            //Tax Number
            EditText txtVendorTaxNumber = (EditText)rootView.findViewById(R.id.txtVendorTaxNumber);
            txtVendorTaxNumber.setText(vendor.TaxNumber);
            //Phone
            EditText txtVendorPhone = (EditText)rootView.findViewById(R.id.txtVendorPhone);
            txtVendorPhone.setText(vendor.PhoneNumber);
            //Email
            EditText txtVendorEmail = (EditText)rootView.findViewById(R.id.txtVendorEmail);
            txtVendorEmail.setText(vendor.EMail);
            //Web URL
            EditText txtVendorWebUrl = (EditText)rootView.findViewById(R.id.txtVendorWebUrl);
            txtVendorWebUrl.setText(vendor.WebUrl);
            //Address
            EditText txtVendorAddress = (EditText)rootView.findViewById(R.id.txtVendorAddress);
            txtVendorAddress.setText(vendor.Address);
            //Province
            EditText txtVendorProvince = (EditText)rootView.findViewById(R.id.txtVendorProvince);
            txtVendorProvince.setText(vendor.City);
            //Region
            EditText txtVendorAreaState = (EditText)rootView.findViewById(R.id.txtVendorAreaState);
            txtVendorAreaState.setText(vendor.Region);
            //Country
            EditText txtVendorCountry = (EditText)rootView.findViewById(R.id.txtVendorCountry);
            txtVendorCountry.setText(vendor.Country);
            //Postal Code
            EditText txtVendorPostalZipCode = (EditText)rootView.findViewById(R.id.txtVendorPostalZipCode);
            txtVendorPostalZipCode.setText(vendor.PostalCode);

            //Credit Limit Overflow
            EditText txtCreditLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtCreditLimitOverFlowAction);
            txtCreditLimitOverFlowAction.setText(vendor.CreditLimitOverFlowAction+"");

            //Debit LimitOver Flow
            EditText txtDebitLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtDebitLimitOverFlowAction);
            txtDebitLimitOverFlowAction.setText(vendor.DebitLimitOverFlowAction+"");

            //Invoice Collection Due Date
            EditText txtInvoiceCollectionDueDate = (EditText) rootView.findViewById(R.id.txtInvoiceCollectionDueDate);
            txtInvoiceCollectionDueDate.setText(vendor.InvoiceCollectionDueDate+"");
        }


        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.btnSaveVendor);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtVendorName = (EditText) rootView.findViewById(R.id.txtVendorName);
                String name = txtVendorName.getText().toString();
                if(name.trim().length() == 0){
                    Toast.makeText(context, R.string.name_title_required, Toast.LENGTH_LONG).show();
                    return;
                }
                TextView hdnVendorId = (TextView) rootView.findViewById(R.id.hdnVendorId);
                EditText txtTaxOffice = (EditText) rootView.findViewById(R.id.txtVendorTaxOffice);
                EditText txtTaxNumber = (EditText) rootView.findViewById(R.id.txtVendorTaxNumber);
                EditText txtPhone = (EditText) rootView.findViewById(R.id.txtVendorPhone);
                EditText txtEmail = (EditText) rootView.findViewById(R.id.txtVendorEmail);
                EditText txtWebUrl = (EditText) rootView.findViewById(R.id.txtVendorWebUrl);
                EditText txtAddress = (EditText) rootView.findViewById(R.id.txtVendorAddress);
                EditText txtProvince = (EditText) rootView.findViewById(R.id.txtVendorProvince);
                EditText txtAreaState = (EditText) rootView.findViewById(R.id.txtVendorAreaState);
                EditText txtCountry = (EditText) rootView.findViewById(R.id.txtVendorCountry);
                EditText txtPostalZipCode = (EditText) rootView.findViewById(R.id.txtVendorPostalZipCode);
                EditText txtVendorCode = (EditText) rootView.findViewById(R.id.txtVendorCode);

                EditText txtCreditLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtCreditLimitOverFlowAction);
                EditText txtDebitLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtDebitLimitOverFlowAction);
                EditText txtInvoiceCollectionDueDate = (EditText) rootView.findViewById(R.id.txtInvoiceCollectionDueDate);






                String uData = sessionManager.GetValue("UserData");
                if(uData!=null && uData.length()>0){
                    Gson gson = new Gson();
                    UserData userData = gson.fromJson(uData,UserData.class);
                    String token = userData.Token;
                    RestService restService = new RestService();
                    if(token.length()>0){
                        VendorModel vm = new VendorModel();
                        vm.Token = token;
                        Vendor vendor = new Vendor();
                        vendor.Id = Long.parseLong(hdnVendorId.getText().toString());
                        vendor.CompanyId = userData.CompanyId;
                        vendor.OwnerBranchId = userData.CurrentBranchId;
                        vendor.Scope = Scopes.Company;
                        vendor.AccountHolderType = AccountHolderTypes.Vendor;
                        vendor.AccountHolderCode = txtVendorCode.getText().toString();
                        vendor.RecordStatus = 1;
                        vendor.SourceSystem = "";
                        vendor.SourceRecordId = "";
                        vendor.Name = name;
                        vendor.Balance = 0F;

                        vendor.CreditLimit = 0F;
                        vendor.DebitLimit = 0F;

                        vendor.Address=txtAddress.getText().toString();
                        vendor.City = txtProvince.getText().toString();
                        vendor.Country = txtCountry.getText().toString();
                        vendor.EMail = txtEmail.getText().toString();
                        vendor.FaxNumber = "";
                        vendor.PhoneNumber = txtPhone.getText().toString();
                        vendor.PostalCode = txtPostalZipCode.getText().toString();
                        vendor.Region = txtAreaState.getText().toString();
                        vendor.TaxNumber = txtTaxNumber.getText().toString();
                        vendor.TaxOffice = txtTaxOffice.getText().toString();
                        vendor.WebUrl = txtWebUrl.getText().toString();
                        vendor.CreditLimitOverFlowAction = Integer.parseInt(txtCreditLimitOverFlowAction.getText().toString());
                        vendor.DebitLimitOverFlowAction = Integer.parseInt(txtDebitLimitOverFlowAction.getText().toString());
                        vendor.InvoiceCollectionDueDate = Integer.parseInt(txtInvoiceCollectionDueDate.getText().toString());


                        vm.Data = vendor;

                        restService.getService().SaveVendor(vm, new Callback<ServiceReturnModel>() {
                            @Override
                            public void success(ServiceReturnModel s, Response response) {
                                if(s.IsSuccess == true){
                                    getFragmentManager().popBackStack();
                                }
                                else{
                                    Toast.makeText(context, getResources().getString(R.string.error_occured_while_customer_save), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        restService.getService().Logout(token, new Callback<String>() {
                            @Override
                            public void success(String s, Response response) {

                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                        sessionManager.SetValue("UserData","");
                        sessionManager.SetValue("LangCode","");
                        Intent intent = new Intent(context,LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

}
