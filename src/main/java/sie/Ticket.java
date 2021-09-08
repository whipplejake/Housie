package sie;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Class that holds the information needed for a ticket in the game Housie
 */
public class Ticket {

  private final int rows;
  private final int columns;
  private final int numbersPerRow;
  // Comment this out for performance
  private final int[][] ticket;
  // end comment
  private final Set<Integer> topLine;
  private final Set<Integer> fullHouse;
  private final Random random;
  private final int range;

  // With how the rules currently sits, there is actually no reason to fully build the ticket.
  // The only things that need to be kept track of are the top row and all of the numbers in
  // the ticket. I am filling the ticket to stay in tune with the nature of the game and to be
  // able to print the board during testing

  /**
   * Ticket for the game Housie
   *
   * @param rows on the ticket
   * @param columns on the ticket
   * @param numbersPerRow numbers per row, must be less than the column size
   * @param range of numbers allowed on board, must be larger that columns x numbers per row
   */
  public Ticket(int rows, int columns, int numbersPerRow, int range) {
    this.rows = rows;
    this.columns = columns;
    // Comment this out for performance
    this.ticket = new int[rows][columns];
    // end comment
    this.numbersPerRow = numbersPerRow;
    this.random = new Random();
    this.topLine = new HashSet<>();
    this.fullHouse = new HashSet<>();
    this.range = range;

    fillTicket();
  }

  /**
   * Returns the number of hits to the ticket by calculating the original count of numbers then subtracting
   * the current number of untouched numbers.
   *
   * @return int number of hits
   */
  public int getNumberOfHits() {
    return (rows * numbersPerRow) - fullHouse.size();
  }

  /**
   * Returns boolean value true if all of the top row numbers have been hit (removed) in the top line set
   *
   * @return boolean if ticket is a top line winner
   */
  public boolean topLineWinner() {
    return topLine.size() == 0;
  }

  /**
   * Returns boolean value true if all of the numbers have been hit (removed) in the full house set
   *
   * @return boolean if ticket is a full house winner
   */
  public boolean fullHouseWinner() {
    return fullHouse.size() == 0;
  }

  /**
   * @param nextInt
   * @return
   */
  // returns true if the number was in the grid in the top line and full house or just the full house
  public boolean selectNumber(int nextInt) {
    return (topLine.remove(nextInt) && fullHouse.remove(nextInt)) || fullHouse.remove(nextInt);
  }

  /**
   * Private helper function that fills each ticket
   */
  private void fillTicket() {
    // go through all the rows in the ticket
    for (int i = 0; i < rows; i++) {

      // Comment this out for performance
      int[] row = ticket[i];
      // end comment

      for (int j = 0; j < numbersPerRow; j++) {
        // get next random value
        int nextInt = random.nextInt(range) + 1;
        // ensure the value has not already been added
        while (fullHouse.contains(nextInt)) {
          nextInt = random.nextInt(range) + 1;
        }
        // add to set containing all numbers on ticket
        fullHouse.add(nextInt);
        // if it is the first row, add to the topline set
        if (i == 0) {
          topLine.add(nextInt);
        }
        // use a random int to generate the index and check if there is a value already at that index

        // Comment this out for performance
        int index = getIndex(random.nextInt(Integer.MAX_VALUE) + 1, columns);
        while (row[index] > 0) {
          // use another random int to generate index
          index = getIndex(random.nextInt(Integer.MAX_VALUE) + 1, columns);
        }
        row[index] = nextInt;
        // end comment
      }
    }
    //    printTicket();
  }

  /**
   * Helper function to retrieve next random int in the range of 1 - number of columns
   *
   * @param randomInt randomly generated int
   * @param columns number of columns which is top bound of the range
   * @return index in the row for the next number to be placed
   */
  private int getIndex(int randomInt, int columns) {
    return randomInt % columns;
  }

  /**
   *
   */
  private void printTicket() {
    System.out.println("\n--- Printing Ticket");
    for (int i = 0; i < rows; i++) {
      System.out.print("[ ");
      for (int j = 0; j < columns; j++) {
        // Comment this out for performance
        System.out.print("-" + ticket[i][j] + "-");
        // end comment
      }
      System.out.println(" ]");
    }
  }
}
