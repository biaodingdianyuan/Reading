package com.example.yangliu.reading.ui;

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


public class readfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  fragment_read_adapter  adapter;//fragment 适配器


    public readfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment readfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static readfragment newInstance(String param1, String param2) {
        readfragment fragment = new readfragment();
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
        View view=inflater.inflate(R.layout.fragment_readfragment, container, false);
        TabLayout tabl= (TabLayout) view.findViewById(R.id.fragmemt_read__tab);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.fragmemt_read_viewpager);
        adapter= new fragment_read_adapter(getChildFragmentManager(),getActivity());

        viewPager.setAdapter(adapter);
        tabl.setupWithViewPager(viewPager);
        tabl.setTabGravity(TabLayout.MODE_SCROLLABLE);
        tabl.setTabMode(TabLayout.MODE_FIXED);



        return view;
    }




}
