public class P8 {
    public static void main(String[] args) {
        final int center = 40;
        int n;
        n = Integer.parseInt(args[0]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < center-i; j++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print("*");
            System.out.println();
        }
    }
}