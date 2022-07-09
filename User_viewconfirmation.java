package com.example.donationapplication;

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

public class User_viewconfirmation extends AppCompatActivity {


    String [] reqid,date,status,donor_id,Photo,name,dob,gender,place,pin,post,district,phone,email,lid;

    ListView lvs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewrequst_from_others);


        lvs=(ListView) findViewById(R.id.lvs);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/userviewrequeststatusers";



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

                                JSONArray js = jsonObj.getJSONArray("data");


                                donor_id=new String[js.length()];
                                Photo=new String[js.length()];
                                name=new String[js.length()];
                                dob=new String[js.length()];
                                gender=new String[js.length()];
                                place=new String[js.length()];
                                pin=new String[js.length()];
                                post=new String[js.length()];
                                district=new String[js.length()];
                                phone=new String[js.length()];
                                email=new String[js.length()];
                                lid=new String[js.length()];
                                reqid=new String[js.length()];
                                date=new String[js.length()];
                                status=new String[js.length()];


                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    donor_id[i]=u.getString("donor_id");
                                    Photo[i]=u.getString("Photo");
                                    name[i]=u.getString("name");
                                    dob[i]=u.getString("dob");
                                    gender[i]=u.getString("gender");
                                    place[i]=u.getString("place");
                                    pin[i]=u.getString("pin");
                                    post[i]=u.getString("post");
                                    district[i]=u.getString("district");
                                    phone[i]=u.getString("phone");
                                    email[i]=u.getString("email");
                                    lid[i]=u.getString("lid");
                                    reqid[i]=u.getString("reqid");
                                    date[i]=u.getString("date");
                                    status[i]=u.getString("status");
                                }
                                lvs.setAdapter(new custom_viewrequest_conf(getApplicationContext(),donor_id,Photo,name,dob,gender,place,pin,post,district,phone,email,lid,reqid,date,status));
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