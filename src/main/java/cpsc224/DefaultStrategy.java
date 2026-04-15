/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

package cpsc224;

/**
 * A class representing the "Hold at 23" strategy.
 */
public class DefaultStrategy implements ComputerStrategy {

  /**
   * determines whether the computer player should hold or not
   * 
   * @param player the current player (taking the turn)
   * @param opponent the current player's opponent
   * @param turnTotal the total rolled so far in the turn
   * @return true if the player holds or false to continue rolling
   */
  @Override
  public boolean hold(Player player, Player opponent, int turnTotal) {
    if (turnTotal >= 23)
      return true;
    return false;
  }

}
