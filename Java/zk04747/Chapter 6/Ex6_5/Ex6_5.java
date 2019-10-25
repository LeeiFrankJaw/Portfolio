import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Ex6_5 implements ActionListener {
    static JButton jbt = new JButton("Open my Window of Sports & Entertainment");
    static LaunchFrame frame = new LaunchFrame();
    static MenuWindow window = new MenuWindow("Window of Sports & Entertainment",
                                              200, 100);
    boolean isOpen = false;

    public static void main(String[] args) {
        Ex6_5 listener = new Ex6_5();

        jbt.addActionListener(listener);
        frame.add(jbt);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbt) {
            if (!isOpen) {
                window.setVisible(true);
                isOpen = true;
                jbt.setText("Close my Window of Sports & Entertainment");
            } else {
                window.setVisible(false);
                isOpen = false;
                jbt.setText("Open my Window of Sports & Entertainment");
            }
        }
    }
}


class MenuWindow extends JFrame implements ActionListener {
    JTextField jtf = new JTextField();
    JMenuItem menuItemQuit = new JMenuItem("quit");

    MenuWindow() {
        this("", 0, 0);
    }

    MenuWindow(String title) {
        this(title, 0, 0);
    }

    MenuWindow(int width, int height) {
        this("", width, height);
    }

    public MenuWindow(String title, int width, int height) {
        JMenu menuSports = new JMenu("Sports");
        JMenu menuEntertain = new JMenu("Entertainment");
        JMenu menuBall = new JMenu("Ball");
        JMenuBar menuBar = new JMenuBar();

        addItem(menuSports, "Running", this);
        addItem(menuSports, "Jump Rope", this);
        addItem(menuBall, "Baseball", this);
        addItem(menuBall, "Basketball", this);
        addItem(menuBall, "Football", this);
        addItem(menuBall, "Soccer", this);
        addItem(menuBall, "Volleyball", this);
        menuSports.add(menuBall);
        menuItemQuit.addActionListener(new quit());
        menuSports.add(menuItemQuit);

        addItem(menuEntertain, "Singing", this);
        addItem(menuEntertain, "Dancing", this);
        addItem(menuEntertain, "Gaming", this);

        menuBar.add(menuSports);
        menuBar.add(menuEntertain);

        add(jtf, BorderLayout.NORTH);

        setJMenuBar(menuBar);
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(Ex6_5.frame);
    }

    private void addItem(JMenu menu, String menuName, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(menuName);

        menuItem.setActionCommand(menuName);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    public void actionPerformed(ActionEvent e) {
        jtf.setText(e.getActionCommand() + " is selected!");
    }
}


class quit implements ActionListener {
    public void actionPerformed(ActionEvent e) {
            System.exit(0);
    }
}


class LaunchFrame extends JFrame {
    LaunchFrame() {
        this("");
    }

    LaunchFrame(String title) {
        setTitle(title);
        setSize(350, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
