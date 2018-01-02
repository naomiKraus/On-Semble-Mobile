package com.onsemble.onsemble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;
import org.json.*;
import java.lang.Object.*;
public class MainActivity extends AppCompatActivity  {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Config.FB_URL + "/employees/" + session.getUserId() + ".json",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        Log.e("AsyncHttpClient", "response = " + response);
                        Toast.makeText(getApplicationContext(),
                                response, Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                        Log.d("Success", "response = " + response);
                        Gson gson = new GsonBuilder().create();
                        User user = gson.fromJson(response, User.class);
                        session.setWorkingStatus(user.workingStatus);
                    }
                });

    }
    private void logoutUser() {
        session.setLogin(false);
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
