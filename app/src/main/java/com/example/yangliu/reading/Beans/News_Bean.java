package com.example.yangliu.reading.Beans;

import java.io.Serializable;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class News_Bean implements Serializable{
    private String title;
    private String link;
    private String author;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
