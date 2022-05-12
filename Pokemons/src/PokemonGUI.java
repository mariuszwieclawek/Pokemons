import javax.swing.*;
import java.awt.*;

public class PokemonGUI {

    static JFrame jframe = null;
    static JTextField jtfield = null;

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("PokeDex");
        jframe = jf;
        jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(400,400));
        jf.setResizable(false);

        JTextField jtf = new JTextField("0");
        jtfield = jtf;
        jtf.setHorizontalAlignment(SwingConstants.RIGHT);
        jf.getContentPane().add(jtf,BorderLayout.NORTH);

        JPanel jp = new JPanel();
        jf.getContentPane().add(jp,BorderLayout.CENTER);

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
