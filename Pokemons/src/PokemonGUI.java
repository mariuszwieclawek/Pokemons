import org.apache.log4j.Logger;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class PokemonGUI {

    static JFrame jf = new JFrame("PokeDex");
    static JPanel jp1 = new JPanel();
    static JTextField jtf = new JTextField(10);
    static JButton jb1 = new JButton("Search");
    static JButton jb2 = new JButton("List");

    static JPanel jp2 = new JPanel();

    static JLabel jl1 = new JLabel("...");
    static JLabel jl2 = new JLabel();
    static JLabel jl3 = new JLabel();
    static JLabel jl4 = new JLabel();
    static JLabel jl5 = new JLabel();
    static JLabel jl6 = new JLabel();
    static JLabel jl7 = new JLabel();

    static ImageIcon image = null;
    static JLabel picLabel = new JLabel();

    static Logger log = Logger.getLogger(PokemonGUI.class.getName());

    static Pokemon pok = new Pokemon();

    public static void createAndShowGUI() {

        ActionListener myActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) throws JSONException {
                String button = e.getActionCommand();
                switch (button) {
                    case "Search":
                        try {
                            PokemonParser.GetPokeInfo(pok, jtf.getText());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        if(PokemonParser.DataIsOk) {
                            image = new ImageIcon(Objects.requireNonNull(getClass().getResource("PokemonImages\\"+pok.getPokeName()+".png")));
                            Image img = image.getImage();
                            Image newimg = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
                            image = new ImageIcon(newimg);

                            picLabel.setIcon(image);
                            jl1.setText("ID: " + pok.getPokeID());
                            jl2.setText("Name: " + pok.getPokeName());
                            jl3.setText("Height: " + pok.getPokeHeight());
                            jl4.setText("Weight: " + pok.getPokeWeight());
                            jl5.setText("Type: " + pok.getPokeType());
                            jl6.setText("Ability: " + pok.getPokeAbilityName());
                            jl7.setText("Move: " + pok.getPokeMove());
                        }else {
                            jl1.setText("Cannot find this pokemon");
                            jl2.setText("");jl3.setText("");jl4.setText("");jl5.setText("");jl6.setText("");jl7.setText("");
                            image = new ImageIcon(Objects.requireNonNull(getClass().getResource("PokemonImages\\pokeball.jpg")));
                            Image img = image.getImage();
                            Image newimg = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
                            image = new ImageIcon(newimg);
                            picLabel.setIcon(image);
                        }
                        break;
                    case "List":
                        try {
                            new PokemonListWorker(jf,jtf,jp2,jl1,jl2,jl3,jl4,jl5,jl6,jl7,picLabel).execute();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        break;
                }
            }
        };

        /* FIRST PANEL */
        jb1.addActionListener(myActionListener);
        jb2.addActionListener(myActionListener);

        jp1.setLayout(null);
        jp1.setBounds(1,1,385,60);
        jp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Search pokemon:"));
        jtf.setBounds(15,20,100,25);
        jb1.setBounds(120,20,100,24);
        jb2.setBounds(260,20,100,24);
        jp1.add(jtf);jp1.add(jb1);jp1.add(jb2);

        jf.add(jp1);

        /* SECOND PANEL */
        jp2.setLayout(null);
        jp2.setBounds(1,71,385,180);
        jp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Information about pokemon you search:"));

        jl1.setBounds(15,20,398,25);
        jl2.setBounds(15,40,398,25);
        jl3.setBounds(15,60,398,25);
        jl4.setBounds(15,80,398,25);
        jl5.setBounds(15,100,398,25);
        jl6.setBounds(15,120,398,25);
        jl7.setBounds(15,140,398,25);
        picLabel.setBounds(85,280,200,200);

        image = new ImageIcon(Objects.requireNonNull(PokemonGUI.class.getResource("PokemonImages\\pokeball.jpg")));
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        picLabel.setIcon(image);

        jp2.add(jl1);jp2.add(jl2);jp2.add(jl3);jp2.add(jl4);jp2.add(jl5);jp2.add(jl6);jp2.add(jl7);
        jf.add(jp2);jf.add(picLabel);

        jf.setLayout(null);
        //jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(400,570));
        jf.setResizable(false);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                log.info("Stop application");
                System.exit(0);
            }
        });

    }
}

