/*
 * Name: Kendra Chavez
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-6
 *
 * Description: gui implementation of two-dice pig (v4)
 * 
 */

package cpsc224;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import javax.swing.border.*;

/**
 * GUI-based implementation of a simple two-dice pig game for CPSC
 * 224, HW-6.
 */
public class TwoDicePigFrame extends JFrame {

  private Game game;
  private Player p1;
  private Player p2;

  private JLabel p1ScoreLabel;
  private JLabel p2ScoreLabel;
  private JLabel turnScoreLabel;

  private JLabel d1Label;
  private JLabel d2Label;

  private JButton rollButton;
  private JButton holdButton;
  private JButton quitButton;

  private JPanel dicePanel;

  public TwoDicePigFrame(Player p1, Player p2) {
    super("Two-Dice Pig (v4)");
    this.p1 = p1;
    this.p2 = p2;
    this.game = new Game(p1, p2);

    setLayout(new BorderLayout());

    JPanel scorePanel = scorePanel();
    add(scorePanel, BorderLayout.NORTH);
    addTitle(scorePanel, "Score Board");

    dicePanel = dicePanel();
    add(dicePanel);
    

    JPanel buttonPanel = buttonPanel();
    add(buttonPanel, BorderLayout.EAST);
    addTitle(buttonPanel, "Menu");

    updateScoreboard();
    updateBorder(dicePanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(350, 250);
    setLocationRelativeTo(null);
    setVisible(true);

  }

  private JPanel scorePanel() {
    JPanel scorePanel = new JPanel();

    p1ScoreLabel = new JLabel("Player 1: 0");
    p1ScoreLabel.setHorizontalAlignment(JLabel.CENTER);
    p1ScoreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));

