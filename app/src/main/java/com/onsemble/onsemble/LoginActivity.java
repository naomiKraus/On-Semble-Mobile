package com.onsemble.onsemble;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText inputUserName;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private EditText inputCompanyName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputUserName = (EditText) findViewById(R.id.userName);
        inputPassword = (EditText) findViewById(R.id.password);
        inputCompanyName = (EditText) findViewById(R.id.companyName);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String userName = inputUserName.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String companyName = inputCompanyName.getText().toString().trim();
                if (!userName.isEmpty() && !password.isEmpty() && !companyName.isEmpty()) {
                    checkLogin(userName, password, companyName);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });

    }
    private void checkLogin(final String userName, final String password, final String companyName) {
        pDialog.setMessage("Logging in ...");
        showDialog();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("companyURL", companyName);
        params.put("password", password);
        params.put("userName", userName);
        client.get(Config.SERVER_URL + "/loginA", params,
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        hideDialog();
                        Log.e("AsyncHttpClient", "response = " + response);
                        Toast.makeText(getApplicationContext(),
                                response, Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        hideDialog();
                        Log.d("", "Login Response: " + response.toString());
                        Gson gson = new GsonBuilder().create();
                        User user = gson.fromJson(response, User.class);
                        Log.d("", "user response class: " + response.toString());
                        if (user == null) {
                            Toast.makeText(getApplicationContext(),
                                    "Authenticating problem, please contact your administrator", Toast.LENGTH_LONG).show();
                        }
                        else {
                            session.setLogin(true);
                            session.setUserId(user.id);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
        );

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

