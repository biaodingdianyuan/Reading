package com.example.yangliu.reading.Beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class ResultBean implements Serializable {
    private ArrayList<author> authors;
    private String now;

    public ArrayList<author> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<author> authors) {
        this.authors = authors;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getTitle_hide() {
        return title_hide;
    }

    public void setTitle_hide(String title_hide) {
        this.title_hide = title_hide;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    private String title_hide;
    private String url;
    private String small_image;

}
