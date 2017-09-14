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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    private String cFirst;
    private String cLast;
    private String cUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String cName = intent.getStringExtra("nameDetail");
        String cGender = intent.getStringExtra("genderDetail");
        String cSize = intent.getStringExtra("sizeDetail");
        String cAge = intent.getStringExtra("ageDetail");
        String cHeight = intent.getStringExtra("heightDetail");
        String cWeight = intent.getStringExtra("weightDetail");
        String cChest = intent.getStringExtra("chestDetail");
        String cWaist = intent.getStringExtra("waistDetail");
        String cFabric = intent.getStringExtra("fabricDetail");
        final String cPrice = intent.getStringExtra("priceDetail");
        final String cQr = intent.getStringExtra("qrDetail");
        String cStock = intent.getStringExtra("stockDetail");
        String cDressUrl = intent.getStringExtra("imageDetail");
        final String cLoc = intent.getStringExtra("locDetail");

        cFirst = MainActivity.myBundle.getString("fname");
        cLast = MainActivity.myBundle.getString("lname");
        cUser = MainActivity.myBundle.getString("username");
        final String cFullName = cFirst +" "+ cLast;


        ImageView detailImageView = (ImageView) findViewById(R.id.imageDetail);
        TextView detailName =(TextView)findViewById(R.id.nameDetail);
        TextView detailPrice =(TextView)findViewById(R.id.priceDetail);
        TextView detailStock =(TextView)findViewById(R.id.stockDetail);
        final Button reserveDetail = (Button) findViewById(R.id.ReserveDetail);
        Picasso.with(this).load("http://jamthesis.com/dressImages/" + cDressUrl).into(detailImageView);

        detailName.setText(cName);
        detailPrice.setText(cPrice);
        detailStock.setText(cStock);

        reserveDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reserveDetail.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(DetailsActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Reserving...");
                progressDialog.show();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                                reserveDetail.setEnabled(true);
                                progressDialog.dismiss();


                                Intent qr = new Intent(DetailsActivity.this,QrActivity.class);
                                qr.putExtra("qrCode",cQr);
                                qr.putExtra("nameDetail",cName);
                                DetailsActivity.this.startActivity(qr);
                                finish();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                reserveDetail.setEnabled(true);
                                progressDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                ReserveRequest reserveRequest = new ReserveRequest(cUser,cQr,cName,cPrice,cFullName,cLoc, responseListener);
                reserveRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                RequestQueue queue = Volley.newRequestQueue(DetailsActivity.this);
                queue.add(reserveRequest);
            }
        });
    }
    @Override
        public void onBackPressed(){
        Intent intent = new Intent(DetailsActivity.this,MainActivity.class);
        intent.putExtra("username", cUser);
        intent.putExtra("fname", cFirst);
        intent.putExtra("lname", cLast);
        DetailsActivity.this.startActivity(intent);
        finish();
    }
}
