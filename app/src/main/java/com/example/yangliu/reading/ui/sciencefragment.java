package com.example.yangliu.reading.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.yangliu.reading.R;

import java.util.List;

public class sciencefragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private fragment_science_Adapter adapter;


    public sciencefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment sciencefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sciencefragment newInstance(int param1) {
        sciencefragment fragment = new sciencefragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);

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
        View view=inflater.inflate(R.layout.fragment_sciencefragment, container, false);

        TabLayout tab = (TabLayout) view.findViewById(R.id.fragment_science_tabl);
        ViewPager Viewp = (ViewPager) view.findViewById(R.id.fragment_science_viewpager);
        adapter = new fragment_science_Adapter(getChildFragmentManager(), getActivity());
        Viewp.setAdapter(adapter);

        tab.setupWithViewPager(Viewp);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }


}
