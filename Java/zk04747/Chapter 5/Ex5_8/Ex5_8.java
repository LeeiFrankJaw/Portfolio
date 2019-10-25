import java.applet.*;
import javax.swing.*;
import java.awt.event.*;

public class Ex5_8 extends Applet {
    static JTextField text1 = new JTextField(10);
    static JTextField text2 = new JTextField(10);

    public void init() {
        Sqr sqr = new Sqr();
        add(text1);
        add(text2);
        text1.addActionListener(sqr);
        setSize(400, 300);
    }
}


class Sqr implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Ex5_8.text1) {
            long n = Long.parseLong(Ex5_8.text1.getText());
            Ex5_8.text2.setText("" + n*n);
        }
    }
}
