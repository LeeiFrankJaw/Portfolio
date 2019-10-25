import java.applet.*;
import javax.swing.*;
import java.awt.*;

public class Ex5_6 extends Applet {
    MyWindow myWindow;

    public void init() {
        myWindow = new MyWindow(400, 350);
        // add(myWindow);
        // setSize(400, 350);
    }
}


class MyWindow extends JFrame {
    public MyWindow(int w, int h) {
        setTitle("Instace of Scroll Pane");
        setSize(w, h);

        Container container = getContentPane();
        container.setPreferredSize(new Dimension(w, h));
        container.setLayout(new BorderLayout());

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(6,6));

        for (int i = 0; i < 6; i++) {
            p.add(new JLabel());
            for (int j = 1; j <= 2; j++) {
                p.add(new JButton("Button " + (2*i + j)));
                p.add(new JLabel("Label " + (2*i + j)));
            }
            p.add(new JLabel());
        }

        p.setBackground(Color.BLUE);
        p.setPreferredSize(new Dimension(w + 60, h + 60));

        JScrollPane jscrlPane = new JScrollPane(p);
        jscrlPane.setPreferredSize(new Dimension(w - 60, h - 60));
        //  As a convenience, the add, remove, and setLayout methods
        //  of this class are overridden, so that they delegate calls
        //  to the corresponding methods of the ContentPane.
        add(jscrlPane, BorderLayout.CENTER);
        setVisible(true);
        pack;
    }
}


class ScrollPane extends JScrollPane {
    public ScrollPane(Component p) {
        super(p);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }
}
