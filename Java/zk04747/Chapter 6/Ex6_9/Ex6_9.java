import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyWindowEx6_9 extends JFrame {
    public MyWindowEx6_9(String s) {
        JScrollBar xAxis = new JScrollBar(JScrollBar.HORIZONTAL, 50, 1, 0, 100);
        JScrollBar yAxis = new JScrollBar(JScrollBar.VERTICAL, 50, 1, 0, 100);
        MyListenerEx6_9 listener = new MyListenerEx6_9(xAxis, yAxis, 238, 118);
        JPanel scrolledCanvas = new JPanel();

        setLocationRelativeTo(null);
        scrolledCanvas.setLayout(new BorderLayout());
        scrolledCanvas.add(listener, BorderLayout.CENTER);
        scrolledCanvas.add(xAxis, BorderLayout.SOUTH);
        scrolledCanvas.add(yAxis, BorderLayout.EAST);
        add(scrolledCanvas, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


class MyListenerEx6_9 extends JComponent implements MouseListener,
        MouseMotionListener, AdjustmentListener {
    private int x, y;
    private JScrollBar xScrollBar;
    private JScrollBar yScrollBar;

    private void updateScrollBars(int x, int y) {
        int d ;

        d = (int) (((float) x / (float) getSize().width) * 100.0);
        xScrollBar.setValue(d);
        d = (int) (((float) y / (float) getSize().width) * 100.0);
        yScrollBar.setValue(d);
    }

    public MyListenerEx6_9(JScrollBar xaxis, JScrollBar yaxis,
                           int x0, int y0) {
        xScrollBar = xaxis;
        yScrollBar = yaxis;
        x = x0;
        y = y0;
        xScrollBar.addAdjustmentListener(this);
        yScrollBar.addAdjustmentListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(getBackground());
        Dimension size = getSize();
        g.fillRect(0, 0, size.width, size.width);
        g.setColor(Color.blue);
        g.fillRect(x, y, 50, 50);
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        updateScrollBars(x, y);
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        updateScrollBars(x, y);
        repaint();
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == xScrollBar)
            x = (int) ((float) (xScrollBar.getValue() / 100.0)
                       * getSize().width);
        else if (e.getSource() == yScrollBar)
            y = (int) ((float) (yScrollBar.getValue() / 100.0)
                       * getSize().height);
        repaint();
    }
}


public class Ex6_9 {
    public static void main(String[] args) {
        MyWindowEx6_9 window = new MyWindowEx6_9("滚动条示意程序");
    }
}
