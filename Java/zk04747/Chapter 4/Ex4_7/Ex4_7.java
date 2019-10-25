public class Ex4_7 {
    public static void main(String args[]) {
        byte b[] = new byte[10], c[], d[];
        String s1, s2, s3, s4;
        
        for (int k = 0; k < 10; k++)
            b[k] = (byte)(k + 48);
        s1 = new String(b);
        s2 = new String(b, 3, 6);
        s3 = new String("ABC");
        s4 = new String("Java程序");
        c = s3.getBytes();
        d = s4.getBytes();
        
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("数组c的长度是：" + c.length);
        System.out.println("数组d的长度是（一个汉字占两个字节）：" + d.length);
        System.out.println("用ASCII数值输出数组c[]：");
        for (int i = 0; i < c.length; i++)
            System.out.println("c" + "[" + i + "]:\t" + c[i]);
        System.out.println("用ASCII数值输出数组d[]：");
        for (int i = 0; i < d.length; i++)
            System.out.println("d" + "[" + i + "]:\t" + d[i]);
    }
}