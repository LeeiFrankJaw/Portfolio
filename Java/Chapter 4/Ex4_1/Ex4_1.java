import java.applet.*;
import java.awt.*;

public class Ex4_1 extends Applet {
    int a[];
    
    public void init() {
        a = new int[5];
        a[0] = 100;
        a[1] = 101;
        a[2] = 102;
        a[3] = 103;
        a[4] = 104;
    }
    
    public void paint(Graphics g) {
        g.drawString("a[0] = " + a[0] + " a[1] = " + a[1] + " a[2] = " + a[2], 12, 12);
        g.drawString("a[3] = " + a[3] + " a[4] = " + a[4], 12, 32);
    }
}