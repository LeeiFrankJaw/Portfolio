import javax.swing.*;

public class Ex2_15 {
    public static void main(String[] args) {
        long m, n, k;
        int j;
        String result = (String) JOptionPane.showInputDialog(null,
                "请输入一个整数（>2）", "输入对话框", JOptionPane.PLAIN_MESSAGE,
                null, null, null);
        m = Integer.parseInt(result);
        System.out.print("\t2");
        j = 1;
        for (n = 3L; n <= m; n += 2) {
            for (k = 3L; k*k <= n; k += 2L)
                if (n % k == 0) break;
            if (k * k > n) {
                if (j++ % 10 == 0)
                    System.out.println();
                System.out.print("\t" + n);
            }
        }
        System.out.println();
    }
}
