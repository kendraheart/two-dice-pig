/*
 * NAME: Kendra Chavez
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-6
 */

package cpsc224;

import java.util.Random;

/**
 * simple class for handling rolls of the dice
 */
public class Dice {

  private static Random random = new Random();

  /**
   * returns two random ints between 1 and 6 as 1 dice roll
   * 
   * @return both dice values from 1 to 6
   */
  public static int[] rollDice() {
    int d1 = random.nextInt(6)+1;
    int d2 = random.nextInt(6)+1;
    return new int[] {d1, d2};
}


}
