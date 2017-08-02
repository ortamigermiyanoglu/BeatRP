package com.aresrd.android.beatrp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    // UI references.
    private EditText txtEmail;
    private EditText txtPassword;
    Context context = null;
    SessionManager sessionManager = null;
    String langCode = "TR";
    RestService restService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        sessionManager = new SessionManager(context);
        restService = new RestService();
        setContentView(R.layout.activity_login);
        String langCode = Locale.getDefault().getLanguage().toUpperCase();
        langCode = langCode.equals("TR") ? "TR" : "EN";
        String langCodeSaved = sessionManager.GetValue("LangCode");
        if(langCodeSaved != null && langCodeSaved.length() > 0){
            if(langCode.equals(langCodeSaved)){
                String uData = "";
                try{
                    uData = sessionManager.GetValue("UserData");
                    if(uData!=null && uData.length()>0){
                        Gson gson = new Gson();
                        UserData userData = gson.fromJson(uData,UserData.class);
                        String token = userData.Token;
                        if(token.length()>0){
                            Intent intent = new Intent(context,MenuActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                catch(Exception ex){

                }
            }
            else{
                sessionManager.SetValue("UserData","");
                sessionManager.SetValue("LangCode",langCode);
            }
        }
        else{
            sessionManager.SetValue("LangCode",langCode);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String uData = "";
        try{
            uData = sessionManager.GetValue("UserData");
            if(uData!=null && uData.length()>0){
                Gson gson = new Gson();
                UserData userData = gson.fromJson(uData,UserData.class);
                String token = userData.Token;
                if(token.length()>0){
                    Intent intent = new Intent(context,MenuActivity.class);
                    startActivity(intent);
                }
            }
        }
        catch(Exception ex){

        }
    }

    public void SignUp(View view){

        EditText email = (EditText) findViewById(R.id.txtEmail);
        //Intent intent = new Intent(this, SignUpActivity.class);
        //String strEmail = email.getText().toString();
        //intent.putExtra(Intent.EXTRA_TEXT,strEmail);
        //EditText pw = (EditText) findViewById(R.id.txtPassword);
        //String strPw = pw.getText().toString();
        //intent.putExtra(Intent.EXTRA_TEXT,strPw);
        //startActivity(intent);
    }

    public void SignIn(View view){
        EditText txtUsername=(EditText)findViewById(R.id.txtEmail);
        EditText txtPassword=(EditText)findViewById(R.id.txtPassword);
        UserLoginModel ulm = new UserLoginModel();
        ulm.languageCode = langCode;
        ulm.password=txtPassword.getText().toString();
        ulm.username=txtUsername.getText().toString();
        restService.getService().Login(ulm, new Callback<UserData>() {
            @Override
            public void success(UserData prm, Response response) {
                if(prm.IsSuccess){
                    Gson gson = new Gson();
                    String prmJson = gson.toJson(prm);
                    sessionManager.SetValue("UserData",prmJson);
                    Intent intent = new Intent(context,MenuActivity.class);
                    startActivity(intent);
                }
                else{
                    CharSequence text = prm.ErrorMessage;
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                CharSequence text = getResources().getString(R.string.service_error);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}