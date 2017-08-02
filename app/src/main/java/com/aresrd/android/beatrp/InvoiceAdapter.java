package com.aresrd.android.beatrp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ramadan on 27.01.2017.
 */

public class InvoiceAdapter extends ArrayAdapter<InvoiceSummaryModel> {
    SessionManager sessionManager = null;
    InvoiceSummaryModel invoice = null;

    public InvoiceAdapter(Context context, ArrayList<InvoiceSummaryModel> invoices) {
        super(context, 0, invoices);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        invoice = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_lv_invoice, parent, false);
        }
        TextView lblDateInvoice = (TextView)convertView.findViewById(R.id.lblDateInvoice);
        TextView lblDocumentNumberInvoice = (TextView)convertView.findViewById(R.id.lblDocumentNumberInvoice);
        TextView lblAccountNameInvoice = (TextView)convertView.findViewById(R.id.lblAccountNameInvoice);
        TextView lblExplanationInvoice = (TextView)convertView.findViewById(R.id.lblExplanationInvoice);
        TextView lblGrandTotal = (TextView)convertView.findViewById(R.id.lblGrandTotal);
        lblDateInvoice.setText(invoice.DocumentDateDay + "." + invoice.DocumentDateMonth + "." + invoice.DocumentDateYear);
        lblDocumentNumberInvoice.setText(invoice.DocumentNumber);
        lblAccountNameInvoice.setText(invoice.AccountName);
        lblExplanationInvoice.setText(invoice.Description);
        sessionManager = new SessionManager(getContext());
        String langCode = sessionManager.GetValue("LangCode");
        String splitChar = langCode.equals("TR") ? "," : ".";
        String total = ConvertFloatToStr(invoice.GrandTotal, splitChar);
        lblGrandTotal.setText(total);
        return convertView;
    }

    private String ConvertFloatToStr(float number, String splitChar){
        String str = number + "";
        String splitter = str.contains(",") ? "\\," : "\\.";
        String[] parts = str.split(splitter);
        String firstPart = parts[0];
        String secondPart = parts[1];
        if(secondPart.length() == 1){
            str = firstPart + splitChar + secondPart + "0";
        } else if(secondPart.length() >= 2){
            str = firstPart + splitChar + secondPart.substring(0,2);
        }
        return str;
    }
}
