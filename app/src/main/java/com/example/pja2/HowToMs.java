package com.example.pja2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HowToMs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HowToMs extends Fragment {

    TextView txt1, txt2, txt3, txt4, txt5;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HowToMs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HowToMs.
     */
    // TODO: Rename and change types and number of parameters
    public static HowToMs newInstance(String param1, String param2) {
        HowToMs fragment = new HowToMs();
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
        View view = inflater.inflate(R.layout.fragment_how_to_ms, container, false);

        txt1 = (TextView) view.findViewById(R.id.htaddCard);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HowTo_Card.class);
                startActivity(intent);
            }
        });
        txt2 = (TextView) view.findViewById(R.id.htaddIncome);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HowTo_IO.class);
                startActivity(intent);
            }
        });
        txt3 = (TextView) view.findViewById(R.id.htconfiglang);
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HowTo_lang.class);
                startActivity(intent);
            }
        });
        txt4 = (TextView) view.findViewById(R.id.htaddTicket);
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HowTo_ticket.class);
                startActivity(intent);
            }
        });
        txt5 = (TextView) view.findViewById(R.id.htaddDeleteData);
        txt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HowTo_delete.class);
                startActivity(intent);
            }
        });

        return view;
    }
}