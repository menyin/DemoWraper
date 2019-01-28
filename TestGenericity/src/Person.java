public class Person {
    public static <T> T getPerson() throws NoSuchMethodException, IllegalAccessException, InstantiationException {
        Class<?> getPerson = Person.class.getMethod("getPerson").getReturnType();
        return  (T)getPerson.newInstance();
    }
}
