package com.example.donationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class change_password extends AppCompatActivity implements View.OnClickListener {
    EditText currpass,newpass,conpass;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        currpass=(EditText) findViewById(R.id.editTextTextPassword);
        newpass=(EditText) findViewById(R.id.editTextTextPassword2);
        conpass=(EditText) findViewById(R.id.editTextTextPassword3);
        btn1=(Button)findViewById(R.id.button3);
        btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        final String current_password=currpass.getText().toString();
        final String new_password=newpass.getText().toString();
        int size=new_password.length();

        final String confirm_password=conpass.getText().toString();

        int sizes=confirm_password.length();

        SharedPreferences sha=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String curpassword= sha.getString("password","");



        if(current_password.length()==0){
            currpass.setError("Enter current password");
        }
        else if(current_password.length()<6){
            currpass.setError("Must contain atleast 6 characters");
        }
        else if(new_password.length()==0) {
            newpass.setError("Enter new password");
        }
        else if(new_password.length()<6) {
            newpass.setError("Must contain atleast 6 characters");
        }
        else if (confirm_password.length()==0){
            conpass.setError("field cannot be empty");
        }
        else if (confirm_password.length()<0){
            conpass.setError("Must contain atleast 6 characters");
        }
        else if (!confirm_password.equalsIgnoreCase(new_password)) {

            conpass.setError("Missmatch in password");

        }


        else if(!curpassword.equalsIgnoreCase(current_password))
        {
            Toast.makeText(getApplicationContext(),"Missmatch in old password. Please try again later",Toast.LENGTH_LONG).show();
            Intent ins= new Intent(getApplicationContext(),login.class);
            startActivity(ins);
        }
       else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            final String maclis = sh.getString("mac_list", "");
            String id = sh.getString("uid", "");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/and_change_password";
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    Toast.makeText(change_password.this, "Successfully changed password", Toast.LENGTH_SHORT).show();
                                    Intent ii=new Intent(getApplicationContext(),login.class);
                                    startActivity(ii);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
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
                    params.put("password", conpass.getText().toString());
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



        }


    }
