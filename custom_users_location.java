package com.example.donationapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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

public class custom_users_location extends BaseAdapter {
    String[]  donor_id,Photo,name,dob,gender,place,pin,post,district,phone,email,lid,dist;
    private Context context;


    public custom_users_location(Context applicationContext, String[]  donor_id, String[] Photo, String[] name, String[] dob, String[] gender, String[] place, String[] pin, String[] post, String[] district, String[] phone, String[] email, String[] lid,String [] dist){
        this.context=applicationContext;
        this.donor_id=donor_id;
        this.Photo=Photo;
        this.name=name;
        this.dob=dob;
        this.gender=gender;
        this.place=place;
        this.pin=pin;
        this.post=post;
        this.post=post;
        this.district=district;
        this.phone=phone;
        this.email=email;
        this.lid=lid;
        this.dist=dist;
    }
    @Override
    public int getCount() {
        return lid.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View ListView;
        if(view==null)
        {
            ListView=new View(context);
            //ListView=inflator.inflate(R.layout.customview, null);
            ListView=inflator.inflate(R.layout.customuser_location,null);

        }
        else

        {
            ListView=(View)view;

        }
        TextView tvname=(TextView)ListView.findViewById(R.id.textView56);
        TextView tvplace=(TextView)ListView.findViewById(R.id.textView75);
        TextView tvdistrict=(TextView)ListView.findViewById(R.id.textView76);
        TextView tvemail=(TextView)ListView.findViewById(R.id.textView80);
        TextView tvkm=(TextView)ListView.findViewById(R.id.textView14);
        ImageView img=(ImageView) ListView.findViewById(R.id.imageView5);
        Button bts=(Button) ListView.findViewById(R.id.button24);
        bts.setTag(lid[i]);
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             final    String requestedlid=v.getTag().toString();

                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("lid_to",v.getTag().toString());
                ed.commit();


                Intent ins= new Intent(context,samdialog.class);
                ins.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(ins);




//                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//                final String maclis=sh.getString("mac_list","");
//                String uid=sh.getString("uid","");
//                String hu = sh.getString("ip", "");
//                String url = "http://" + hu + ":5000/user_sentrequest_to_user";
//                RequestQueue requestQueue = Volley.newRequestQueue(context);
//                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                try {
//                                    JSONObject jsonObj = new JSONObject(response);
//                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                        Toast.makeText(context, "Request send", Toast.LENGTH_SHORT).show();
//                                    }
//                                    else {
//                                        Toast.makeText(context, "Failed to sent request", Toast.LENGTH_LONG).show();
//                                    }
//                                }    catch (Exception e) {
//                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // error
//                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        String id=sh.getString("uid","");
//                        params.put("lid_to",v.getTag().toString());
//                        params.put("lid_from",sh.getString("lid",""));
////                params.put("mac",maclis);
//
//                        return params;
//                    }
//                };
//
//                int MY_SOCKET_TIMEOUT_MS=100000;
//
//                postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        MY_SOCKET_TIMEOUT_MS,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(postRequest);


            }
        });



        tvname.setText(name[i]);
        tvplace.setText(place[i]);
        tvdistrict.setText(district[i]);

        tvemail.setText(email[i]);
        tvkm.setText(dist[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://"+ip+":5000"+Photo[i];

        Picasso.with(context).load(url).into(img);

        return ListView;
    }
}