    p2ScoreLabel = new JLabel("Player 2: 0");
    p2ScoreLabel.setHorizontalAlignment(JLabel.CENTER);
    p2ScoreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));

    turnScoreLabel = new JLabel("Turn Score: 0");
    turnScoreLabel.setHorizontalAlignment(JLabel.CENTER);
    turnScoreLabel.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 13));

    scorePanel.add(p1ScoreLabel);
    scorePanel.add(Box.createHorizontalStrut(7));
    scorePanel.add(p2ScoreLabel);
    scorePanel.add(Box.createHorizontalStrut(30));
    scorePanel.add(turnScoreLabel);

    return scorePanel;
  }

  private ImageIcon diceImage(int value) {
    String fileName = "images/die_" + value + ".jpg";
    URL url = ClassLoader.getSystemClassLoader().getResource(fileName);
    return new ImageIcon(url);
  }

  private JPanel dicePanel() {
    JPanel dicePanel = new JPanel();

    JPanel dieImage = new JPanel();
    dieImage.setLayout(new FlowLayout(FlowLayout.CENTER));

    d1Label = new JLabel(diceImage(1)); // to set die_1 as default image before a player has rolled
    d2Label = new JLabel(diceImage(1));
    dieImage.add(d1Label);
    dieImage.add(d2Label);

    dicePanel.add(dieImage);

    return dicePanel;
  }

  private JPanel buttonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    rollButton = new JButton("Roll");
    rollButton.addActionListener(e -> {
      rollSelected();
    });
    rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    holdButton = new JButton("Hold");
    holdButton.addActionListener(e -> {
      holdSelected();
    });
    holdButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    quitButton = new JButton("Quit");
    quitButton.addActionListener(e -> {
      quitSelected();
    });
    quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    buttonPanel.add(Box.createRigidArea(new Dimension(20, 20)));
    buttonPanel.add(rollButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(20, 8)));
    buttonPanel.add(holdButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(20, 8)));
    buttonPanel.add(quitButton);

    return buttonPanel;
  }

  private void rollSelected() {
    game.rollDice();
    int d1 = game.getLastRoll()[0];
    int d2 = game.getLastRoll()[1];
    updateDice(d1, d2);
    updateScoreboard();

    if (game.rolledOne()) {
      rolledOneDialog(game.currentOpponent());
      updateBorder(dicePanel);
      
      if (game.currentPlayer().isCompPlayer()){ //to start a compPlayer turn after Player 1 rolls a 1
        computerTurn();
      }
      return;

    } else if (game.rolledSnakeEyes()) {
      snakeEyesDialog(game.currentOpponent());
      updateBorder(dicePanel);

      
      if (game.currentPlayer().isCompPlayer()){//to start a compPlayer turn after Player 1 rolls snake eyes
        computerTurn();
      }
      return;
    }else{
      updateBorder(dicePanel);
    }

    if (game.hasWinner()) {
      winnerDialog(game.currentPlayer());
      return;
    }

    if (game.currentPlayer().isCompPlayer()){
      computerTurn();
    }
  }

  private void holdSelected() {
    game.currentPlayer().bankTurnScore();
    updateScoreboard();

    if (game.hasWinner()) {
      winnerDialog(game.currentPlayer());
      return;
    }

    game.switchPlayers();
    updateBorder(dicePanel);

    if (game.currentPlayer().isCompPlayer()){
      computerTurn();
    }
  }

  private void quitSelected() {
    int choice = JOptionPane.showConfirmDialog(this, "Start a new game?", "Quit Game",
        JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.YES_OPTION) {
      p1.resetGameScore();
      p2.resetGameScore();
      this.dispose();
      new TwoDicePigFrame(p1, p2);
    }

     else if (choice == JOptionPane.NO_OPTION)
      System.exit(0);
  }

  private void winnerDialog(Player p) {
    int choice =JOptionPane.showConfirmDialog(this, p.name() + " wins! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION) {
      p1.resetGameScore();
      p2.resetGameScore();
      this.dispose();
      new TwoDicePigFrame(p1, p2);

    } 
    else if (choice == JOptionPane.NO_OPTION)
      System.exit(0);
 


  }

  public void snakeEyesDialog(Player p) {
    JOptionPane.showMessageDialog(this, p.name() + " rolled snake eyes!");
  }

  public void rolledOneDialog(Player p) {
    JOptionPane.showMessageDialog(this, p.name() + " rolled a one!");
  }

  private JPanel addTitle(JPanel panel, String title) {
    Border empty = BorderFactory.createEmptyBorder();
    Border line = BorderFactory.createLineBorder(Color.black);
    Border compound = BorderFactory.createCompoundBorder(line, empty);
    panel.setBorder(BorderFactory.createTitledBorder(compound, title));

    return panel;
  }

  public void updateDice(int d1, int d2) {
    d1Label.setIcon(diceImage(d1));
    d2Label.setIcon(diceImage(d2));
  }

  public void updateScoreboard() {
    p1ScoreLabel.setText("Player 1: " + p1.gameScore());
    p2ScoreLabel.setText("Player 2: " + p2.gameScore());
    turnScoreLabel.setText("Turn Score: " + game.currentPlayer().turnScore());
  }

  public void updateBorder(JPanel panel) {
    String title = game.currentPlayer().name() + "'s Turn";
    Border empty = BorderFactory.createEmptyBorder();
    Border line = BorderFactory.createLineBorder(Color.black);
    Border compound = BorderFactory.createCompoundBorder(line, empty);
    panel.setBorder(BorderFactory.createTitledBorder(compound, title));
  }

  private void computerTurn(){
    game.playComputerTurn();

    int d1 = game.getLastRoll()[0];
    int d2 = game.getLastRoll()[1];
    updateDice(d1, d2);
    updateScoreboard();

    Player compPlayer = game.currentOpponent();

    if (game.rolledOne()){
      JOptionPane.showMessageDialog(this, "The Computer rolled a 1! \nPlayer 1's turn!");
    } else if (game.rolledSnakeEyes()){
      JOptionPane.showMessageDialog(this, "The Computer rolled Snake Eyes! \nPlayer 1's turn!");
    } else if (compPlayer.isCompPlayer()){
      JOptionPane.showMessageDialog(this, "The Computer held! \nPlayer 1's turn!");    
    }

    if (game.hasWinner()){
      winnerDialog(game.currentPlayer());
    }
  updateBorder(dicePanel);
}


  public static void main(String[] args) {
    JWindow window = new JWindow();
    window.setPreferredSize(new Dimension(400, 300));

    SplashScreen splashScreenPanel = new SplashScreen();

    window.add(splashScreenPanel);
    window.pack();
    window.setLocationRelativeTo(null);
    window.setVisible(true);

    splashScreenPanel.getStartButton().addActionListener(e -> {
      Player p1 = new HumanPlayer("Player 1");
      Player p2;

      if (splashScreenPanel.isCompPlayerSelected()) {
        if (splashScreenPanel.getCompStratChoice().equals("Custom Strategy")) {
          p2 = new ComputerPlayer("Player 2", new CustomStrategy(100));
        } else {
          p2 = new ComputerPlayer("Player 2", new DefaultStrategy());
        }
      } else {
        p2 = new HumanPlayer("Player 2");
      }
      window.dispose();

      new TwoDicePigFrame(p1, p2);
    });
  }
}