class PokemonListWorker extends SwingWorker {
    private final JFrame f = new JFrame("List of Pokemons");
    private final JDialog dialog = new JDialog(f, "Loading", true);
    private JTable jt = null;
    private final String[] column={"ID","NAME"};
    private final String[][] data= new String[898][2];

    static Logger log = Logger.getLogger(PokemonGUI.class.getName());

    static JFrame jf = null;
    static JTextField jtf = null;
    static JPanel jp2 = null;
    static JLabel jl1 = null;
    static JLabel jl2 = null;
    static JLabel jl3 = null;
    static JLabel jl4 = null;
    static JLabel jl5 = null;
    static JLabel jl6 = null;
    static JLabel jl7 = null;

    ImageIcon image = new ImageIcon(Objects.requireNonNull(getClass().getResource("PokemonImages\\pokeball.jpg")));
    static JLabel picLabel = null;

    static Pokemon pok = new Pokemon();

    public PokemonListWorker(JFrame jfr, JTextField jtxtf, JPanel jpan2, JLabel jlab1, JLabel jlab2, JLabel jlab3, JLabel jlab4,
                             JLabel jlab5, JLabel jlab6, JLabel jlab7, JLabel picLabel1) throws IOException {
        jf = jfr;
        jtf = jtxtf;
        jp2 = jpan2;
        jl1 = jlab1;jl2 = jlab2;jl3 = jlab3;jl4 = jlab4;jl5 = jlab5;jl6 = jlab6;jl7 = jlab7;picLabel=picLabel1;

        JProgressBar progressBar = new JProgressBar();
        progressBar.setString("Please wait");
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(true);
        dialog.getContentPane().add(progressBar);
        dialog.setSize(100, 100);
        dialog.setModal(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    protected Integer doInBackground() throws Exception {
        System.out.println( "PokemonListWorker doInBackground()" );
        log.info("PokemonListWorker doInBackground()");

        for (int i = 0; i < 898; i++) {
            image = new ImageIcon(Objects.requireNonNull(getClass().getResource("PokemonImages\\"+PokemonParser.GetPokeName(String.valueOf(i + 1))+".png")));
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = PokemonParser.GetPokeName(String.valueOf(i + 1));
        }

        jt = new JTable(data, column);

        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = jt.getSelectedRow();
                if(row>=0) {
                    try {
                        PokemonParser.GetPokeInfo(pok, String.valueOf(row+1));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    if(PokemonParser.DataIsOk) {
                        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("PokemonImages\\" + pok.getPokeName() + ".png")));
                        Image img = image.getImage();
                        Image newimg = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
                        image = new ImageIcon(newimg);

                        picLabel.setIcon(image);
                        jl1.setText("ID: " + pok.getPokeID());
                        jl2.setText("Name: " + pok.getPokeName());
                        jl3.setText("Height: " + pok.getPokeHeight());
                        jl4.setText("Weight: " + pok.getPokeWeight());
                        jl5.setText("Type: " + pok.getPokeType());
                        jl6.setText("Ability: " + pok.getPokeAbilityName());
                        jl7.setText("Move: " + pok.getPokeMove());

                    }else {
                        jl1.setText("Cannot find this pokemon");
                        jl2.setText("");jl3.setText("");jl4.setText("");jl5.setText("");jl6.setText("");jl7.setText("");
                        image = new ImageIcon(Objects.requireNonNull(getClass().getResource("PokemonImages\\pokeball.jpg")));
                        Image img = image.getImage();
                        Image newimg = img.getScaledInstance(200,200,Image.SCALE_SMOOTH);
                        image = new ImageIcon(newimg);
                        picLabel.setIcon(image);

                    }
                    jp2.add(jl1);jp2.add(jl2);jp2.add(jl3);jp2.add(jl4);jp2.add(jl5);jp2.add(jl6);jp2.add(jl7);
                    jf.add(jp2);jf.add(picLabel);
                }
            }
        });
        return 0;
    }
    @Override
    protected void done() {
        System.out.println("PokemonListWorker done");

        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(300,400);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        dialog.setVisible(false);
    }
}