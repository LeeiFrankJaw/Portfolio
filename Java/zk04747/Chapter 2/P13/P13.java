public class P13 {
    public static void main(String[] args) {
        double pi = 4, t1 = 4, t2 = -1;
        int i = 3;
        while (t1 >= 1e-8) {
            t1 = 4.0 / i;
            pi += t1 * t2;
            t2 *= -1.0;
            i += 2;
        }
        System.out.println(pi);
        System.out.println(i / 2);
    }
}