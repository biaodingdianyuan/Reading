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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangliu.reading.Beans.BookBean;
import com.example.yangliu.reading.Beans.ReadingBean;
import com.example.yangliu.reading.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class lv_read_fragement extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView readRecycler;

    private SwipeRefreshLayout readSwipe;

    private AVLoadingIndicatorView readloading;
    private BookBean[] books;
    private lv_read_adapter adapter;
    private List<BookBean> list;
    private ReadingBean readingBean;
    private String mParam1;
    private String mParam2;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 111) {
                Bundle bundle = msg.getData();
                readingBean = new ReadingBean();
                readRecycler.setVisibility(View.VISIBLE);
                readloading.setVisibility(View.GONE);
                books = new BookBean[]{};
                readingBean = (ReadingBean) bundle.getSerializable("reading");
                books = readingBean.getBooks();
                list.clear();
                list.addAll(Arrays.asList(books));
                adapter.notifyDataSetChanged();
                readRecycler.scrollToPosition(adapter.getItemCount() - 1);
                readSwipe.setRefreshing(false);

            }
        }
    };


    public lv_read_fragement() {
        // Required empty public constructor
    }

    public static lv_read_fragement newInstance(String param1) {
        lv_read_fragement fragment = new lv_read_fragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        View view = inflater.inflate(R.layout.lv_read_fragement, container, false);

        readingBean = new ReadingBean();
        books = new BookBean[]{};
        list = new ArrayList<BookBean>();
        readRecycler= (RecyclerView) view.findViewById(R.id.read_recycler);
        readloading= (AVLoadingIndicatorView) view.findViewById(R.id.readloading);
        readSwipe= (SwipeRefreshLayout) view.findViewById(R.id.read_swipe);
        readloading.setVisibility(View.VISIBLE);
        readRecycler.setVisibility(View.GONE);
        adapter = new lv_read_adapter(getActivity(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        readRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, true));
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        readRecycler.setItemAnimator(new DefaultItemAnimator());
        readRecycler.setAdapter(adapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getdata("https://api.douban.com/v2/book/search?q=" + mParam1);
            }
        }).start();
        //滑动的监听器
        readSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getdata("https://api.douban.com/v2/book/search?q=" + mParam1);
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


    class lv_read_adapter extends RecyclerView.Adapter {
        private Context context;
        private List<BookBean> book;
        private LayoutInflater layoutInflater;

        public lv_read_adapter(Context context, List<BookBean> book) {
            this.context = context;
            this.book = book;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.lv_read, null);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((MyViewHolder) holder).name.setText(book.get(position).getTitle());
            ((MyViewHolder) holder).price.setText(book.get(position).getPrice());
            ((MyViewHolder) holder).author.setText(book.get(position).getAuthor() + "");
            ((MyViewHolder) holder).data.setText(book.get(position).getPubdate());
            ((MyViewHolder) holder).number.setText(book.get(position).getPages());
            Picasso.with(getActivity()).load(book.get(position).getImage()).into(((MyViewHolder) holder).image);
            ((MyViewHolder) holder).lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", book.get(position).getEbook_url()).putExtra("title", book.get(position).getTitle()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return book.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, author, data, number, price;
        ImageView image, read_collect;
        LinearLayout lin;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.lv_read_name);
            image = (ImageView) view.findViewById(R.id.lv_read_image);
            number = (TextView) view.findViewById(R.id.lv_read_number);
            author = (TextView) view.findViewById(R.id.lv_read_author);
            data = (TextView) view.findViewById(R.id.lv_read_date);
            price = (TextView) view.findViewById(R.id.lv_read_price);
            read_collect = (ImageView) view.findViewById(R.id.read_collect);
            lin = (LinearLayout) view.findViewById(R.id.lv_read_line);
        }

    }

    public void getdata(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().get().url(url).build();
        Gson gson = new Gson();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ReadingBean reading = gson.fromJson(response.body().string(), new TypeToken<ReadingBean>() {
                }.getType());
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putSerializable("reading", reading);
                message.what = 111;
                message.setData(bundle);
                handler.sendMessage(message);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
