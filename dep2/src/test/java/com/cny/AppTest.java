package com.cny;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Dep1Class dep1Class = new Dep1Class();
        JSONObject jsonObjct = dep1Class.getJsonObjct();
        System.out.println( jsonObjct.getString("name"));
        assertTrue( true );
    }
}
