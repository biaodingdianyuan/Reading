package com.example.yangliu.reading.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yangliu.reading.R;


public class Collect_fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public Collect_fragment() {
        // Required empty public constructor
    }

    public static Collect_fragment newInstance(String param1, String param2) {
        Collect_fragment fragment = new Collect_fragment();
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
        View view=inflater.inflate(R.layout.fragment_collect_fragment, container, false);
        TabLayout tab= (TabLayout) view.findViewById(R.id.collect_tab);
        ViewPager viewPager= (ViewPager) view.findViewById(R.id.collect_viewPager);
        collect_adapter adapter=new collect_adapter(getChildFragmentManager(),getActivity());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
        return view;
    }

class collect_adapter extends FragmentPagerAdapter{
    private  Context context;
    private String[]tab=new String[]{"日报","科学","阅读","新闻"};
    private String[]str=new String[]{"home","science","read","news"};
    public collect_adapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        return collectfragment.newInstance(str[position],null);
    }

    @Override
    public int getCount() {
        return tab.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return tab[position];
    }
}


}
