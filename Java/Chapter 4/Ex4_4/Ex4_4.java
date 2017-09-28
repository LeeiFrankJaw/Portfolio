public class Ex4_4 {
    public static void main(String[] args) {
        String s1 = "ABC";
        String s2 = "DEFG";
        
        System.out.println(s1 + s2);
        
        s1 = "ABC";
        s2 = "XYZ";
        s1 = s1.concat(s2);
        
        System.out.println(s1);
    }
}