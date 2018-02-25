package com.firat.server.data;

import com.google.gson.Gson;

public class SearchRequest {
    private String query;
    private String type;
    private int limit;
    private int offset;

    public SearchRequest() { }
    public SearchRequest(String query,String type, int limit,int offset)
    {
        this.query = query;
        this.type = type;
        this.limit = limit;
        this.offset = offset;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
