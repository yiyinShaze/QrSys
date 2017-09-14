package com.example.mheljohn.qrsys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final EditText etFName = (EditText) findViewById(R.id.firstNameText);
        final EditText etLName = (EditText) findViewById(R.id.lastNameText);
        final EditText etUsername = (EditText) findViewById(R.id.usernameText);
        final EditText etPassword = (EditText) findViewById(R.id.passwordText);
        final EditText etNumber = (EditText) findViewById(R.id.contactNoText);
        final EditText etAddress = (EditText) findViewById(R.id.addressText);
        final EditText etEmail = (EditText) findViewById(R.id.emailText);

        final Button bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bRegister.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Registering...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                final String fName = etFName.getText().toString();
                final String lName = etLName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String cNumber = etNumber.getText().toString();
                final String address = etAddress.getText().toString();
                final String email = etEmail.getText().toString();
                final String loc = "0";
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            String success = jsonResponse.getString("success");
                            if (success=="0") {
                                bRegister.setEnabled(true);
                                progressDialog.dismiss();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                intent.putExtra("username", username);
                                intent.putExtra("fname", fName);
                                intent.putExtra("lname", lName);
                                RegisterActivity.this.startActivity(intent);
                                finish();
                            } else if(success=="1") {
                                bRegister.setEnabled(true);
                                progressDialog.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Email is already taken")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            } else if(success=="2") {
                                bRegister.setEnabled(true);
                                progressDialog.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Username is already taken")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            } else if(success=="3") {
                                bRegister.setEnabled(true);
                                progressDialog.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Both Username and Email is already taken")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(fName, lName, username, password,cNumber,address,email,loc, responseListener);
                registerRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
