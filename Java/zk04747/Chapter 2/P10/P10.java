public class P10 {
    public static void main(String[] args) {
        int a, b, c, count = 0;
        for (int n = 100; n <= 999; n++) {
            a = n / 100;
            b = n / 10 % 10;
            c = n % 10;
            if (a*a*a + b*b*b + c*c*c == n) {
                System.out.print("\t" + n);
                if (++count % 6 == 0)
                    System.out.println();
            }
        }
        System.out.println();
    }
}