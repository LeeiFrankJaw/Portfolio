public class Ex4_7 {
    public static void main(String args[]) {
        byte b[] = new byte[10], c[], d[];
        String s1, s2, s3, s4;
        
        for (int k = 0; k < 10; k++)
            b[k] = (byte)(k + 48);
        s1 = new String(b);
        s2 = new String(b, 3, 6);
        s3 = new String("ABC");
        s4 = new String("Java����");
        c = s3.getBytes();
        d = s4.getBytes();
        
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("����c�ĳ����ǣ�" + c.length);
        System.out.println("����d�ĳ����ǣ�һ������ռ�����ֽڣ���" + d.length);
        System.out.println("��ASCII��ֵ�������c[]��");
        for (int i = 0; i < c.length; i++)
            System.out.println("c" + "[" + i + "]:\t" + c[i]);
        System.out.println("��ASCII��ֵ�������d[]��");
        for (int i = 0; i < d.length; i++)
            System.out.println("d" + "[" + i + "]:\t" + d[i]);
    }
}