package com.example.yangliu.reading.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.yangliu.reading.MyApplication;
import com.example.yangliu.reading.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity {

    @InjectView(R.id.main_tool)
    Toolbar mainTool;
    @InjectView(R.id.main_fragment)
    FrameLayout mainFragment;
    @InjectView(R.id.activity_main)
    DrawerLayout activityMain;
    @InjectView(R.id.navigation)
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (!MyApplication.isnet) {
            Toast.makeText(this, "请检查网络连接！", Toast.LENGTH_SHORT).show();
        }
        tool();
        drawer();

    }

    public void tool() {
        mainTool.setTitle("日报");
        mainTool.setTitleTextColor(Color.WHITE);
        mainTool.setNavigationIcon(R.mipmap.ic_home_white);

        mainTool.inflateMenu(R.menu.menuread);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, swipe.newInstance(0)).commit();
        mainTool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.science:
                        mainTool.getMenu().clear();
                        mainTool.setNavigationIcon(R.mipmap.ic_science_white);
                        mainTool.setTitle("科学");
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sciencefragment.newInstance(0)).commit();
                        mainTool.inflateMenu(R.menu.menuhome);
                        break;
                    case R.id.home:
                        mainTool.getMenu().clear();
                        mainTool.setTitle("日报");
                        mainTool.setNavigationIcon(R.mipmap.ic_home_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, swipe.newInstance(0)).commit();
                        mainTool.inflateMenu(R.menu.menuread);
                        break;
                    case R.id.news:
                        mainTool.getMenu().clear();
                        mainTool.setTitle("新闻");
                        mainTool.setNavigationIcon(R.mipmap.ic_news_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, newfragment.newInstance(0)).commit();
                        mainTool.inflateMenu(R.menu.menuscience);
                        break;
                    case R.id.read:
                        mainTool.getMenu().clear();
                        mainTool.setTitle("阅读");
                        mainTool.setNavigationIcon(R.mipmap.ic_reading_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, readfragment.newInstance(null, null)).commit();
                        mainTool.inflateMenu(R.menu.menunews);
                        break;
                }
                return true;
            }
        });

    }

    public void drawer() {


        mainTool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                activityMain.openDrawer(GravityCompat.START);
            }
        });


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        mainTool.setTitle("日报");
                        mainTool.getMenu().clear();
                        mainTool.inflateMenu(R.menu.menuread);
                        mainTool.setNavigationIcon(R.mipmap.ic_home_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, swipe.newInstance(0)).commit();

                        break;
                    case R.id.science:
                        mainTool.setTitle("科学");
                        mainTool.getMenu().clear();
                        mainTool.inflateMenu(R.menu.menuhome);
                        mainTool.setNavigationIcon(R.mipmap.ic_science_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, sciencefragment.newInstance(0)).commit();

                        break;
                    case R.id.read:
                        mainTool.getMenu().clear();
                        mainTool.setTitle("阅读");
                        mainTool.inflateMenu(R.menu.menunews);
                        mainTool.setNavigationIcon(R.mipmap.ic_reading_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, readfragment.newInstance(null, null)).commit();
                        break;


                    case R.id.news:
                        mainTool.setTitle("新闻");
                        mainTool.getMenu().clear();
                        mainTool.inflateMenu(R.menu.menuscience);
                        mainTool.setNavigationIcon(R.mipmap.ic_news_white);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, newfragment.newInstance(0)).commit();
                        break;

                    case R.id.collect:
                        mainTool.setTitle("收藏");
                        mainTool.getMenu().clear();
                        mainTool.inflateMenu(R.menu.menuread);
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, Collect_fragment.newInstance(null, null)).commit();
                        break;

                }
                activityMain.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}
