import java.util.*;
public class Ex4_6 {
    public static void main(String args[]) {
        String s1 = " public static void, main",
            s2 = " StringTokenizer pas2? = new StringTokenizer ",
            s;
        StringTokenizer pas1 = new StringTokenizer(s1, " ,"),
            pas2 = new StringTokenizer(s2, " ?");
        int n1 = pas1.countTokens(), n2 = pas2.countTokens();
        
        System.out.println("s1�е��ʣ�" + n1 + "����ȫ���������£�");
        while (pas1.hasMoreTokens()) {
            s = pas1.nextToken();
            System.out.println(s);
        }
        
        System.out.println("s2�е��ʣ�" + n2 + "����ȫ���������£�");
        while (pas1.hasMoreTokens()) {
            s = pas1.nextToken();
            System.out.println(s);
        }
    }
}