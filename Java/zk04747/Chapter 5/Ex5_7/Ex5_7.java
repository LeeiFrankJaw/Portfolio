import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class MyPanelEx5_7 extends JPanel {
    MyPanelEx5_7(int a) {
        add(new JLabel("I am the label " + a + "."));
    }
}


public class Ex5_7 extends Applet implements ActionListener {
    CardLayout myCard = new CardLayout();
    MyPanelEx5_7[] myPanel = new MyPanelEx5_7[10];
    JPanel p = new JPanel(myCard);

    private void addButton(JPanel pnl, String butt, ActionListener listener) {
        JButton jbt = new JButton(butt);
        jbt.addActionListener(listener);
        pnl.add(jbt);
    }

    public void init() {
        JPanel p2 = new JPanel();

        setLayout(new BorderLayout());

        for (int i = 0; i < 10; i++) {
            myPanel[i] = new MyPanelEx5_7(i + 1);
            p.add("A" + i, myPanel[i]);
        }

        addButton(p2, "First", this);
        addButton(p2, "Last", this);
        addButton(p2, "Prev", this);
        addButton(p2, "Next", this);

        add(p, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        setSize(400, 150);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("First")) {
            myCard.first(p);
        } else if (e.getActionCommand().equals("Last")) {
            myCard.last(p);
        } else if (e.getActionCommand().equals("Prev")) {
            myCard.previous(p);
        } else if (e.getActionCommand().equals("Next")) {
            myCard.next(p);
        }
    }
}
