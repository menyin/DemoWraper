package testchain;


import javax.sql.rowset.Predicate;

public class Test {
    public static void main(String[] args) {


        BootJob b1 = new AbstractBootJob() {
            @Override
            protected void startTask() {
                System.out.println(11);
            }

            @Override
            protected void stopTask() {

            }
        };
        BootJob b2 = new AbstractBootJob() {
            @Override
            protected void startTask() {
                System.out.println(22);
            }

            @Override
            protected void stopTask() {

            }
        };
        BootJob b3 = new AbstractBootJob() {
            @Override
            protected void startTask() {
                System.out.println(33);
            }

            @Override
            protected void stopTask() {

            }
        };

        b1.setNext(b2).setNext(b3);
        b1.start();

           }
}
