package feifan;

public class Main {
    public static void main(String[] args) {
        ChildOne childOne = new ChildOne();
        ChildTwo childTwo = new ChildTwo();

        childOne.addItem("一个男人1");
        childOne.addItem("两个男人2");
        System.out.println(childTwo.getList());
    }
}
