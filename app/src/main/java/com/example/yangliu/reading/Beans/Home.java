package com.example.yangliu.reading.Beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class Home implements Serializable {
    private String date;
    private ArrayList<StroiesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<StroiesBean> getStories() {
        return stories;
    }

    public void setStories(ArrayList<StroiesBean> stories) {
        this.stories = stories;
    }
}
