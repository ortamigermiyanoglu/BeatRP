package com.aresrd.android.beatrp;

/**
 * Created by Ramadan on 25.12.2016.
 */
import com.google.gson.JsonObject;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
public interface IService {

    //region User
    //User Login
    @POST("/User/Login")
    public void Login(@Body UserLoginModel prm, Callback<UserData> callback);

    @GET("/User/Logout/{prm}")
    public void Logout(@Path("prm") String prm, Callback<String> callback);
    //endregion

    //region Customer
    @GET("/Customer/GetAllCustomers/{prm}")
    public void GetAllCustomers(@Path("prm") String prm, Callback<CustomerListModel> callback);

    @POST("/Customer/SaveCustomer")
    public void SaveCustomer(@Body CustomerModel prm, Callback<ServiceReturnModel> callback);

    //get customer count
    @POST("/Customer/GetCustomerCountByCompany")
    public void GetCustomerCountByCompany(@Body CustomerCountRequestModel prm, Callback<CountReturnModel> callback);


    //endregion

    //region Vendor
    @GET("/Vendor/GetAllVendors/{prm}")
    public void GetAllVendors(@Path("prm") String prm, Callback<VendorListModel> callback);

    @POST("/Vendor/SaveVendor")
    public void SaveVendor(@Body VendorModel prm, Callback<ServiceReturnModel> callback);
    //endregion

    //region AccountFlow
    @POST("/AccountFlow/GetAccountFlows")
    public void GetAccountFlows(@Body AccountFlowRequestModel prm, Callback<AccountFlowListModel> callback);
    //endregion

    //region Invoice
    @POST("/Invoice/GetInvoiceSummaryList")
    public void GetInvoiceSummaryList(@Body InvoiceListRequestModel prm, Callback<InvoiceSummaryListModel> callback);
    //endregion
}