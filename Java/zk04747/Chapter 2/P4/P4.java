public class P4 {
    public static void main(String[] args) {
        int a= 4, b, c;
        b = a * a++;
        c = ++a + a;
        System.out.println(a + "\t" + b + "\t" + c);
//        System.out.println(b);
//        System.out.println(c);
    }
}