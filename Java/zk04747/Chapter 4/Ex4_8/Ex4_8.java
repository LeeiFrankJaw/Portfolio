import java.util.*;

public class Ex4_8 {
    public static void main(String[] args) {
        Date date1 = new Date(), date2;
        String t;
        double sum = 0.0;
        
        System.out.println("日期及时间是：" + date1);
        t = date1.toString();
        t = t.substring(11, t.indexOf('C'));
        System.out.println("现在的时间是：" + t);
        for (double i = 0.0; i < 80000000; i++)
            sum += i;
        System.out.println(sum);
        date2 = new Date();
        t = date2.toString();
        t = t.substring(11, t.indexOf('C'));
        System.out.println("现在的时间是：" + t);
    }
}