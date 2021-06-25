package com.cny;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.javafx.image.impl.IntArgb.ToIntArgbPreConv.instance;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hh-mm-ss");
        /*Date date = new Date();
        String format = simpleDateFormat.format(date);
        System.out.println(format);*/

       /* Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
//        long timeInMillis = instance.getTimeInMillis();
        System.out.println(simpleDateFormat.format(time));
        try {
            Date parse = simpleDateFormat.parse("20181012 10-10-10");
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        /*Map<String, String> map = new HashMap();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getValue());
        }*/


        /*Dep1Class dep1Class = new Dep1Class();
        JSONObject jsonObjct = dep1Class.getJsonObjct();
        System.out.println( jsonObjct.getString("name"));
        System.out.println( "Hello World!" );*/

        /*Person person = new Person();
        ConvertUtils.register(new DateLocaleConverter(), Date.class);

        try {
            BeanUtils.setProperty(person, "id", "123");
            BeanUtils.setProperty(person, "name", 18);
            BeanUtils.setProperty(person, "age", 18);
            BeanUtils.setProperty(person, "birth", "2018-10-12");
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ObjectOutputStream objectOutputStream=null;
        FileOutputStream fileOutputStream=null;
        ObjectInputStream objectInputStream=null;
        Person person = new Person();
        person.setId("12");
        person.setAge(18);
        person.setBirth(new Date());
        person.setName("zhangsan");
        try {
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("myPerson"));
             objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(person);
            byte[] bytes = byteOutputStream.getBytes();
             fileOutputStream = new FileOutputStream("myPerson.proto");
            fileOutputStream.write(bytes);
            objectInputStream = new ObjectInputStream(new ByteInputStream(bytes,bytes.length));
            Person personCopy = (Person)objectInputStream.readObject();
            System.out.println(person);
            System.out.println(personCopy);
            System.out.println(11);

        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            byteOutputStream.close();
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}
