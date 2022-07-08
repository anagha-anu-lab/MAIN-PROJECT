package com.example.donationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Add_donation extends AppCompatActivity implements View.OnClickListener {
    TextView tvadd;
    EditText editem,eddes;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
        tvadd=(TextView) findViewById(R.id.textView7);
        editem=(EditText) findViewById(R.id.editTextTextPersonName4);
        eddes=(EditText) findViewById(R.id.editTextTextMultiLine2);

        btn1=(Button) findViewById(R.id.button8);
        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String Add_connection = editem.getText().toString();
        if(editem.length()==0){
            editem.setError("This field cannot be empty");
        }
        else if(eddes.length()==0) {
            eddes.setError("This field cannot be empty");
        }
        else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis = sh.getString("mac_list", "");
            String ip = sh.getString("ip", "");
            String url = "http://" + ip + ":5000/and_Add_donation";
            Toast.makeText(this, "Loading..." + url, Toast.LENGTH_SHORT).show();


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
                                    Toast.makeText(Add_donation.this, "Thank you..!!!..Donation Added...", Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(getApplicationContext(),Donor_home.class);
                                    startActivity(i);
                                }


                                // }
                                else {
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

                    String id = sh.getString("lid", "");
                    params.put("item", editem.getText().toString());
                    params.put("description", eddes.getText().toString());
                    params.put("lid", id);

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

