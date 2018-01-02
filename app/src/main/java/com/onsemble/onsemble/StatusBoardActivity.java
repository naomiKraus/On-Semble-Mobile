package com.onsemble.onsemble;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.*;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class StatusBoardActivity extends AppCompatActivity {
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_board);
        session = new SessionManager(getApplicationContext());
        final ArrayList<String> employees = new ArrayList<String>();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Config.FB_URL + "/refs/employees/" + session.getUserId() + "/statusBoardCanSee.json",
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
                        User[] users = gson.fromJson(response, User[].class);
                        ArrayList<User> arrayOfUsers = new ArrayList<User>();
                        UsersAdapter adapter = new UsersAdapter(getApplicationContext(), arrayOfUsers);
                        for(User user : users)
                        {
                            adapter.add(user);
                        }

                    }
         });
    }
}
