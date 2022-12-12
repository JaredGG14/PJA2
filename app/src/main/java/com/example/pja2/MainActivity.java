package com.example.pja2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerL;
    NavigationView navigationView;
    Toolbar toolbar;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerL = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        toggle = new ActionBarDrawerToggle(this, mDrawerL,toolbar,R.string.drawer_open, R.string.drawer_close);


        getSupportFragmentManager().beginTransaction().add(R.id.content, new HomeMs()).commit();
        setTitle(R.string.app_name);

        //setup toolbar
        setSupportActionBar(toolbar);


        //toggle = new ActionBarDrawerToggle(this, mDrawerL,toolbar,R.string.drawer_open, R.string.drawer_close);
        mDrawerL.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);


        if (AddSaving.save_savings == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new SavingsMs()).commit();
            setTitle(R.string.menu_savings);
            AddSaving.save_savings=false;
        }

        if (Add_card.save_card == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new CardMs()).commit();
            setTitle(R.string.menu_card);
            Add_card.save_card=false;
        }

        if (Add_Income_Outcome.Add_IO == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new SpentMs()).commit();
            setTitle(R.string.menu_spents);
            Add_Income_Outcome.Add_IO=false;
        }

        if (AddCash.Add_Cash == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new CashMs()).commit();
            setTitle(R.string.menu_cash);
            AddCash.Add_Cash=false;
        }
        if (UploadImage.Add_new_tkt == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new TicketMs()).commit();
            setTitle(R.string.menu_tickets);
            UploadImage.Add_new_tkt=false;
        }
        if (AccountSettings.save_user == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new SettingMs()).commit();
            setTitle(R.string.menu_settings);
            AccountSettings.save_user=false;
        }
        if (SettingMs.setting_boolean == true){
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, new SettingMs()).commit();
            setTitle(R.string.menu_settings);
            SettingMs.setting_boolean=false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectItemNav(item);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    private void selectItemNav(MenuItem item) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (item.getItemId()){
            case R.id.home:
                ft.replace(R.id.content, new HomeMs()).commit();
                break;
            case R.id.nav_savings2:
                ft.replace(R.id.content, new SavingsMs()).commit();
                break;
            case R.id.nav_cards:
                ft.replace(R.id.content, new CardMs()).commit();
                break;
            case R.id.nav_cash2:
                ft.replace(R.id.content, new CashMs()).commit();
                break;
            case R.id.nav_ticket2:
                ft.replace(R.id.content, new TicketMs()).commit();
                break;
            case R.id.nav_spents2:
                ft.replace(R.id.content, new SpentMs()).commit();
                break;
            case R.id.settingsFragment:
                ft.replace(R.id.content, new SettingMs()).commit();
                break;
            case R.id.nav_howto2:
                ft.replace(R.id.content, new HowToMs()).commit();
                break;
            case R.id.nav_rateus:
                ft.replace(R.id.content, new RateUsMs()).commit();
                break;
            case R.id.nav_contact2:
                ft.replace(R.id.content, new ContactMs()).commit();
                break;
        }
        setTitle(item.getTitle());
        mDrawerL.closeDrawers();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.howTo_menu:
                ft.replace(R.id.content, new HowToMs()).commit();
                setTitle(R.string.menu_howto);
                break;
            case R.id.Setting_menu:
                ft.replace(R.id.content, new SettingMs()).commit();
                setTitle(R.string.menu_settings);
               break;

        }

        return super.onOptionsItemSelected(item);


    }


}

