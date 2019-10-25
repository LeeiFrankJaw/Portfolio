import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Ex6_6 extends Applet {
    public void init() {
        MyWindow window = new MyWindow("Window with dialogue");
    }
}


class MyWindow extends JFrame implements ActionListener {
    protected static JTextField jtfFruit = new JTextField(20);
    protected static JTextField jtfFood = new JTextField(20);
    // private static int flg = 0;
    private JButton jbtFruit = new JButton("Select fruit");
    private JButton jbtFood = new JButton("Select food");

    MyWindow() {
        this("");
    }

    MyWindow(String title) {
        jbtFruit.addActionListener(this);
        jbtFood.addActionListener(this);

        add(jbtFruit);
        add(jbtFood);
        add(jtfFruit);
        add(jtfFood);

        setTitle(title);
        setLayout(new GridLayout(2, 2));
        // setSize(200,200);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* public static void returnName(String sInput) {
        switch (flg) {
            case 1:
                jtfFruit.setText("The fruit " + sInput + " is selected.");
                break;
            case 2:
                jtfFood.setText("The food " + sInput + " is selected.");
                break;
            }
            } */

    public void actionPerformed(ActionEvent e) {
        MyDialog dialog;

        if (e.getSource() == jbtFruit) {
            dialog = new MyDialog(this, "fruit", jtfFruit);
            // dialog.setVisible(true);
            // flg = 1;
        } else if (e.getSource() == jbtFood) {
            dialog = new MyDialog(this, "food", jtfFood);
            // dialog.setVisible(true);
            // flg = 2;
        }
    }
}


class MyDialog extends JDialog implements ActionListener {
    JButton jbtDone = new JButton("Done");
    JLabel jlblTitle;
    JTextField jtf = new JTextField(10);
    JTextField jtfTrigger;


    MyDialog(JFrame frame, String title, JTextField Trigger) {        
        super(frame, title);

        jlblTitle = new JLabel("type the name of " + title);
        jtfTrigger = Trigger;
        jbtDone.addActionListener(this);

        add(jlblTitle);
        add(jtf);
        add(jbtDone);

        setLayout(new FlowLayout());
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // MyWindow.returnName(jtf.getText());
        // setVisible(false);
        if (jtfTrigger == MyWindow.jtfFruit) {
            jtfTrigger.setText("The fruit " + jtf.getText() + " is selected.");
        } else if (jtfTrigger == MyWindow.jtfFood) {
            jtfTrigger.setText("The food " + jtf.getText() + " is selected.");
        }
        dispose();
    }
}
