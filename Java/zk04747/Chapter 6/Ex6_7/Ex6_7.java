import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ex6_7 {
    public static void main(String[] args) {
        MyFrameEx6_7 frame = new MyFrameEx6_7("Scroll Bar Demo");
    }
}


class MyFrameEx6_7 extends JFrame implements ActionListener, AdjustmentListener {
    private JButton jbt = new JButton("Turn on/off scroll bar");
    private JTextField jtf = new JTextField("Scroll bar is off", 20);
    private MyScrollBar jscb = new MyScrollBar(10, 10, 0, 255);
    private boolean isOpen = false;

    MyFrameEx6_7(String title) {
        super(title);

        jbt.addActionListener(this);
        jscb.addAdjustmentListener(this);

        add(jbt, BorderLayout.NORTH);
        add(jtf);
        add(jscb, BorderLayout.EAST);

        // setLayout(new GridLayout(2, 1));
        // setSize(200, 100);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbt) {
            if (isOpen) {
                jtf.setText("Scroll bar is off");
            } else {
                jtf.setText("Scroll bar is on");
            }
            isOpen = !isOpen;
        }
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (isOpen) {
            jtf.setText("The selected value is "
                        + ((MyScrollBar) e.getAdjustable()).getValue());
        }
    }
}


class MyScrollBar extends JScrollBar {
    MyScrollBar(int init, int len, int low, int high) {
        super(JScrollBar.VERTICAL, init, len, low, high);
    }

    public Dimension getPreferredSize() {
        return new Dimension(20, 125);
    }
}
