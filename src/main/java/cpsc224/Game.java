/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

package cpsc224;

/**
 * a basic class for representing the game
 */
public class Game {
    private Player player1;
    private Player player2;
    private int currPlayer;
    private boolean rolledOne;
    private boolean rolledSnakeEyes;
    private int[] lastRoll;
    public String currentPlayer;


    /**
     * constuctor for game class
     * sets currPlayer to 1 (defaul player)
     * 
     * @param player1 the player1
     * @param player2 the player2
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currPlayer = 1;
    }

    /**
     * method for returning player1's name
     * 
     * @return player 1's name
     */
    public String player1() {
        return this.player1.name();
    }

    /**
     * method for returning player2's name
     * 
     * @return player 2's name
     */
    public String player2() {
        return this.player2.name();
    }

    /**
     * method for returning current player
     * 
     * @return currentPlayer
     */
    public Player currentPlayer() {
        if (currPlayer == 1) {
            return player1;
        } else
            return player2;
    }

    /**
     * method for returning current opponent
     * 
     * @return currentOpponent
     */
    public Player currentOpponent() {
        if (currPlayer == 1) {
            return player2;
        } else
            return player1;
    }

    /**
     * method for switching players
     */
    public void switchPlayers() {
        if (currPlayer == 1) {
            currPlayer = 2;
        } else
            currPlayer = 1;
    }

    public void rollDice(){
        lastRoll = Dice.rollDice();
        rolledOne = false;
        rolledSnakeEyes = false;

        Player current = currentPlayer();

        if (lastRoll[0] == 1 && lastRoll[1] == 1) {
            rolledSnakeEyes = true;
            current.resetTurnScore();
            current.resetGameScore();
            switchPlayers();
        } else if (lastRoll[0] == 1 || lastRoll[1] == 1) {
            rolledOne = true;
            current.resetTurnScore();
            switchPlayers();
        } else {
            current.addTurnPoints(lastRoll[0] + lastRoll[1]);
        }
    }

    public int[] getLastRoll() {
        return lastRoll;
    }

    /**
     * method for determining if the game has a winner
     * 
     * @return true if player1 or player2 is winner or false otherwise
     */
    public boolean hasWinner() {
        if (player1.isWinner() || player2.isWinner()) {
            return true;
        }else
            return false;
    }

    /**
     * @return rolledOne if player rolls a one
     */
    public boolean rolledOne(){
        return rolledOne;
    }

    /**
     * @return rolledSnakeEyes if player rolls two ones
     */
    public boolean rolledSnakeEyes(){
        return rolledSnakeEyes;
    }

    /**
     * to add turn score to total score and switch players if hold is selected
     */
    public void hold(){
        currentPlayer().bankTurnScore();
        switchPlayers();
    }

    public void playComputerTurn() { 
        Player current = currentPlayer();
        if (!current.isCompPlayer()) {
            return;
        }

        ComputerPlayer comp = (ComputerPlayer) current;
        Player opponent = currentOpponent();


        while (current.isCompPlayer() && current == currentPlayer()) {
            if (comp.getStrategy().hold(comp, opponent, comp.turnScore())) {
                hold();
                break;
            }
            rollDice();
            if (rolledOne || rolledSnakeEyes || hasWinner()) {
                break;
                }

            if (comp.getStrategy().hold(comp, opponent, comp.turnScore())) {
                hold();
                break;
            } 
        }
    }
}


