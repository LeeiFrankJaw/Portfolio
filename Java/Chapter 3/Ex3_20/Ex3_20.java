import java.applet.*;
import java.awt.*;

public class Ex3_20 extends Applet {
    int pos;
    
    public void init() {
        pos = 5;
    }
    
    public void start() {
        repaint();
    }
    
    public void stop() {}
    
    public void paint(Graphics g) {
        g.drawString("��������ѧϰJava�������", 20, pos + 10);
        pos = (pos+20)%100 + 5;
    }
}