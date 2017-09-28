public class Ex4_5 {
    public static void main(String[] args) {
        String s = "1234567788",
            s2 = "  1234567788  ",
            str, str2;
        
        str = s.replace('7', 'A');
        System.out.println("s = " + s + " str = " + str);
        
        str2 = s2.trim();
        System.out.println("s2 = " + s2 + " str2 = " + str2);
    }
}