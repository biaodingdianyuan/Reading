package com.example.yangliu.reading.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.yangliu.reading.R;


public class newfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public newfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     2.
     * @return A new instance of fragment newfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newfragment newInstance(int param1) {
        newfragment fragment = new newfragment();
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
       View view =inflater.inflate(R.layout.fragment_newfragment, container, false);
        TabLayout tab= (TabLayout) view.findViewById(R.id.fragment_new_tabl);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.fragment_new_viewpager);
        fragment_news_adapter adapter=new fragment_news_adapter(getChildFragmentManager(),getActivity());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);



        return view;
    }



}
