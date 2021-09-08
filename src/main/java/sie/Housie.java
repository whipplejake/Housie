package sie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Class for the Housie game
 */
public class Housie {

  public static final int EARLY_FIVE = 5;

  private final List<Player> players;
  private final Random random;
  private final Set<Integer> calledNumbers = new HashSet<>();
  private final int range;
  private final int numbersOnBoard;
  private int totalNumbersCalled = 0;
  private boolean topLineWinner = false;
  private boolean earlyFiveWinner = false;
  private boolean fullHouseWinner = false;

  /**
   * Parameterized constructor
   *
   * @param range         top end of available numbers for the game
   * @param numOfPlayers  number of players in the game
   * @param rows          number of rows on each ticket
   * @param columns       number of columns on each ticket
   * @param numbersPerRow numbers contained in each row
   */
  public Housie(int range, int numOfPlayers, int rows, int columns, int numbersPerRow) {
    this.players = new ArrayList<>();
    this.random = new Random();
    this.range = range;
    this.numbersOnBoard = rows * numbersPerRow;

    System.out.println("   *** Generating Players and Tickets ***");
    // create the amount of requested players and their ticket
    for (int i = 0; i < numOfPlayers; i++) {
      Ticket ticket = new Ticket(rows, columns, numbersPerRow, range);
      // adding a player, player id id increased by 1 since i is 0 indexed
      this.players.add(new Player(ticket, i + 1));
    }
    System.out.println("   *** Players and Tickets Generated ***");
  }

  /**
   * Public method to call a random number. After the number is called, each player is checked
   * for a hit. If the number is found on the players ticket it is marked and then the ticket
   * is checked for a winning scenario.
   *
   * @return if the game is over
   */
  public boolean callNumber() {
    int nextNumber = getNextNumber();
    System.out.println("Next number is: " + nextNumber);
    for (Player player : players) {
      // if the number is a hit for the player, check to see if they won
      if (player.checkNumber(nextNumber)) {
        // check to see if at least five numbers have been called in order to be the early five winner
        if (!earlyFiveWinner && totalNumbersCalled >= EARLY_FIVE && player.totalTicketHits() == EARLY_FIVE) {
          player.setEarlyFiveWinner(true);
          announceWinner(player, false, true, false);
        }
        // check to see if there hasnt already been a top line winner. If there hasnt been check the current player
        if (!topLineWinner && player.isTopLineWinner()) {
          announceWinner(player, true, false, false);
        }
        // check to see if there hasnt been a full house winner. If there hasnt, check the current player
        if (!fullHouseWinner && player.isFullHouseWinner()) {
          announceWinner(player, false, false, true);
        }
      }
    }
    // check to see if the game is over
    return isGameOver();
  }

  /**
   * Helper method that prints the summary after the game is over
   */
  public void printSummary() {
    System.out.println("====================");
    System.out.println("     Summary:");
    for (Player player : players) {
      player.printPlayerSummary();
    }
    System.out.println("====================");

  }

  /**
   * Check to see if the game is over. First checks to see if there is an early five winner OR the total
   * numbers on each board is less than 5. Then it checks to see if there is a top line winner and a full
   * house winner. If all three conditions evaluate to true, return that the game is over and exit
   *
   * @return
   */
  private boolean isGameOver() {
    return (earlyFiveWinner || numbersOnBoard < EARLY_FIVE) && topLineWinner && fullHouseWinner;
  }

  /**
   * Private helper function that randomly generates a random number in the range provided. 1 is added
   * since the nextInt function is inclusive of 0 but exclusive of the top of the range. If the number
   * has already been called, a new random number is generated until it is unique.
   *
   * @return next number to be called
   */
  private int getNextNumber() {
    int nextNumber = random.nextInt(range) + 1;
    while (calledNumbers.contains(nextNumber)) {
      nextNumber = random.nextInt(range) + 1;
    }
    calledNumbers.add(nextNumber);
    totalNumbersCalled++;
    return nextNumber;
  }

  /**
   * Private helper function that announces the winner of a scenario and sets the boolean to true
   * so that scenario is not considered anymore
   *
   * @param player          Player that has won a scenario
   * @param topLineWinner   true if winning 'Top Line'
   * @param earlyFiveWinner true if winning 'Early Five'
   * @param fullHouseWinner true if winning 'Full House'
   */
  private void announceWinner(Player player, boolean topLineWinner, boolean earlyFiveWinner, boolean fullHouseWinner) {
    this.topLineWinner |= topLineWinner;
    this.earlyFiveWinner |= earlyFiveWinner;
    this.fullHouseWinner |= fullHouseWinner;

    System.out.print("\nWe have a winner! ");

    if (topLineWinner) {
      System.out.println("Player " + player.getPlayerId() + " has the 'Top Line' winning combination\n");
    } else if (earlyFiveWinner) {
      System.out.println("Player " + player.getPlayerId() + " has the 'First Five' winning combination\n");
    } else {
      System.out.println("Player " + player.getPlayerId() + " has the 'Full House' winning combination\n");
    }
  }

}
