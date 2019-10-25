import java.applet.*;
import javax.swing.*;

public class Ex5_4 extends Applet {
    MyPanelEx5_4 panel1, panel2;
    JButton button;
    public void init() {
        panel1 = new MyPanelEx5_4("Confirm", "Cancel",
                             "Label, we are in the panel 1.");
        panel2 = new MyPanelEx5_4("Confirm", "Cancel",
                             "Label, we are in the panel 2.");
        button = new JButton("I am not the button in the panel");
        add(panel1);
        add(panel2);
        add(button);
        setSize(360, 200);
    }
}


class MyPanelEx5_4 extends JPanel {
    JButton button1, button2;
    JLabel label;
    
    MyPanelEx5_4(String s1, String s2, String s3) {
        button1 = new JButton(s1);
        button2 = new JButton(s2);
        label = new JLabel(s3);
        add(button1);
        add(button2);
        add(label);
    }
}
