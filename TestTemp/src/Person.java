import java.util.ArrayList;
import java.util.List;

public class Person<T> {
    private final static List<String> list = new ArrayList<>();
    private  Class<T> type;

    public Person(Class<T> type) {
        this.type = type;
    }

    public static <T> Person<T> getPerson(Class<T> clazz){
        return new Person(clazz);
    }
    public void addItem(String item){
        list.add(item);
    }

    public List<String> getList() {
        return  list;
    }


}
