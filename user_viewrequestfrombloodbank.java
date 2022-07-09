package com.example.donationapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class user_viewrequestfrombloodbank extends AppCompatActivity {

    String [] bb_id,bb_lid,name,email,phone,latitide,longitude,place,post,pin,district,license_no,status,request_id,date,status_b;
    ListView lvs;

    @Override
    public void onBackPressed() {
        Intent ins= new Intent(getApplicationContext(),Donor_home.class);
        startActivity(ins);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbloodbank);





        lvs=(ListView) findViewById(R.id.lvs);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/user_viewrequestfrombloodbank";



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

                                JSONArray js= jsonObj.getJSONArray("data");
                                bb_id=new String[js.length()];
                                bb_lid=new String[js.length()];
                                name=new String[js.length()];
                                email=new String[js.length()];
                                phone=new String[js.length()];
                                latitide=new String[js.length()];
                                longitude=new String[js.length()];
                                place=new String[js.length()];
                                post=new String[js.length()];
                                pin=new String[js.length()];
                                district=new String[js.length()];
                                license_no=new String[js.length()];
                                status=new String[js.length()];
                                request_id=new String[js.length()];
                                date=new String[js.length()];
                                status_b=new String[js.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    bb_id[i]=u.getString("bb_id");
                                    bb_lid[i]=u.getString("bb_lid");
                                    name[i]=u.getString("name");
                                    email[i]=u.getString("email");
                                    email[i]=u.getString("email");
                                    phone[i]=u.getString("phone");
                                    latitide[i]=u.getString("latitide");
                                    longitude[i]=u.getString("longitude");
                                    place[i]=u.getString("place");
                                    post[i]=u.getString("post");
                                    pin[i]=u.getString("pin");
                                    district[i]=u.getString("district");
                                    license_no[i]=u.getString("license_no");
                                    status[i]=u.getString("status");
                                    request_id[i]=u.getString("request_id");
                                    date[i]=u.getString("date");
                                    status_b[i]=u.getString("status_b");
//                                    Toast.makeText(view_reply.this, "aaaaaaaaaa", Toast.LENGTH_SHORT).show();

                                }

                                lvs.setAdapter(new custom_request_from_bloodbank(getApplicationContext(),bb_id,bb_lid,name,email,phone,latitide,longitude,place,post,pin,district,license_no,status,request_id,date,status_b));

                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
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

                String id=sh.getString(" ","");
                params.put("lid",sh.getString("lid",""));
//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);



    }
}