package com.example.zola6.greeter.utils;

import java.util.Map;

/**
 * Created by Admin on 2017. 03. 12..
 */

public class QueryBuilder {

    public String buildQuery(SearchParams searchParams){

        String expression = searchParams.getExpression();
        StringBuilder query = new StringBuilder("SELECT * FROM Message WHERE approved = 1");
        if(expression != "" && expression != null){
            query.append(" AND sms_text LIKE '%").append(expression).append("%'");
        }
        StringBuilder queryLabels = new StringBuilder("");
        for ( Map.Entry<String, Boolean> entry : searchParams.getFilters().entrySet()){
            if ( entry.getValue() ){
                if (queryLabels.toString() == ""){
                    queryLabels.append("sms_label=").append(entry.getKey());
                }else{
                    queryLabels.append("OR sms_label=").append(entry.getKey());
                }
            }
        }
        if (queryLabels.toString() != ""){
            query.append("(").append(queryLabels.toString()).append(")");
        }
        return query.toString();
    }

}
