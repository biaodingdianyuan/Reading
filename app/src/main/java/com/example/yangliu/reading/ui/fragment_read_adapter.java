package com.example.yangliu.reading.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



/**
 * Created by 刘海风 on 2016/7/27.
 */

public class fragment_read_adapter extends FragmentPagerAdapter {
    private Context context;
    private  String tabtitle[]=new String[]{"综合","文学","程序员","流行","文化","生活","金融"};
    private int count=tabtitle.length;
    public fragment_read_adapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;

    }

    @Override
    public Fragment getItem(int position) {
        return lv_read_fragement.newInstance(tabtitle[position]);
    }

    @Override
    public int getCount() {
        return count;
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return tabtitle[position];
    }

}
