package com.example.pja2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactMs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactMs extends Fragment {
    ImageView iv1, iv2, iv3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactMs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactMs.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactMs newInstance(String param1, String param2) {
        ContactMs fragment = new ContactMs();
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
        View view = inflater.inflate(R.layout.fragment_contact_ms, container, false);

        iv1 = (ImageView) view.findViewById(R.id.fb_logo);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri link = Uri.parse("http://pili.app/fb/SQ6GEQig");
                Intent intent = new Intent(Intent.ACTION_VIEW,link);
                startActivity(intent);
            }
        });

        iv2 = (ImageView) view.findViewById(R.id.ig_logo);
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/_gabriel_ro?igshid=YmMyMTA2M2Y= "));
                intent.setPackage("com.instagram.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/xxx")));
                }

            }

        });

        iv3 = (ImageView) view.findViewById(R.id.tw_logo);
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://twitter.com/"));
                intent.setPackage("com.twitter.android");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://twitter.com/xxx")));
                }
            }
        });

        return view;
    }
}