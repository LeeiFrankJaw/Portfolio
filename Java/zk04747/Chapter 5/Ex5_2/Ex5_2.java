import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.Color;
import java.awt.FlowLayout;

public class Ex5_2 {
    public static MyWindowDemo mw1;
    public static MyWindowDemo mw2;
    
    public static void main(String[] args) {
        JButton butt1 = new JButton("我是一个按钮");
        JButton butt2 = new JButton("我是另一个按钮");
        String name1 = "我的第一个窗口";
        String name2 = "我的第二个窗口";
        
        mw1 = new MyWindowDemo(name1, butt1, Color.BLUE, 350, 450);
        mw1.setVisible(true);
        
        mw2 = new MyWindowDemo(name2, butt2, Color.MAGENTA, 300, 400);
        mw2.setVisible(true);
    }
}


class MyWindowDemo extends JFrame {
    public MyWindowDemo(String name, JButton button, Color c, int w, int h) {
        super();
        setSize(w, h);
        setTitle(name);
        Container contentPane = getContentPane();
        contentPane.add(button);
        contentPane.setBackground(c);
        contentPane.setLayout(new FlowLayout());
    }
}
