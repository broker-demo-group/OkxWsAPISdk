package org.okxborkerdemo.ws.entry;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: bowen
 * @description:
 * @date: 2022/6/13  11:28 PM
 **/
public class ParamMap{
    Map<String, String> param = new HashMap<>();

    public String add(String key, String value) {
        return param.put(key, value);
    }

    public String getPayLoadJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> e : param.entrySet()) {
            sb.append("\""+e.getKey() + "\":\"" + e.getValue() + "\",");
        }
        if(sb.length()>1) {
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("}");
        JsonObject json = new Gson().fromJson(sb.toString(), JsonObject.class);
        return json.toString();
    }

}
