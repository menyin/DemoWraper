import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        RandomAccessFile rd;
        try {
            String path = Main.class.getResource("").getPath();
            File file = new File(path+"TestRandomAccessFile2.txt");
//            file.createNewFile();
            rd= new RandomAccessFile(file, "rw");
            //写
           /* String str = "社会主义好";
            byte[] bytes = str.getBytes();
            rd.write(bytes);*/

           //读
            /*String s = rd.readLine();
            s = new String(s.getBytes("ISO-8859-1"), "utf-8");
            System.out.println(s);*/

            Pattern pattern = Pattern.compile("(\\d+)");
            Matcher matcher = pattern.matcher("123dddd456aaaa789");
            while (matcher.find()) {
                System.out.println(matcher.group());
                System.out.println(matcher.groupCount());
                System.out.println("======================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

    }
}
