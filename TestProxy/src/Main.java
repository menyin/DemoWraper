import java.lang.reflect.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person(123, "张三");
        Object proxyInstance = new MyProxy(person).getProxyInstance();
        for (Field field : proxyInstance.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object o = field.get(proxyInstance);
            System.out.println(field.getName()+":"+o);
        }

    }



}
