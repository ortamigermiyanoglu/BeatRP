package com.aresrd.android.beatrp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.aresrd.android.beatrp.MenuActionItem.ITEM3;

public class MenuActivity extends AppCompatActivity {


    Context context = null;
    Toolbar toolbar = null;
    SessionManager sessionManager = null;
    static RestService restService = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        context = getApplicationContext();
        sessionManager = new SessionManager(context);
        sessionManager.SetValue("MenuItem", R.id.home + "");
        restService = new RestService();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            CharSequence text = "settings tıklandı";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return true;
        } else if (id == R.id.logout) {
            String uData = sessionManager.GetValue("UserData");
            if(uData!=null && uData.length()>0){
                Gson gson = new Gson();
                UserData userData = gson.fromJson(uData,UserData.class);
                String token = userData.Token;
                if(token.length()>0){
                    restService.getService().Logout(token, new Callback<String>() {
                        @Override
                        public void success(String s, Response response) {

                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
            }
            sessionManager.SetValue("UserData","");
            sessionManager.SetValue("LangCode","");
            Intent intent = new Intent(context,LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}
