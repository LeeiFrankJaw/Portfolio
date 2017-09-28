public class P6 {
    public static void main(String[] args) {
        final int N = 1000;
        int n, i, s;
        for (n = 4; n <= N; n++) {
            for (i = 2, s = 1; i*i <= n; i++)
                if (n % i == 0) {
                    s += i;
                    if (i*i != n) s += n / i;
            }
            if (s == n) System.out.println(n);
        }
    }
}
            