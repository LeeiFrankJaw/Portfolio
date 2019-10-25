import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ex5_10 extends Applet implements ActionListener {
    JLabel jlbl = new JLabel("Enumerate all the multiples of the factor"
                             + " between the start and end number.",
                             JLabel.CENTER);
    JPanel_1 p1 = new JPanel_1();
    JPanel_2 p2 = new JPanel_2();

    public void init() {
        setLayout(new GridLayout(3, 1));
        setSize(500, 400);

        p2.jbt.addActionListener(this);

        add(jlbl);
        add(p1);
        add(p2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == p2.jbt) {
            int start = Integer.parseInt(p1.jtxt1.getText());
            int end = Integer.parseInt(p1.jtxt2.getText());
            int factor = Integer.parseInt(p1.jtxt3.getText());
            String result = "";

            p2.jta.setText(null);
            for (int i = start; i <= end; i++) {
                if (i % factor == 0) {
                    result = result + " " + i;
                    // p2.jta.append(" " + i);
                }
            }
            
            p2.jta.setText(result.trim());
        }
    }
}


class JPanel_1 extends JPanel {
    JLabel jlbl1 = new JLabel("Start:", JLabel.CENTER);
    JLabel jlbl2 = new JLabel("End:", JLabel.CENTER);
    JLabel jlbl3 = new JLabel("Factor:", JLabel.CENTER);
    JTextField jtxt1 = new JTextField("5", 10);
    JTextField jtxt2 = new JTextField("30", 10);
    JTextField jtxt3 = new JTextField("2", 10);

    JPanel_1() {
        setLayout(new GridLayout(1, 6));

        add(jlbl1);
        add(jtxt1);

        add(jlbl2);
        add(jtxt2);

        add(jlbl3);
        add(jtxt3);
    }
}


class JPanel_2 extends JPanel {
    JLabel jlbl1 = new JLabel("Result:", JLabel.CENTER);
    JLabel jlbl2 = new JLabel();
    JTextArea jta = new JTextArea(4, 10);
    JScrollPane jscp = new JScrollPane(jta);
    JButton jbt = new JButton("Enumerate");

    JPanel_2() {
        setLayout(new GridLayout(1, 4));
        jta.setLineWrap(true);

        add(jlbl1);
        add(jscp);
        add(jlbl2);
        add(jbt);
    }
}
