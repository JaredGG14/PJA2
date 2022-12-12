package com.example.pja2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.pja2.adaptador.galleryPhotoAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketMs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketMs extends Fragment {
    GridView gridView;
    FloatingActionButton btn;
    ArrayList<String> imagelist;
    RecyclerView recyclerView;
    StorageReference root;
    ProgressBar progressBar;
    ImageAdapter adapter;
    ImageView itemShow;
    FirebaseAuth auth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TicketMs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TicketMs.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketMs newInstance(String param1, String param2) {
        TicketMs fragment = new TicketMs();
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
        View view = inflater.inflate(R.layout.fragment_ticket_ms, container, false);

        /*
        gridView = (GridView) view.findViewById(R.id.galleryTickets);
        gridView.setAdapter(new galleryPhotoAdapter(getContext()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), AddTicket.class);
                intent.putExtra("misImagenes", i);
                startActivity(intent);
            }
        });
        */
        btn = (FloatingActionButton) view.findViewById(R.id.fab);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UploadImage.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();

        itemShow = (ImageView) view.findViewById(R.id.item);
        imagelist=new ArrayList<>();
        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter=new ImageAdapter(imagelist,getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(null, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar=(ProgressBar) view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("images/" + auth.getCurrentUser().getUid() + "/");
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference file:listResult.getItems()){
                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imagelist.add(uri.toString());
                            Log.e("Itemvalue",uri.toString());
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);

                        }
                    });
                }
            }
        });
        return view;
    }
}