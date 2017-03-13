package com.example.zola6.greeter.utils;

import java.util.Map;

/**
 * Created by Admin on 2017. 03. 12..
 */

public class SearchParams {

    private String expression = null;
    private Map<String, Boolean> filters = null;

    public SearchParams(String expression, Map<String, Boolean> filters){
        this.expression = expression;
        this.filters = filters;
    }

    public Map<String, Boolean> getFilters() {
        return this.filters;
    }

    public String getExpression() {
        return this.expression;
    }

}
