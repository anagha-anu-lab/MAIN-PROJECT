package com.example.donationapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.donationapplication.databinding.ActivityDonorHomeBinding;

public class Donor_home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDonorHomeBinding binding;

    @Override
    public void onBackPressed() {
        Intent ins= new Intent(getApplicationContext(),login.class);
        startActivity(ins);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDonorHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDonorHome.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_donor_home);
//        NavigationUI.setupActionBarWithNavController(this
//                , navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.donor_home, menu);
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (R.id.nav_view_pro==id)
        {
            Intent i = new Intent(getApplicationContext(),view_profile.class);
            startActivity(i);
        }

        if (R.id.nav_bloodbank==id)
        {
            Intent i = new Intent(getApplicationContext(),Viewbloodbank.class);
            startActivity(i);
        }

        if (R.id.nav_log_out==id)
        {
            Intent i = new Intent(getApplicationContext(),login.class);
            startActivity(i);
        }
        if (R.id.nav_complaint==id)
        {
            Intent i = new Intent(getApplicationContext(),view_reply.class);
            startActivity(i);
        }
        if (R.id.nav_searchblooduser==id)
        {
            Intent i = new Intent(getApplicationContext(),user_Search_blood_inside_users.class);
            startActivity(i);
        }
        if (R.id.nav_viewrequestfromothers==id)
        {
            Intent i = new Intent(getApplicationContext(),User_viewrequst_from_others.class);
            startActivity(i);
        }

        if (R.id.nav_requestfrombloodbank==id)
        {
            Intent i = new Intent(getApplicationContext(),user_viewrequestfrombloodbank.class);
            startActivity(i);
        }
        if (R.id.bloodbankentry==id)
        {
            Intent i = new Intent(getApplicationContext(),user_view_bloodentrymadebybloodbank.class);
            startActivity(i);
        }

        if (R.id.nav_search==id)
        {
            Intent i = new Intent(getApplicationContext(),Usersendrequestforblood.class);
            startActivity(i);
        }
        if (R.id.nav_health==id)
        {
            Intent i = new Intent(getApplicationContext(),Healthconditions.class);
            startActivity(i);
        }
        if (R.id.nav_myrreq==id)
        {
            Intent i = new Intent(getApplicationContext(),User_viewconfirmation.class);
            startActivity(i);
        }
        if (R.id.nav_change_pass==id)
        {
            Intent i = new Intent(getApplicationContext(),change_password.class);
            startActivity(i);
        }
        if (R.id.nav_bloodbank_location==id)
        {
            Intent i = new Intent(getApplicationContext(),user_Search_blood_inside_users_locationbased.class);
            startActivity(i);
        }
        if (R.id.nav_Sentrrequest==id)
        {
            Intent i = new Intent(getApplicationContext(),user_view_sentrequesttobloodbank.class);
            startActivity(i);
        }

        return false;
    }
}