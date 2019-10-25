import java.applet.Applet;
import java.awt.*;

public class Ex7_2 extends Applet {
    public void paint(Graphics g) {
        for (int i = 0; i <= 10; i++) {
            Color color = new Color(i * 25 + 5, 0, 0);
            g.setColor(color);
            g.fillRect(i * 32 + 5, 2, 28, 28);
        }

        for (int i = 0; i <= 10; i++) {
            Color color = new Color(0, i * 25 + 5, 0);
            g.setColor(color);
            g.fillRect(i * 32 + 5, 32, 28, 28);
        }

        for (int i = 0; i <= 10; i++) {
            Color color = new Color(0, 0, i * 25 + 5);
            g.setColor(color);
            g.fillRect(i * 32 + 5, 62, 28, 28);
        }
    }
}
