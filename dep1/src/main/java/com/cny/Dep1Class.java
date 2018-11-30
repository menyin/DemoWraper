package com.cny;

import com.alibaba.fastjson.JSONObject;

public class Dep1Class {
    public JSONObject getJsonObjct(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "chexi");
        jsonObject.put("age", 18);
        return jsonObject;
    }
}
