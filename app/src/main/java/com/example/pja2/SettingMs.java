package com.example.pja2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingMs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingMs extends Fragment {
    public static boolean setting_boolean=false;
    TextView txt1, txt2;
    public RadioButton rb, rb1, rb3, rb4, rb2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingMs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingMs.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingMs newInstance(String param1, String param2) {
        SettingMs fragment = new SettingMs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting_ms, container, false);

        txt1 = (TextView) view.findViewById(R.id.tv2_acco);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Empty information");
                builder.setMessage(getString(R.string.empty_data_message) +
                        getString(R.string.empty_data_message_2) +
                        getString(R.string.empty_data_message_3) +
                        getString(R.string.empty_data_message_4));

                builder.setPositiveButton(R.string.confirm_btn, null);
                builder.setNegativeButton(R.string.cancel_btn, null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        txt2 = (TextView) view.findViewById(R.id.tv1_acco);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AccountSettings.class);
                startActivity(intent);
            }
        });

        rb = (RadioButton) view.findViewById(R.id.rB_English);
        rb1 = (RadioButton) view.findViewById(R.id.rB_Español);
        rb2 = (RadioButton) view.findViewById(R.id.rb_Light);
        rb3 = (RadioButton) view.findViewById(R.id.rB_dark);
        rb4 = (RadioButton) view.findViewById(R.id.rB_def);

        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    openL();
                    rb1.setChecked(false);
                    rb.setChecked(false);
                }else{
                    rb.setChecked(false);
                }
            }
        });

        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    openL();
                    rb.setChecked(false);
                    rb1.setChecked(false);
                }else{
                   rb1.setChecked(false);
                }
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDay();
                rb3.setChecked(false);
                rb4.setChecked(false);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNight();
                rb2.setChecked(false);
                rb4.setChecked(false);
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openD();
                rb2.setChecked(false);
                rb3.setChecked(false);
            }
        });

        return view;
    }

    public void setNight(){
        SharedPreferences sp = getContext().getSharedPreferences("SP", getContext().MODE_PRIVATE);
        int theme = sp.getInt("Theme", 1);
        if(theme==0){
            ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            setting_boolean=true;
        }
        else{
            ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setting_boolean=true;
        }
    }

    public void setDay(){
        SharedPreferences sp = getContext().getSharedPreferences("SP", getContext().MODE_PRIVATE);
        int theme = sp.getInt("Theme", 1);
        if(theme==1){
            ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            ((AppCompatActivity) new AccountSettings()).getDelegate().setTheme(androidx.appcompat.R.style.Theme_AppCompat_DayNight_DarkActionBar);
            setting_boolean=true;
        }
        else{
            ((AppCompatActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setting_boolean=true;
        }
    }

    public void openL(){
        startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
        setting_boolean=true;
    }

    public void openD(){
        startActivity(new Intent(Settings.ACTION_DISPLAY_SETTINGS));
        setting_boolean=true;
    }
}