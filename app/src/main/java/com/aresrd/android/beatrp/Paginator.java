package com.aresrd.android.beatrp;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by sumut on 8/1/2017.
 */

public class Paginator {

    public int TOTAL_NUMBER_OF_ITEMS;
    public int ITEMS_PER_PAGE = 5;
    public int ITEMS_REMAINING;
    public int LAST_PAGE_NUMBER;
    public int TOTAL_PAGE_NUMBER;

    Context context = null;

    public Paginator(Context context){

        this.context = context;
        SessionManager sessionManager = new SessionManager(context);
        String uData = sessionManager.GetValue("UserData");
        if( uData != null && uData.length() > 0){
            Gson gson = new Gson();
            UserData userData = gson.fromJson(uData,UserData.class);
            String token = userData.Token;
            if(token.length()>0){
                CustomerCountRequestModel model = new CustomerCountRequestModel();
                model.Token = token;
                model.CompanyId = userData.CompanyId;
                MenuActivity.restService.getService().GetCustomerCountByCompany(model, new Callback<CountReturnModel>() {
                    @Override
                    public void success(CountReturnModel crm, Response response) {
                        TOTAL_NUMBER_OF_ITEMS  = (int) crm.Count;

                        ITEMS_REMAINING = TOTAL_NUMBER_OF_ITEMS % ITEMS_PER_PAGE;
                        LAST_PAGE_NUMBER = TOTAL_NUMBER_OF_ITEMS / ITEMS_PER_PAGE;
                        TOTAL_PAGE_NUMBER = TOTAL_NUMBER_OF_ITEMS /ITEMS_PER_PAGE;
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }

        }

    }

    public Context getContext() {
        return context;
    }

    public ArrayList<Customer> pageGenerator (int currentPage, ArrayList<Customer> mCustomerArray){
        int startItem = currentPage * ITEMS_PER_PAGE;
        int numberOfData = ITEMS_PER_PAGE;

        ArrayList<Customer> pageData = new ArrayList<>();

        if ( currentPage == LAST_PAGE_NUMBER && ITEMS_REMAINING > 0 ){

            for ( int i = startItem; i< startItem + ITEMS_REMAINING; i++){
                pageData.add(mCustomerArray.get(i));
            }
        } else {
            for ( int i = startItem; i<startItem + numberOfData; i++){
                pageData.add(mCustomerArray.get(i));
            }
        }
        return pageData;
    }





}
