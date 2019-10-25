import javax.swing.*;

public class Ex5_1 {
    public static void main(String args[]) {
        JFrame mw = new JFrame("我的第一个窗口");
        JButton button = new JButton("我是一个按钮");
        
        mw.setSize(250, 200);
        mw.getContentPane().add(button);
        mw.setVisible(true);
        mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
