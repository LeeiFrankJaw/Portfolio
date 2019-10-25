import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ex6_8 extends Applet {
    public void init() {
        MyFrameEx6_8 frame = new MyFrameEx6_8("Mouse Event Demo Program");
    }
}


class MyFrameEx6_8 extends JFrame implements MouseListener {
    JTextArea jta = new JTextArea(10, 20);
    JScrollPane jscp = new JScrollPane(jta);
    MyPanel p = new MyPanel();
    int x, y, r = 0, mouseFlg = 0;
    static String[] mouseStates = {"Mouse is presed", "Mouse is released",
                                   "Mouse has entered", "Mouse has exited",
                                   "Mouse is double-clicked"};

    MyFrameEx6_8(String title) {
        super(title);

        add(p);
        jta.setBackground(Color.BLUE);
        jta.setForeground(Color.YELLOW);
        jta.setLineWrap(true);
        add(jscp);
        addMouseListener(this);

        setLayout(new GridLayout(2,1));
        // setSize(200, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g) {
        r = r + 4;
        if (r > 80) {
            r = 10;
        }

        jta.append(mouseStates[mouseFlg] + " at (" + x + ", " + y + ")");
        p.print(r);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 4;
        repaint();
        }
    }

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 0;
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 1;
        repaint();
    }

    public void mouseEntered(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 2;
        repaint();
    }

    public void mouseExited(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        mouseFlg = 3;
        repaint();
    }
}


class MyPanel extends JPanel {
    public void print(int r) {
        Graphics g = getGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.RED);
        g.fillOval(10, 10, r, r);
    }
}
