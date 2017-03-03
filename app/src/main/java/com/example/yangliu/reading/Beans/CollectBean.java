package com.example.yangliu.reading.Beans;

import java.io.Serializable;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class CollectBean implements Serializable{
    private String title;
    private String image;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
