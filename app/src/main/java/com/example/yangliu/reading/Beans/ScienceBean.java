package com.example.yangliu.reading.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuhaifeng on 2017/2/25.
 */

public class ScienceBean implements Serializable {
    private String now;
    private String ok;
    private String limit;


    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private List<ResultBean> result;
    private String offset;
    private String total;
}
