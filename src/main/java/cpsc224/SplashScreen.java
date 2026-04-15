/*
 * Name: Kendra Chavez
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-6
 *
 * Description: two-dice pig (v4) splash screen
 * 
 */
package cpsc224;

import java.awt.*;
import javax.swing.*;
import java.net.URL;

public class SplashScreen extends JPanel {
    
    private JRadioButton humanPlayerRadio;
    private JRadioButton compPlayerRadio;
    private ButtonGroup opponentGroup;

    private JRadioButton defaultStratRadio;
    private JRadioButton customStratRadio;
    private ButtonGroup stratGroup;
    private JPanel stratPanel;

    private JButton startButton;
    private JButton quitButton;

    public SplashScreen(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to Two-Dice Pig!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
        welcomeLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(welcomeLabel);

        add(Box.createRigidArea(new Dimension(0, 10)));

        String fileName = "images/pig.png";
        URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
        ImageIcon image = new ImageIcon(url);
        JLabel label = new JLabel(image);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.add(Box.createRigidArea(new Dimension(0, 10)));
        add(label);

        add(Box.createRigidArea(new Dimension(0, 10)));

        humanPlayerRadio = new JRadioButton("vs Human", true);
        compPlayerRadio = new JRadioButton("vs Computer");

        opponentGroup = new ButtonGroup();
        opponentGroup.add(humanPlayerRadio);
        opponentGroup.add(compPlayerRadio);

        JPanel opponentPanel = new JPanel();
        opponentPanel.add(humanPlayerRadio);
        opponentPanel.add(compPlayerRadio);
        opponentPanel.setAlignmentX(CENTER_ALIGNMENT);
        add(opponentPanel);


        defaultStratRadio = new JRadioButton("Default Strategy", true);
        customStratRadio = new JRadioButton("Custom Strategy");

        stratGroup = new ButtonGroup();
        stratGroup.add(defaultStratRadio);
        stratGroup.add(customStratRadio);

        stratPanel = new JPanel();
        stratPanel.add(defaultStratRadio);
        stratPanel.add(customStratRadio);
        stratPanel.setAlignmentX(CENTER_ALIGNMENT);
        stratPanel.setVisible(false); //set to false bc it's only visible if compPlayer is selected
        add(stratPanel);

        //to toggle radio buttons for compStrat on/off 
        compPlayerRadio.addActionListener(e -> {stratPanel.setVisible(true);});
        humanPlayerRadio.addActionListener(e -> {stratPanel.setVisible(false);});
        

        startButton = new JButton("Play");
        startButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));

        quitButton = new JButton("Quit");
        quitButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 20));
        quitButton.addActionListener(e -> {System.exit(0);});

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(quitButton);
        buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
        add(buttonPanel);

        add(Box.createVerticalGlue());
        
    }





    public JButton getStartButton() {return startButton;}

    public boolean isCompPlayerSelected() {return compPlayerRadio.isSelected();}

    public String getCompStratChoice() {
        if (defaultStratRadio.isSelected()){
            return "Default Strategy";
        } 
        return "Custom Strategy";
    }

}
