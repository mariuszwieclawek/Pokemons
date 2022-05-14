import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PokemonGUI {

    static JFrame jf = new JFrame("PokeDex");
    static JPanel jp1 = new JPanel();
    static JTextField jtf = new JTextField(10);
    static JButton jb1 = new JButton("Search");

    static JPanel jp2 = new JPanel();
    static JLabel jl1 = new JLabel("...");
    static JLabel jl2 = new JLabel();
    static JLabel jl3 = new JLabel();
    static JLabel jl4 = new JLabel();
    static JLabel jl5 = new JLabel();
    static Pokemons pok = new Pokemons();
    static PokemonsParser pokpars = new PokemonsParser();


    public static void createAndShowGUI() {

        ActionListener myActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) throws JSONException {
                String button = e.getActionCommand();
                switch (button) {
                    case "Search":
                        String text = jtf.getText();
                        try {
                            pokpars.GetPokeInfo(pok, text);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        jl1.setText("ID: " + pok.getPokeID());
                        jl2.setText("Name: " + pok.getPokeName());
                        jl3.setText("Type: " + pok.getPokeType());
                        jl4.setText("Ability: " + pok.getPokeAbilityName());
                        jl5.setText("Ability description: " + pok.getPokeAbilityDesricption());
                        pok.PrintAll();
                        break;
                }
            }
        };

        /*** FIRST PANEL ***/
        jb1.addActionListener(myActionListener);

        jp1.setLayout(null);
        jp1.setBounds(1,1,385,60);
        jp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Search pokemon:"));
        jtf.setBounds(15,20,100,25);
        jb1.setBounds(120,20,100,25);
        jp1.add(jtf);jp1.add(jb1);

        jf.add(jp1);

        /*** SECOND PANEL ***/
        jp2.setLayout(null);
        jp2.setBounds(1,71,385,290);
        jp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Information about pokemon you search:"));

        jl1.setBounds(15,20,398,25);
        jl2.setBounds(15,40,398,25);
        jl3.setBounds(15,60,398,25);
        jl4.setBounds(15,80,398,25);
        jl5.setBounds(15,100,398,25);
        jp2.add(jl1);jp2.add(jl2);jp2.add(jl3);jp2.add(jl4);jp2.add(jl5);

        jf.add(jp2);

        jf.setLayout(null);
        jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(400,400));
        jf.setResizable(false);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
