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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCustomers extends Fragment {

    Context context = null;
    SessionManager sessionManager = null;
    RestService restService = null;
    CustomerAdapter adapter;
    ListView listView;
    public View view;


    // navigation objects
    Button prevButton, nextButton;
    private int currentPage = 0;
    List<String> spinnerContentArray =  new ArrayList<>();
    Spinner pageNavigationSpinner;











    public FragmentCustomers() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_customers, container, false);

        context = getActivity();
        sessionManager = new SessionManager(context);
        restService = new RestService();
        String uData = sessionManager.GetValue("UserData");
        Gson gson = new Gson();
        UserData userData = gson.fromJson(uData,UserData.class);
        String token = userData.Token;
        restService.getService().GetAllCustomers(token, new Callback<CustomerListModel>() {
            @Override
            public void success(CustomerListModel prm, Response response) {
                if(prm.IsSuccess){
                    List<Customer> customers = prm.Data;
                    final ArrayList<Customer> ary = new ArrayList<Customer>(customers);



                    prevButton = (Button) view.findViewById(R.id.prevButton);
                    nextButton = (Button) view.findViewById(R.id.nextButton);
                    prevButton.setEnabled(false);
                    pageNavigationSpinner = (Spinner) view.findViewById(R.id.spinnerSelectPage);
                    adapter = new CustomerAdapter(context,R.layout.layout_lv_customer  ,ary);
                    for (int i = 1; i<=(adapter.paging.TOTAL_PAGE_NUMBER+1); i++){
                        spinnerContentArray.add("Page "+i);
                    }




                    ArrayAdapter<String> myNavigationAdapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_spinner_item,spinnerContentArray);

                    myNavigationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    pageNavigationSpinner.setAdapter(myNavigationAdapter);




                    listView = (ListView)getView().findViewById(R.id.lvCustomers);
                    View header = inflater.inflate(R.layout.layout_customer_header, listView, false);
                    listView.addHeaderView(header);
                    listView.setAdapter(adapter);


                    pageNavigationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            currentPage = position;

                            adapter = new CustomerAdapter(context, R.layout.layout_lv_customer,
                                    adapter.paging.pageGenerator(currentPage, ary));

                            listView.setAdapter(adapter);

                            toggleButtons();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {


                        }
                    });


                    nextButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentPage += 1;

                            adapter = new CustomerAdapter(getContext(),
                                    R.layout.layout_lv_customer, adapter.paging.pageGenerator(currentPage, ary));

                            listView.setAdapter(adapter);

                            toggleButtons();
                        }
                    });



                    prevButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            currentPage += -1;

                            adapter = new CustomerAdapter(getContext(),
                                    R.layout.layout_lv_customer, adapter.paging.pageGenerator(currentPage, ary));

                            listView.setAdapter(adapter);

                            toggleButtons();
                        }
                    });
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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.newCustomer);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentCustomerEdit fragment = new FragmentCustomerEdit();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainer, fragment);
                fragmentTransaction.addToBackStack("addCustomerEdit");
                fragmentTransaction.commit();
                String header = getResources().getString(R.string.new_customer);
                sessionManager.SetValue("header", header);
                sessionManager.SetValue("customerId","0");
                sessionManager.SetValue("MenuItem", MenuActionItem.ITEM5.ordinal()+ "");
            }
        });

        return view;
    }



    private void toggleButtons() {

        if (currentPage == adapter.paging.TOTAL_PAGE_NUMBER && adapter.paging.TOTAL_PAGE_NUMBER != 0) {
            nextButton.setEnabled(false);
            prevButton.setEnabled(true);
        } else if (currentPage == adapter.paging.TOTAL_PAGE_NUMBER && adapter.paging.TOTAL_PAGE_NUMBER == 0) {
            prevButton.setEnabled(false);
            nextButton.setEnabled(false);
        } else if (currentPage == 0 && adapter.paging.TOTAL_PAGE_NUMBER != 0) {
            prevButton.setEnabled(false);
            nextButton.setEnabled(true);
        } else {
            prevButton.setEnabled(true);
            nextButton.setEnabled(true);
        }

    }
}
