public class P11 {
    public static void main(String[] args) {
        for (int i = 0; 5*i <= 100; i++)
            for (int j = 0; 3*j <= 100-5*i; j++) {
            if (3*(100-5*i-3*j) + j + i == 100) {
                System.out.println(i + "\t" + j + "\t" + 3*(100-5*i-3*j));
//                System.out.println(i + j + 3*(100-5*i-3*j));
//                System.out.println(5*i + "\t" + 3*j + "\t" + (100-5*i-3*j));
//                System.out.println(5*i + 3*j + (100-5*i-3*j));
//                System.out.println("\n");
            }
        }
    }
}