import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File(".");

//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getCanonicalPath());
        /*String path = Main.class.getResource("").getPath();
        File file = new File(path + "Main.class");
//        System.out.println(path);
        System.out.println(file.exists());*/

      /*  File file = new File("./Main.class");
        System.out.println(file.exists());
        System.out.println(file.getCanonicalPath());*/


        FileInputStream fs=null;
        FileOutputStream fw=null;
        try {
            fs = new FileInputStream(Main.class.getResource("").getPath() + "test.txt");
            fw = new FileOutputStream(Main.class.getResource("").getPath() + "testCopy.txt", true);
            byte[] bytes = new byte[4];

            int read;
            while ((read = fs.read(bytes)) != -1) {
                System.out.print(new String(bytes));
                fw.write(bytes, 1, 2);// => el12
            }
            System.out.println("\r\n");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            fs.close();
            fw.close();
        }


    }
}
