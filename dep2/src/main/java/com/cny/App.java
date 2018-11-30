package com.cny;

import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Dep1Class dep1Class = new Dep1Class();
        JSONObject jsonObjct = dep1Class.getJsonObjct();
        System.out.println( jsonObjct.getString("name"));
        System.out.println( "Hello World!" );
    }
}
