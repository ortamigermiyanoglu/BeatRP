package com.aresrd.android.beatrp;

/**
 * Created by Ramadan on 26.12.2016.
 */

public class Customer extends AccountHolder {
    public String TaxOffice;
    public String TaxNumber;
    public String Address;
    public String City;
    public String Region;
    public String Country;
    public String PostalCode;
    public String PhoneNumber;
    public String FaxNumber;
    public String EMail;
    public String WebUrl;
    public int InvoicePaymentDueDate;

    public Customer()
    {
        AccountHolderType = AccountHolderTypes.Cust;
    }
}
