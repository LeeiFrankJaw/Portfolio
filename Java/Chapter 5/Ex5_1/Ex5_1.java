import javax.swing.*;

public class Ex5_1 {
    public static void main(String args[]) {
        JFrame mw = new JFrame("�ҵĵ�һ������");
        JButton button = new JButton("����һ����ť");
        
        mw.setSize(250, 200);
        mw.getContentPane().add(button);
        mw.setVisible(true);
        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
