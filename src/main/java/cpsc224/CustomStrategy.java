/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

 package cpsc224;

/**
 * class to implement a custom strategy
 *
 */
public class CustomStrategy implements ComputerStrategy {

    /**
     * creates a custom strategy based on winning score
     * 
     * @param winningScore the winning score
     */

    public CustomStrategy(int winningScore) {
        winningScore = 100;
    }

    /**
     * custom hold at strategy that forces the computer to hold
     * if they are 15 or less points away from winning
     * 
     * @param Player the computer player taking the turn
     * @param opponent the opposing player
     * @param turnTotal the turn total
     * @return true if the turn score is greater than the hold value
     */
    @Override
    public boolean hold(Player Player, Player opponent, int turnTotal) {
        int holdAtValue = 23;

        int pointsToWin = 100 - Player.gameScore();
        if (pointsToWin <= 15){
            holdAtValue = pointsToWin;
        }

        if (Player.turnScore() >= holdAtValue) {
            return true;
        }else
            return false;
    }
}
