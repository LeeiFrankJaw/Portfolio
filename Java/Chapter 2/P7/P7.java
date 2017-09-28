public class P7 {
    public static void main(String[] args) {
        int n;
        double x;
        x = Double.parseDouble(args[0]);
        for (n = 1; n*n <= x; n++);
        System.out.println(--n);
    }
}