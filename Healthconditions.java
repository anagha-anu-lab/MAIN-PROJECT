package com.example.donationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Healthconditions extends AppCompatActivity implements View.OnClickListener {
EditText ed,ed1;
CheckBox c1,c2;
Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthconditions);
        ed=(EditText) findViewById(R.id.editTextTextPersonName2);
        ed1=(EditText) findViewById(R.id.editTextTextPersonName5);

        c1=(CheckBox) findViewById(R.id.checkBox);
        c2=(CheckBox) findViewById(R.id.checkBox2);

        bt=(Button) findViewById(R.id.button25);
        bt.setOnClickListener(this);




        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/and_view_health";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {




                                String name = jsonObj.getString("height");

                                ed.setText(name);

                                String dob = jsonObj.getString("weight");

                                ed1.setText(dob);

                                String gender = jsonObj.getString("dia");
if (gender.equalsIgnoreCase("Yes"))
{
    c1.setChecked(true);
}


                                String phone = jsonObj.getString("drink");


if(phone.equalsIgnoreCase("Yes"))

{
    c2.setChecked(true);
}



                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();


                params.put("lid", sh.getString("lid", ""));


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

    }
String dia="",alco="";
    @Override
    public void onClick(View v) {
        if (c1.isChecked())
        {
            dia="Yes";
        }
        else{
            dia="No";
        }

        if(c2.isChecked())
        {
            alco="Yes";
        }
        else{
            alco="No";
        }

        String height = ed.getText().toString();

        String weight = ed1.getText().toString();

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (height.length() == 0) {
            ed.setError("Missing");
        } else if (weight.length() == 0) {
            ed1.setError("gender Missing");
        }
        else{

            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/user_health";
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {


                                    Toast.makeText(getApplicationContext()," successfull",Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), Donor_home.class);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invaild ", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("height", height);
                    params.put("weight", weight);
                    params.put("dia", dia);
                    params.put("alco", alco);
                    params.put("lid", sh.getString("lid",""));


                    return params;
                }
            };

            int MY_SOCKET_TIMEOUT_MS = 100000;

            postRequest.setRetryPolicy(new DefaultRetryPolicy(
                    MY_SOCKET_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(postRequest);

        }



    }
}