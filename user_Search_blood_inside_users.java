package com.example.donationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class user_Search_blood_inside_users extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sp;
    ListView lvs;

    String [] bloodgroups={"A-","A+", "B+","B-","AB+","AB-"};

    String [] donor_id,Photo,name,dob,gender,place,pin,post,district,phone,email,lid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_blood_inside_users);

        sp=(Spinner) findViewById(R.id.spinner);
        lvs=(ListView) findViewById(R.id.lvs);

        sp.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,bloodgroups));

        sp.setOnItemSelectedListener(this);




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

a();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void a()
    {
            String ads= sp.getSelectedItem().toString();


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed=sh.edit();
            ed.putString("selblood",ads);
            ed.commit();

            final String maclis=sh.getString("mac_list","");
            String uid=sh.getString("uid","");
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/user_search_blood_inside_users";



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
                                    }
                                    lvs.setAdapter(new custom_users(getApplicationContext(),donor_id,Photo,name,dob,gender,place,pin,post,district,phone,email,lid));
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
                    params.put("blood",sp.getSelectedItem().toString());
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