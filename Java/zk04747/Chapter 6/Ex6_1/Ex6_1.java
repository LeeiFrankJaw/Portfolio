import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

public class Ex6_1 {
    public static void main(String[] args) {
        MyFrameEx6_1 frame = new MyFrameEx6_1("Ex6_1");
    }
}


class MyFrameEx6_1 extends JFrame {
    MyFrameEx6_1() {
        setLayout(new FlowLayout());
        add(new JCheckBox("Soccer"));
        add(new JCheckBox("Volleyball"));
        add(new JCheckBox("Basketball"));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 360);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    MyFrameEx6_1(String title) {
        this();
        setTitle(title);
    }
}
