public class P12 {
    public static void main(String[] args) {
        double e = 1, i = 1, t = 1;
        while (t >= 1e-10) {
            t /= i;
            e += t;
            i++;
        }
        System.out.println(e);
    }
}