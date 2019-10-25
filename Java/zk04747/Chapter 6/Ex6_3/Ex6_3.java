import java.applet.Applet;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class Ex6_3 extends Applet {
    MyFrameEx6_3 frame = new MyFrameEx6_3("List Demo");
}


class MyFrameEx6_3 extends JFrame implements ListSelectionListener {
    String[] newsGB = {"Daily Telegraph", "The Financial Times",
                       "The Guardian", "The Times"};
    String[] newsUS = {"New York Times", "USA Today",
                       "Wall Street Journal", "Washington Post"};
    JList jlst1 = new JList(newsGB);
    JList jlst2 = new JList(newsUS);
    JTextArea jta = new JTextArea(10, 20);

    MyFrameEx6_3() {
        jlst1.setVisibleRowCount(3);
        jlst1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlst1.addListSelectionListener(this);
        jlst2.setVisibleRowCount(2);
        jlst2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jlst2.addListSelectionListener(this);

        add(jlst1);
        add(jlst2);
        add(jta);

        getContentPane().setBackground(Color.BLUE);
        getContentPane().setLayout(new GridLayout(2, 2));
              // setSize(200, 500);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    MyFrameEx6_3(String title) {
        this();
        setTitle(title);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == jlst1) {
            jta.setText(null);
            Object listValue = ((JList) e.getSource()).getSelectedValue();
            String selectedName = listValue.toString();
            for (int i = 0; i < newsGB.length; i++) {
                if (newsGB[i].equals(selectedName)) {
                    jta.append(selectedName + ": seleted\n");
                }
            }
        } else if (e.getSource() == jlst2) {
            jta.setText(null);
            int[] tempList = jlst2.getSelectedIndices();
            for (int i = 0; i < tempList.length; i++) {
                jta.append(newsUS[tempList[i]] + ": selected\n");
            }
        }
    }
}
