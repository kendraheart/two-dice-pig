/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

package cpsc224;

/**
 * A basic interface for computer hold strategies.
 */
public interface ComputerStrategy {

  /**
   * Determines whether the computer player should hold or not.
   * 
   * @param player the current player (taking the turn)
   * @param opponent the current player's opponent
   * @param turnTotal the total rolled so far in the turn
   * @return true if the computer should hold or false if the computer should roll
   */
  public boolean hold(Player player, Player opponent, int turnTotal);

}
