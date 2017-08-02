package com.aresrd.android.beatrp;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Ramadan on 3.01.2017.
 */

public class AccountFlowAdapter extends ArrayAdapter<AccountFlowModel> {
    SessionManager sessionManager = null;
    AccountFlowModel account = null;

    public AccountFlowAdapter(Context context, ArrayList<AccountFlowModel> accountFlows) {
        super(context, 0, accountFlows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        account = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_lv_accountflow, parent, false);
        }
        TextView lblDate = (TextView)convertView.findViewById(R.id.lblDate);
        TextView lblDocumentNumber = (TextView)convertView.findViewById(R.id.lblDocumentNumber);
        TextView lblProcess = (TextView)convertView.findViewById(R.id.lblProcess);
        TextView lblDept = (TextView)convertView.findViewById(R.id.lblDept);
        TextView lblWill = (TextView)convertView.findViewById(R.id.lblWill);
        lblDate.setText(account.FlowDateDay + "." + account.FlowDateMonth + "." + account.FlowDateYear);
        lblDocumentNumber.setText(account.TransactionNumber);
        lblProcess.setText(account.Description);
        sessionManager = new SessionManager(getContext());
        String langCode = sessionManager.GetValue("LangCode");
        String splitChar = langCode.equals("TR") ? "," : ".";
        String dept = ConvertFloatToStr(account.Debit, splitChar);
        lblDept.setText(dept);
        String will = ConvertFloatToStr(account.Credit, splitChar);
        lblWill.setText(will);
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