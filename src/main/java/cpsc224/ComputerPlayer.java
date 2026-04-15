/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

 package cpsc224;

/**
 * A basic class for representing computer players in the game.
 */
public class ComputerPlayer extends Player {

    private ComputerStrategy strategy;

    /**
     * Creates a computer player with a given name and the chosen computer strategy
     * 
     * @param name the name of the player (e.g., "Player 1")
     * @param strategy the computers strategy
     */
    public ComputerPlayer(String name, ComputerStrategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    /**
     * method for using the default hold for holdAt23 strat
     * 
     * @param opponent the oppenent
     * @param turnTotal the total of the current turn
     * @return strategy.hold(this, opponent, turnTotal);
     */

    public boolean hold(Player opponent, int turnTotal) {
        return strategy.hold(this, opponent, turnTotal);

    }

    /**
     * method for returing the strategy to have access to customStrategys methods
     * 
     * @return strategy
     */
    public ComputerStrategy getStrategy() {
        return strategy;
    }

     public boolean isCompPlayer(){
        return true;
  }

}
