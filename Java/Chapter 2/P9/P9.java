public class P9 {
    public static void main(String[] args) {
        int x = 5, y = 110;
        while (x <= y)
            x *= 2;
        System.out.println(x + "\t" + y);
        x = 5;
        do {
            x = y / x;
            y = y - x;
        } while (y >= 1);
        System.out.println(x + "\t" + y);
    }
}