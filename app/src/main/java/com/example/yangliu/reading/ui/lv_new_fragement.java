package com.example.yangliu.reading.ui;

import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangliu.reading.Beans.News_Bean;
import com.example.yangliu.reading.R;
import com.example.yangliu.reading.tools.data_news1;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wang.avi.AVLoadingIndicatorView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class lv_new_fragement extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView newRecycler;
    SwipeRefreshLayout newSwipe;
    AVLoadingIndicatorView newloading;
    private String mParam1;
    private String mParam2;
    private lv_news_fragementAdapter adapter;

    private List<News_Bean> new_list;
    private Handler myhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 111) {
                newloading.setVisibility(View.GONE);
                newRecycler.setVisibility(View.VISIBLE);
                new_list.clear();
                new_list.addAll((List<News_Bean>) msg.getData().getSerializable("new"));
                adapter.notifyDataSetChanged();
                newRecycler.scrollToPosition(adapter.getItemCount() - 1);
                newSwipe.setRefreshing(false);


            }
        }
    };

    public static lv_new_fragement newInstance(String param1, String param2) {
        lv_new_fragement fragment = new lv_new_fragement();
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
        View view = inflater.inflate(R.layout.fragment_lv_new_fragement, container, false);
        newRecycler= (RecyclerView) view.findViewById(R.id.new_recycler);
        newSwipe= (SwipeRefreshLayout) view.findViewById(R.id.new_swipe);
        newloading= (AVLoadingIndicatorView) view.findViewById(R.id.newloading);

        new_list = new ArrayList<News_Bean>();
        newloading.setVisibility(View.VISIBLE);
        newRecycler.setVisibility(View.GONE);
        adapter = new lv_news_fragementAdapter(new_list, getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        newRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, true));
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        newRecycler.setItemAnimator(new DefaultItemAnimator());
        newRecycler.setAdapter(adapter);
        newSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getdata(mParam1);
                    }
                }).start();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                getdata(mParam1);
            }
        }).start();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    class lv_news_fragementAdapter extends RecyclerView.Adapter {
        List<News_Bean> list;
        private Context context;
        private LayoutInflater layoutInflater;

        public lv_news_fragementAdapter(List<News_Bean> list, Context context) {
            this.list = list;
            this.context = context;
            this.layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.lv_news, null);
            NewHolder holder = new NewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            String content = list.get(position).getDescription();
            String text = content.replaceAll("</?[^>]+>", "");
            text = text.replaceAll("<a>\\s*|\t|\r|\n</a>", "");

            String title = list.get(position).getTitle();
            title = title.replaceAll("</?[^>]+>", "");
            title = title.replaceAll("<a>\\s*|\t|\r|\n</a>", "");
            ((NewHolder) holder).description.setText(text);
            ((NewHolder) holder).title.setText(title);
            ((NewHolder) holder).lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", list.get(position).getLink()).putExtra("title", list.get(position).getTitle()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class NewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, date;
        private LinearLayout lin;

        public NewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.lv_news_title);
            description = (TextView) itemView.findViewById(R.id.lv_news_description);
            lin = (LinearLayout) itemView.findViewById(R.id.lv_new_lin);
        }
    }

    public void getdata(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                try {
                    List<News_Bean> news = new data_news1().getlist(response.body().string());
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("new", (Serializable) news);
                    message.what = 111;
                    message.setData(bundle);
                    myhandler.sendMessage(message);

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
