/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

package cpsc224;

/**
 * a pig player class for tracking information about the player
 * including their current score in a game and basic play statistics
 */
public class Player {

  private String name;
  private int gameScore; //player's total score
  private int turnScore; //player's score during a specific turn 
  private int winningScore = 100;

  /**
   * creates a player
   * 
   * @param name the player's name
   */
  public Player(String name) {
    this.name = name;
    this.gameScore = 0;
    this.turnScore = 0;
  }


  /**
   * the name of the player
   * 
   * @return the player's name
   */
  public String name() {
    return name;
  }


  /**
   * the current game score
   * 
   * @return the player's current score in the game
   */
  public int gameScore() {
    return gameScore;
  }

  /**
   * the current turn score
   * 
   * @return the player's score during a specific turn
   */
  public int turnScore() {
    return turnScore;
  }

  /**
   * sets the current game score to zero (if player rolls two 1's)
   */
  public void resetGameScore() {
    this.gameScore = 0;
  }

 /**
  * sets the current turn score to zero (if player rolls one 1)
  */
  public void resetTurnScore() {
    this.turnScore = 0;
  }

  /**
   * checks if the player has won the game 
   * (if game score is equeal to or greater than winning score)
   * 
   * @return true if the player has won and false otherwise
   */
  public boolean isWinner() {
    if (this.gameScore >= this.winningScore) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * updates the turnScore after the player has rolled the dice
   * 
   * @param rollPoints the points that were rolled
   */
  public void addTurnPoints(int rollPoints) {
    this.turnScore += rollPoints;
}


  /**
   * updates the player's score after their turn is complete
   * resets the turnScore to zero after turn is complete
   * 
   */
  public void bankTurnScore() {
    this.gameScore += turnScore;
    resetTurnScore();
  }

  public boolean isCompPlayer(){
    return false;
  }
}
