import java.applet.Applet;
import java.awt.*;

public class Ex7_3 extends Applet {
    int i = 1;

    public void init() {
        setBackground(Color.YELLOW);
    }

    public void paint(Graphics g) {
        i = i + 8;
        if (i > 160) {
            i = 1;
        }
        g.setColor(Color.RED);
        g.fillRect(i, 10, 20, 20);
        g.drawString("we're learning update method!", 100, 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        repaint();
    }

    public void update(Graphics g) {
        g.clearRect(i, 10, 20, 20);
        paint(g);
    }
}
