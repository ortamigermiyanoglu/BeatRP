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
public class FragmentCustomerEdit extends Fragment {

    SessionManager sessionManager = null;
    Context context = null;

    LinearLayout layout1, layout2, layout3, layout4, layout5, layout6
            ,layout7, layout8, layout9, layout10, layout11, layout12
            ,layout13, layout14, layout15;


    public FragmentCustomerEdit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        sessionManager = new SessionManager(context);
        String customerId = sessionManager.GetValue("customerId");
        String header = sessionManager.GetValue("header");
        final View rootView = inflater.inflate(R.layout.fragment_customer_edit, container, false);


        layout1 = (LinearLayout) rootView.findViewById(R.id.cEdit1);
        layout2 = (LinearLayout) rootView.findViewById(R.id.cEdit2);
        layout3 = (LinearLayout) rootView.findViewById(R.id.cEdit3);
        layout4 = (LinearLayout) rootView.findViewById(R.id.cEdit4);
        layout5 = (LinearLayout) rootView.findViewById(R.id.cEdit5);
        layout6 = (LinearLayout) rootView.findViewById(R.id.cEdit6);
        layout7 = (LinearLayout) rootView.findViewById(R.id.cEdit7);
        layout8 = (LinearLayout) rootView.findViewById(R.id.cEdit8);
        layout9 = (LinearLayout) rootView.findViewById(R.id.cEdit9);
        layout10 = (LinearLayout) rootView.findViewById(R.id.cEdit10);
        layout11 = (LinearLayout) rootView.findViewById(R.id.cEdit11);
        layout12 = (LinearLayout) rootView.findViewById(R.id.cEdit12);
        layout13 = (LinearLayout) rootView.findViewById(R.id.cEdit13);
        layout14 = (LinearLayout) rootView.findViewById(R.id.cEdit14);
        layout15 = (LinearLayout) rootView.findViewById(R.id.cEdit15);



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
        TextView hdnCustomerId = (TextView)rootView.findViewById(R.id.hdnCustomerId);
        hdnCustomerId.setText(customerId);
        //Header
        TextView lblCustomerName = (TextView)rootView.findViewById(R.id.lblCustomerName);
        lblCustomerName.setText(header);
        if(customerId !=  "0"){
            String cust = sessionManager.GetValue("customer");
            Gson gson = new Gson();
            Customer customer = gson.fromJson(cust,Customer.class);
            //Name/Title
            EditText txtCustomerName = (EditText)rootView.findViewById(R.id.txtCustomerName);
            txtCustomerName.setText(customer.Name);
            //Customer Code
            EditText txtCustomerCode = (EditText)rootView.findViewById(R.id.txtCustomerCode);
            txtCustomerCode.setText(customer.AccountHolderCode);
            //Tax Office
            EditText txtTaxOffice = (EditText)rootView.findViewById(R.id.txtCustomerTaxOffice);
            txtTaxOffice.setText(customer.TaxOffice);
            //Tax Number
            EditText txtTaxNumber = (EditText)rootView.findViewById(R.id.txtCustomerTaxNumber);
            txtTaxNumber.setText(customer.TaxNumber);
            //Phone
            EditText txtPhone = (EditText)rootView.findViewById(R.id.txtCustomerPhone);
            txtPhone.setText(customer.PhoneNumber);
            //Email
            EditText txtEmail = (EditText)rootView.findViewById(R.id.txtCustomerEmail);
            txtEmail.setText(customer.EMail);
            //Web URL
            EditText txtWebUrl = (EditText)rootView.findViewById(R.id.txtCustomerWebUrl);
            txtWebUrl.setText(customer.WebUrl);
            //Address
            EditText txtAddress = (EditText)rootView.findViewById(R.id.txtCustomerAddress);
            txtAddress.setText(customer.Address);
            //Province
            EditText txtProvince = (EditText)rootView.findViewById(R.id.txtCustomerProvince);
            txtProvince.setText(customer.City);
            //Region
            EditText txtAreaState = (EditText)rootView.findViewById(R.id.txtCustomerAreaState);
            txtAreaState.setText(customer.Region);
            //Country
            EditText txtCountry = (EditText)rootView.findViewById(R.id.txtCustomerCountry);
            txtCountry.setText(customer.Country);
            //Postal Code
            EditText txtPostalZipCode = (EditText)rootView.findViewById(R.id.txtCustomerPostalZipCode);
            txtPostalZipCode.setText(customer.PostalCode);


              //InvoicePaymentDueDate
            EditText txtInvoicePaymentDueDate = (EditText) rootView.findViewById(R.id.txtInvoicePaymentDueDate);
            txtInvoicePaymentDueDate.setText(customer.InvoicePaymentDueDate+"");
//
            //CreditOverFlowAction
            EditText txtCreditLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtCreditLimitOverFlowAction_customer);
            txtCreditLimitOverFlowAction.setText(customer.CreditLimitOverFlowAction+"");
//
//            DebitLimitOverFlowAction
            EditText txtDebitLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtDebitLimitOverFlowAction_customer);
            txtDebitLimitOverFlowAction.setText(customer.DebitLimitOverFlowAction+"");



        }





        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.btnSaveCustomer);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtCustomerName = (EditText) rootView.findViewById(R.id.txtCustomerName);
                String name = txtCustomerName.getText().toString();
                if(name.trim().length() == 0){
                    Toast.makeText(context, R.string.name_title_required, Toast.LENGTH_LONG).show();
                    return;
                }
                TextView hdnCustomerId = (TextView) rootView.findViewById(R.id.hdnCustomerId);
                EditText txtTaxOffice = (EditText) rootView.findViewById(R.id.txtCustomerTaxOffice);
                EditText txtTaxNumber = (EditText) rootView.findViewById(R.id.txtCustomerTaxNumber);
                EditText txtPhone = (EditText) rootView.findViewById(R.id.txtCustomerPhone);
                EditText txtEmail = (EditText) rootView.findViewById(R.id.txtCustomerEmail);
                EditText txtWebUrl = (EditText) rootView.findViewById(R.id.txtCustomerWebUrl);
                EditText txtAddress = (EditText) rootView.findViewById(R.id.txtCustomerAddress);
                EditText txtProvince = (EditText) rootView.findViewById(R.id.txtCustomerProvince);
                EditText txtAreaState = (EditText) rootView.findViewById(R.id.txtCustomerAreaState);
                EditText txtCountry = (EditText) rootView.findViewById(R.id.txtCustomerCountry);
                EditText txtPostalZipCode = (EditText) rootView.findViewById(R.id.txtCustomerPostalZipCode);
                EditText txtCustomerCode = (EditText) rootView.findViewById(R.id.txtCustomerCode);
                EditText txtInvoicePaymentDueDate = (EditText) rootView.findViewById(R.id.txtInvoicePaymentDueDate);
                EditText txtCreditLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtCreditLimitOverFlowAction_customer);
                EditText txtDebitLimitOverFlowAction = (EditText) rootView.findViewById(R.id.txtDebitLimitOverFlowAction_customer);



                String uData = sessionManager.GetValue("UserData");
                if(uData!=null && uData.length()>0){
                    Gson gson = new Gson();
                    UserData userData = gson.fromJson(uData,UserData.class);
                    String token = userData.Token;
                    if(token.length()>0){
                        CustomerModel cm = new CustomerModel();
                        cm.Token = token;
                        Customer customer = new Customer();

                        customer.Address=txtAddress.getText().toString();
                        customer.City = txtProvince.getText().toString();
                        customer.Country = txtCountry.getText().toString();
                        customer.EMail = txtEmail.getText().toString();
                        customer.FaxNumber = "";
                        customer.PhoneNumber = txtPhone.getText().toString();
                        customer.PostalCode = txtPostalZipCode.getText().toString();
                        customer.Region = txtAreaState.getText().toString();
                        customer.TaxNumber = txtTaxNumber.getText().toString();
                        customer.TaxOffice = txtTaxOffice.getText().toString();
                        customer.WebUrl = txtWebUrl.getText().toString();
                        customer.Name = name;
                        customer.AccountHolderCode = txtCustomerCode.getText().toString();
                        customer.Balance = 0F;
                        customer.CompanyId = userData.CompanyId;
                        customer.Id = Long.parseLong(hdnCustomerId.getText().toString());
                        customer.OwnerBranchId = userData.CurrentBranchId;
                        customer.AccountHolderType = AccountHolderTypes.Cust;
                        customer.RecordStatus = 1;
                        customer.Scope = Scopes.Company;
                        customer.CreditLimitOverFlowAction = Integer.parseInt(String.valueOf(txtCreditLimitOverFlowAction.getText()));
                        customer.DebitLimitOverFlowAction = Integer.parseInt(String.valueOf(txtDebitLimitOverFlowAction.getText()));
                        customer.InvoicePaymentDueDate = Integer.parseInt(String.valueOf(txtInvoicePaymentDueDate.getText()));


                        cm.Data = customer;
                        MenuActivity.restService.getService().SaveCustomer(cm, new Callback<ServiceReturnModel>() {
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
                                Toast.makeText(context, getResources().getString(R.string.service_error), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        MenuActivity.restService.getService().Logout(token, new Callback<String>() {
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
