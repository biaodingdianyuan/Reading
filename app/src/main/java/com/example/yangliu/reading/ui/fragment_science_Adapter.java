package com.example.yangliu.reading.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

;

/**
 * Created by 刘海风 on 2016/7/26.
 */

public  class fragment_science_Adapter extends FragmentPagerAdapter {
    private Context context;
    private  String tabtitle[]=new String[]{"热点","前沿","评论","专访","视觉","速读","谣言粉碎机","商业科技"};
    private String httptabtitle[]=new String[]{"hot","frontier","review","interview","visual","brief","fact","techb"};
    private int count=tabtitle.length;
    public fragment_science_Adapter(FragmentManager fm, Context context ) {
        super(fm);
           this.context=context;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Fragment getItem(int position) {

        return lv_science_fragement.newInstance(null,httptabtitle[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabtitle[position];
    }
}
