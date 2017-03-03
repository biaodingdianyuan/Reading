package com.example.yangliu.reading.Beans;

import java.io.Serializable;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class author  implements Serializable{
    private String nickname;
    private String followers_count;
    private String url;
    private String title;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(String followers_count) {
        this.followers_count = followers_count;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
