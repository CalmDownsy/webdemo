package com.webdemo.common.utils;

import io.swagger.models.auth.In;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/3/8 15:04
 * @Description:
 */
public class Query extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    private int offset;
    private int limit;


    public Query(Map<String, Object> params) {
        this.putAll(params);
        this.offset = Integer.parseInt(params.get("offset").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("offset", this.offset);
        this.put("limit", this.limit);
        this.put("page", this.offset / this.limit + 1);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
