public class P14 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]),
            d = (int)Math.log10(n),
            a, b, m;
        
        while (n/10 != 0) {
            m = (int)Math.pow(10, d);
            a = n / m;
            b = n % 10;
            if (a != b) {
                System.out.println("这不是一个回文整数。");
                break;
            }
            n %= m;
            n /= 10;
            d -= 2;
        }
        if (n/10 == 0) System.out.println("这是一个回文整数。");
    }
}