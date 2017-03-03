package com.example.yangliu.reading.Beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class StroiesBean implements Serializable {
    private String title;
    private  String  ga_prefix;
    private ArrayList images;
    private String type;
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public ArrayList getImages() {
        return images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
