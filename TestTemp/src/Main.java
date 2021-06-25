import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
       //泛型父类的静态字段是否是公用的。子类改变对应的静态字段，其它子类是否会跟着改变。
       /* Person<ManPerson> man = Person.getPerson(ManPerson.class);
        Person<GirlPerson> girl = Person.getPerson(GirlPerson.class);*/
        /*ManPersonWrapper man = new ManPersonWrapper(ManPerson.class);
        GirlPersonWrapper girl = new GirlPersonWrapper(GirlPerson.class);
        man.addItem("一个男人");
        man.addItem("两个男人");
        System.out.println(girl.getList());*/
//        Class<?> mm = String.class.forName("Mm");
//        Class<?> mm = Mm.class.getClassLoader().loadClass("Mm");
//        String ss = "";

        /*String a="www";
        String b="www";
        String c=new String("www");
        String d=new String("www");
        String f=new String("www");
        System.out.println(a==b);
        System.out.println(a==c);
        System.out.println(d==f);*/





    }
}
