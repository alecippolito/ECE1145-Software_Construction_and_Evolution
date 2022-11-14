package hotciv.alternative;
import hotciv.framework.*;
import hotciv.standard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.isNull;

public class HotCivUI extends JFrame {

    public Game game;
    private JPanel mainPanel;
    private JTable table1;

    public HotCivUI(){

        this.setTitle("HotCiv");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(1000,700);

        mainPanel = new JPanel();

        JComboBox comboBox1 = new JComboBox();
        comboBox1.addItem("AlphaCiv");
        comboBox1.addItem("BetaCiv");
        comboBox1.addItem("GammaCiv");
        comboBox1.addItem("EpsilonCiv");
        comboBox1.addItem("DeltaCiv");
        comboBox1.addItem("SemiCiv");
        comboBox1.addItem("ThetaCiv");
        comboBox1.addItem("ZetaCiv");

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //configure the game, based on the combo list
                GameFactory factory = null;
                switch (comboBox1.getSelectedItem().toString()) {
                    case "AlphaCiv":
                        factory = new AlphaFactory();
                        break;
                    case "BetaCiv":
                        factory = new BetaFactory();
                        break;
                    case "GammaCiv":
                        factory = new GammaFactory();
                        break;
                    case "DeltaCiv":
                        factory = new DeltaFactory();
                        break;
                    case "EpsilonCiv":
                        factory = new EpsilonFactory();
                        break;
                    case "SemiCiv":
                        factory = new SemiFactory();
                        break;
                    case "ThetaCiv":
                        factory = new ThetaFactory();
                        break;
                    case "ZetaCiv":
                        factory = new ZetaFactory();
                        break;
                }

                //test for errors
                if (isNull(factory)){
                    throw new RuntimeException("The factory variable is null, cannot start game");
                }

                //with the correct factory, initialize the game class
                runGame(factory);
            }
        });



        mainPanel.add(comboBox1);
        mainPanel.add(startButton);

        this.setContentPane(mainPanel);
    }

    public void runGame(GameFactory factory){
        game = new GameImpl(factory);
        System.out.print("YEEEAHHH");


        //here is where we initialize the buttons and all that
    }

    public static void main(String[] args){
        HotCivUI UI = new HotCivUI();
        UI.setVisible(true);
    }
}
