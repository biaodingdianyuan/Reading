package com.example.yangliu.reading.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yangliu.reading.ui.lv_new_fragement;


/**
 * Created by 刘海风 on 2016/7/27.
 */

public class fragment_news_adapter extends FragmentPagerAdapter {
    private Context context;
    private  String tabtitle[]=new String[]{"国内","国际","军事","图片","娱乐","艺术","科技","华人","金融","财经","时政","汽车","法制","教育"};
    private  String httptabtitle[]=new String[]{"http://www.xinhuanet.com/local/news_province.xml",
                                                    "http://www.xinhuanet.com/world/news_world.xml",
                                                    "http://www.xinhuanet.com/mil/news_mil.xml",
            "http://www.xinhuanet.com/photo/news_photo.xml",
            "http://ent.news.cn/news_ent.xml",
            "http://www.xinhuanet.com/shuhua/news_calligraphy.xml",
            "http://www.xinhuanet.com/tech/news_tech.xml" ,
            "http://www.xinhuanet.com/overseas/news_overseas.xml" ,
            "http://www.xinhuanet.com/finance/news_finance.xml" ,
            "http://www.xinhuanet.com/fortune/news_fortune.xml" ,
            "http://www.xinhuanet.com/politics/news_politics.xml" ,
            "http://www.xinhuanet.com/auto/news_auto.xml" ,
            "http://www.xinhuanet.com/legal/news_legal.xml" ,
            "http://www.xinhuanet.com/edu/news_edu.xml"};
    private int count=tabtitle.length;
    public fragment_news_adapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;

    }

    @Override
    public Fragment getItem(int position) {
        return lv_new_fragement.newInstance(httptabtitle[position],null);
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
