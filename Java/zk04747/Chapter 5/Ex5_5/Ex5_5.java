import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Ex5_5 {
    public static void main(String[] args) {
        JLabel jlbl1, jlbl2, jlbl3, jlbl4, jlbl5;
        JFrame frame = new JFrame("I am a frame.");

        frame.setSize(250, 200);

        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout(5, 5));

        jlbl1 = new JLabel("East Label", JLabel.CENTER);
        jlbl2 = new JLabel("South Label", JLabel.CENTER);
        jlbl3 = new JLabel("West Label", JLabel.CENTER);
        jlbl4 = new JLabel("North Label", JLabel.CENTER);
        jlbl5 = new JLabel("Center Label", JLabel.CENTER);

        /* LineBorder lineBorder = new LineBorder(Color.BLACK, 1);
        jlbl1.setBorder(lineBorder);
        jlbl2.setBorder(lineBorder);
        jlbl3.setBorder(lineBorder);
        jlbl4.setBorder(lineBorder);
        jlbl5.setBorder(lineBorder); */

        container.add(jlbl1, BorderLayout.EAST);
        container.add(jlbl2, BorderLayout.SOUTH);
        container.add(jlbl3, BorderLayout.WEST);
        container.add(jlbl4, BorderLayout.NORTH);
        container.add(jlbl5, BorderLayout.CENTER);

        // frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
