package com.aresrd.android.beatrp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDpDialog extends DialogFragment {

    Context context = null;
    SessionManager sessionManager = null;
    String type = "";
    View rootView = null;
    Fragment fragment = null;
    DatePicker dpDate = null;

    public FragmentDpDialog() {
        fragment = this;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        sessionManager = new SessionManager(context);
        rootView = inflater.inflate(R.layout.fragment_dp_dialog, container, false);
        type = sessionManager.GetValue("DateType");
        getDialog().setTitle(type);
        dpDate = (DatePicker)rootView.findViewById(R.id.dpDate);
        String date = sessionManager.GetValue("Date");
        String[] ary = date.split("\\.");
        int day = Integer.parseInt(ary[0]);
        int month = Integer.parseInt(ary[1]) - 1;
        int year = Integer.parseInt(ary[2]);
        dpDate.init(year, month, day, null);
        Button btnDismissDate = (Button) rootView.findViewById(R.id.btnDismissDate);
        btnDismissDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button btnSetDate = (Button) rootView.findViewById(R.id.btnSetDate);
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = dpDate.getYear() + "";
                int mnth = dpDate.getMonth() + 1;
                String month = (mnth + "").length() == 1 ? "0" + mnth : mnth + "";
                String day = (dpDate.getDayOfMonth() + "").length() == 1 ? "0" + dpDate.getDayOfMonth() : dpDate.getDayOfMonth() + "";
                String date = day + "." + month + "." + year;
                if(type.equals(getResources().getString(R.string.begin_date))){
                    Button btnBeginDate = (Button)fragment.getTargetFragment().getView().findViewById(R.id.btnBeginDate);
                    btnBeginDate.setText(date);
                }
                else if(type.equals(getResources().getString(R.string.begin_date_invoice))){
                    Button btnBeginDate = (Button)fragment.getTargetFragment().getView().findViewById(R.id.btnBeginDateInvoice);
                    btnBeginDate.setText(date);
                }
                else if(type.equals(getResources().getString(R.string.end_date))){
                    Button btnEndDate = (Button)fragment.getTargetFragment().getView().findViewById(R.id.btnEndDate);
                    btnEndDate.setText(date);
                }
                else if(type.equals(getResources().getString(R.string.end_date_invoice))){
                    Button btnEndDate = (Button)fragment.getTargetFragment().getView().findViewById(R.id.btnEndDateInvoice);
                    btnEndDate.setText(date);
                }
                dismiss();
            }
        });
        return rootView;
    }
}