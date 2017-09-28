public class Ex4_3 {
    public static void main(String[] args) {
        boolean bTbl[][] = new boolean[4][];
        int i, j;
        
        for (i = 0; i < bTbl.length; i++)
            bTbl[i] = new boolean[i+1];
        
        for (i = 0; i < bTbl.length; i++) {
            for (j = 0; j < bTbl[i].length - 1; j++)
                System.out.print(bTbl[i][j] + " ");
            System.out.println(bTbl[i][j]);
        }
    }
}