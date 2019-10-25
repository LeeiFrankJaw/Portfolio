import java.applet.Applet;
import java.awt.Color;
// import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class Ex6_2 extends Applet {
    MyFrameEx6_2 frame = new MyFrameEx6_2("Item Processing Demo");
}


class MyFrameEx6_2 extends JFrame implements ItemListener {
    final static String[] Manufacturer = {"HP", "IBM", "Dell"};
    final static double[][] priceTable = {{0.0, 12.0, 11.5, 11.0},
                                          {0.0, 17.0, 16.5, 16.0},
                                          {0.0, 16.5, 16.0, 15.8}};
    static int maker = -1;
    static int num = 0;
    // Container container = getContentPane();
    JPanel_1 p1 = new JPanel_1();
    JPanel_2 p2 = new JPanel_2();
    JLabel jlbl1 = new JLabel("Product Info", JLabel.CENTER);
    JLabel jlbl2 = new JLabel("Product Price", JLabel.CENTER);
    JTextArea jta1 = new JTextArea();
    JTextArea jta2 = new JTextArea();

    MyFrameEx6_2() {
        jta1.setLineWrap(true);
        jta2.setLineWrap(true);

        getContentPane().setLayout(new GridLayout(3, 2));
        getContentPane().add(jlbl1);
        getContentPane().add(jlbl2);
        getContentPane().add(p1);
        getContentPane().add(p2);
        getContentPane().add(jta1);
        getContentPane().add(jta2);

        p1.jrbt1.addItemListener(this);
        p1.jrbt2.addItemListener(this);
        p1.jrbt3.addItemListener(this);
        p2.jcbx1.addItemListener(this);
        p2.jcbx2.addItemListener(this);
        p2.jcbx3.addItemListener(this);

        setSize(450, 300);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    MyFrameEx6_2(String title) {
        this();
        setTitle(title);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getItemSelectable() == p1.jrbt1) {
            maker = 0;
            jta1.setText(Manufacturer[0] + " sells at");
        } else if (e.getItemSelectable() == p1.jrbt2) {
            maker = 1;
            jta1.setText(Manufacturer[1] + " sells at");
        } else if (e.getItemSelectable() == p1.jrbt3) {
            maker = 2;
            jta1.setText(Manufacturer[2] + " sells at");
        } else if (e.getItemSelectable() == p2.jcbx1) {
            num = 1;
        } else if (e.getItemSelectable() == p2.jcbx2) {
            num = 2;
        } else if (e.getItemSelectable() == p2.jcbx3) {
            num = 3;
        }

        if (maker != -1) {
            jta2.setText(priceTable[maker][num] + " thousand yuan per computer.");
        }
    }
}


class JPanel_1 extends JPanel {
    ButtonGroup g = new ButtonGroup();
    JRadioButton jrbt1 = new JRadioButton(MyFrameEx6_2.Manufacturer[0]
                                          + " Computer", false);
    JRadioButton jrbt2 = new JRadioButton(MyFrameEx6_2.Manufacturer[1]
                                          + " Computer", false);
    JRadioButton jrbt3 = new JRadioButton(MyFrameEx6_2.Manufacturer[2]
                                          + " Computer", false);

    JPanel_1() {

        g.add(jrbt1);
        g.add(jrbt2);
        g.add(jrbt3);

        setLayout(new GridLayout(3, 1));
        setBorder(new TitledBorder("Select one from three"));

        add(jrbt1);
        add(jrbt2);
        add(jrbt3);
    }
}


class JPanel_2 extends JPanel {
    ButtonGroup g = new ButtonGroup();
    JCheckBox jcbx1 = new JCheckBox("Purchase one");
    JCheckBox jcbx2 = new JCheckBox("Purchase two");
    JCheckBox jcbx3 = new JCheckBox("Purchase three");

    JPanel_2() {
        g.add(jcbx1);
        g.add(jcbx2);
        g.add(jcbx3);

        setLayout(new GridLayout(3, 1));
        setBorder(new LineBorder(Color.BLACK));

        add(jcbx1);
        add(jcbx2);
        add(jcbx3);
    }
}
