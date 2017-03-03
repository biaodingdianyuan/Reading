package com.example.yangliu.reading.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yangliu.reading.Beans.ResultBean;
import com.example.yangliu.reading.Beans.ScienceBean;
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

import butterknife.ButterKnife;
import butterknife.InjectView;


public class lv_science_fragement extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SwipeRefreshLayout swipe;
    private RecyclerView recyclerView;
    private AVLoadingIndicatorView scienceloading;
    private String mParam1;
    private String mParam2;
    private ScienceBean bean;
    private List<ResultBean> list;
    private scienceAdapter adapter;
    private Handler myhangdler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 111) {
                scienceloading.setVisibility(View.GONE);
                bean = (ScienceBean) msg.getData().getSerializable("science");
                recyclerView.setVisibility(View.VISIBLE);
                list.clear();
                list.addAll(bean.getResult());
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(adapter.getItemCount() - 1);
                swipe.setRefreshing(false);
            }
        }
    };


    public static lv_science_fragement newInstance(String param1, String param2) {
        lv_science_fragement fragment = new lv_science_fragement();
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

        bean = new ScienceBean();
        View view = inflater.inflate(R.layout.lv_science_fragement, container, false);
        scienceloading = (AVLoadingIndicatorView) view.findViewById(R.id.scienceloading);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.science_swipe);
        recyclerView = (RecyclerView) view.findViewById(R.id.science_recycler);
        list = new ArrayList<ResultBean>();
        adapter = new scienceAdapter(getActivity(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, true));
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setVisibility(View.GONE);
        scienceloading.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getSciencedata("http://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=" + mParam2);

            }
        }).start();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getSciencedata("http://www.guokr.com/apis/minisite/article.json?retrieve_type=by_channel&channel_key=" + mParam2);

                    }
                }).start();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    class scienceAdapter extends RecyclerView.Adapter {
        Context context;
        List<ResultBean> list;
        LayoutInflater layoutInflater;

        public scienceAdapter(Context context, List<ResultBean> list) {
            this.context = context;
            this.list = list;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.lv_science, null);
            scienceHolder holder = new scienceHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((scienceHolder) holder).title.setText(list.get(position).getTitle_hide());
            Picasso.with(getActivity()).load(list.get(position).getSmall_image()).resize(96,96)
                    .into(((scienceHolder) holder).science_image);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class scienceHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView science_image;

        public scienceHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.lv_science_title);
            science_image = (ImageView) itemView.findViewById(R.id.lv_science_image);
        }
    }

    public void getSciencedata(String url) {
        ScienceBean scienceBean = null;
        Gson gson = new Gson();
        Type listType = new TypeToken<ScienceBean>() {
        }.getType();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                scienceBean = gson.fromJson(response.body().string(), listType);

                Message m = new Message();
                Bundle b = new Bundle();
                b.putSerializable("science", scienceBean);
                m.what = 111;
                m.setData(b);
                myhangdler.sendMessage(m);
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
