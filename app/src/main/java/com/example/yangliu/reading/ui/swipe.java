package com.example.yangliu.reading.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yangliu.reading.Beans.Home;
import com.example.yangliu.reading.Beans.ScienceBean;
import com.example.yangliu.reading.Beans.StroiesBean;
import com.example.yangliu.reading.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.support.v7.appcompat.R.id.home;

/**
 * Created by 刘海风 on 2016/8/2.
 */

public class swipe extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private RecycleAdapter recycleAdapter;
    private List<StroiesBean> stories;
    private SwipeRefreshLayout swipe;
    private AVLoadingIndicatorView loading;
    private RecyclerView recycler;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 111) {
                Bundle bundle = msg.getData();
                loading.setVisibility(View.GONE);
                recycler.setVisibility(View.VISIBLE);
                stories.clear();
                stories.addAll(((Home) bundle.getSerializable("home")).getStories());
                recycleAdapter.notifyDataSetChanged();
                recycler.scrollToPosition(recycleAdapter.getItemCount()-1);
                swipe.setRefreshing(false);
            }

        }
    };


    public swipe() {
        // Required empty public constructor
    }

    public static swipe newInstance(int param1) {
        swipe fragment = new swipe();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.swiperefresh, container, false);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        loading = (AVLoadingIndicatorView) view.findViewById(R.id.homeloading);
        stories = new ArrayList<StroiesBean>();
        recycleAdapter = new RecycleAdapter(getActivity(), stories);
        recycler = (RecyclerView) view.findViewById(R.id.recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, true));
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(recycleAdapter);
        loading.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.GONE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                downLoadData();
            }
        }).start();
        //滑动的监听器
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stories.clear();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downLoadData();
                    }
                }).start();

            }
        });


        return view;
    }
    private void downLoadData() {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        Type listType = new TypeToken<Home>() {
        }.getType();
        Request request = new Request.Builder().get().url("http://news-at.zhihu.com/api/4/news/latest").build();
        try {
            Response response = client.newCall(request).execute();
            Home home = gson.fromJson(response.body().string(), listType);
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("home", home);
            message.what = 111;
            message.setData(bundle);
            handler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    class RecycleAdapter extends RecyclerView.Adapter {
        private Context context;
        private List<StroiesBean> stroiesBean;
        private LayoutInflater layoutInflater;

        public RecycleAdapter(Context context, List<StroiesBean> stroiesBean) {
            this.context = context;
            this.stroiesBean = stroiesBean;
            layoutInflater = LayoutInflater.from(context);

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.lv_home, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((MyViewHolder) holder).tv.setText(stroiesBean.get(position).getTitle());
            Picasso.with(getActivity()).load(stroiesBean.get(position).getImages().get(0).toString()).into(((MyViewHolder) holder).image);
            ((MyViewHolder) holder).lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   startActivity(new Intent(getActivity(),WebViewActivity.class).putExtra("url","http://daily.zhihu.com/story/"+stroiesBean.get(position).getId()).putExtra("title",stroiesBean.get(position).getTitle()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return stroiesBean.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView image;
        LinearLayout lin;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.textView3);
            image = (ImageView) view.findViewById(R.id.imageView);
            lin= (LinearLayout) view.findViewById(R.id.swipe_lin);
        }

    }

}
