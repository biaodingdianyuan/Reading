package com.example.yangliu.reading.Beans;

import java.io.Serializable;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class ReadingBean implements Serializable{
    private BookBean books[];

    public BookBean[] getBooks() {
        return books;
    }

    public void setBooks(BookBean[] books) {
        this.books = books;
    }
}
