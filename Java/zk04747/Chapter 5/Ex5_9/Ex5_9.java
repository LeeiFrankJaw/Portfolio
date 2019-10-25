import java.util.*;
import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Ex5_9 extends Applet implements ActionListener {
    JTextArea jtxtArea = new JTextArea(5, 10);
    JTextField jtxtField = new JTextField(10);
    JButton jbt1 = new JButton("Sum it");
    JButton jbt2 = new JButton("Restart");

    public void init() {
        setSize(250, 150);

        jtxtArea.setBackground(Color.CYAN);
        jtxtField.setBackground(Color.PINK);
        jtxtField.setEditable(false);

        jbt1.addActionListener(this);
        jbt2.addActionListener(this);

        add(jtxtArea);
        add(jtxtField);
        add(jbt1);
        add(jbt2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbt1) {
            int sum = 0;

            String s = jtxtArea.getText();
            StringTokenizer tokens = new StringTokenizer(s, ", \n\r\t\f");

            while (tokens.hasMoreTokens()) {
                sum += Integer.parseInt(tokens.nextToken());
            }

            jtxtField.setText("" + sum);
        } else if (e.getSource() == jbt2) {
            jtxtArea.setText(null);
            jtxtField.setText(null);
        }
    }
}
