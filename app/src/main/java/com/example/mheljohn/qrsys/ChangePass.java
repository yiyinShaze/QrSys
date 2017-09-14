package com.example.mheljohn.qrsys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.opengl.ETC1;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePass extends AppCompatActivity {

    private String currentPassword, newPassword,veriPassword;
    private String cUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        final EditText currentPass = (EditText) findViewById(R.id.etCurrent);
        final EditText newPass = (EditText) findViewById(R.id.etNew);
        final EditText verifyPass = (EditText) findViewById(R.id.etVerify);
        final Button changePass = (Button)findViewById(R.id.bChange);
        cUser = MainActivity.myBundle.getString("username");
        currentPass.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


            }
        });

        newPass.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                newPassword = newPass.getText().toString();

            }
        });

        verifyPass.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                veriPassword = verifyPass.getText().toString();
                if(newPassword.equals(veriPassword)){
                    Drawable img = ContextCompat.getDrawable(getApplicationContext(), R.drawable.check_icon);
                    verifyPass.setCompoundDrawablesWithIntrinsicBounds( null, null, img, null);
                    changePass.setEnabled(true);
                }else{

                    Drawable img = ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_icon);
                    verifyPass.setCompoundDrawablesWithIntrinsicBounds( null, null, img, null);
                    changePass.setEnabled(false);

                }
            }
        });



        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You clicked: ",Toast.LENGTH_SHORT).show();
                changePass.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(ChangePass.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                currentPassword = currentPass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {

                                String username = jsonResponse.getString("username");
                                String fname = jsonResponse.getString("fname");
                                String lname = jsonResponse.getString("lname");
                                progressDialog.dismiss();
                                final Intent intent = new Intent(ChangePass.this, MainActivity.class);

                                intent.putExtra("username", username);
                                intent.putExtra("fname", fname);
                                intent.putExtra("lname", lname);
                                progressDialog.setIndeterminate(true);
                                progressDialog.setMessage("Updating...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success = jsonResponse.getBoolean("success");

                                            if (success) {

                                                changePass.setEnabled(true);
                                                progressDialog.dismiss();

                                                ChangePass.this.startActivity(intent);
                                                finish();

                                            } else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePass.this);
                                                builder.setMessage("Update Failed")
                                                        .setNegativeButton("Retry", null)
                                                        .create()
                                                        .show();
                                                changePass.setEnabled(true);
                                                progressDialog.dismiss();
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                ChangeRequest changeRequest = new ChangeRequest(cUser, currentPassword,newPassword, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(ChangePass.this);
                                queue.add(changeRequest);

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePass.this);
                                builder.setMessage("Wrong Current Password")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                changePass.setEnabled(true);
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ChangeRequest changeRequest = new ChangeRequest(cUser, currentPassword, responseListener);
                changeRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue queue = Volley.newRequestQueue(ChangePass.this);
                queue.add(changeRequest);
            }
        });


    }

}
