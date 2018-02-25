package com.firat.server.data;

import com.google.gson.Gson;
import io.swagger.annotations.ApiModelProperty;

public class SearchRequest {
    @ApiModelProperty(value = "Search query keywords and optional field filters and operators."
            ,required = true
            ,example = "şebnem ferah")
    private String query;
    @ApiModelProperty(value = "A comma-separated list of item types to search across. Valid types is only the album"
            ,required = true
            ,example = "album")
    private String type;
    @ApiModelProperty(value = "Maximum number of results to return."
            ,required = true
            ,example = "10")
    private int limit;
    @ApiModelProperty(value = "The index of the first result to return."
            ,required = true
            ,example = "0")
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
