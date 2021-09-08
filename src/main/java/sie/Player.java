package sie;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for a Housie Player
 */
public class Player {

  private final Ticket ticket;
  private final int playerId;
  private boolean topLineWinner = false;
  private boolean earlyFiveWinner = false;
  private boolean fullHouseWinner = false;

  /**
   * Constructor that instantiates a player with a ticket
   *
   * @param ticket   ticket given to the player
   * @param playerId id assigned to the player
   */
  public Player(Ticket ticket, int playerId) {
    this.ticket = ticket;
    this.playerId = playerId;

  }

  /**
   * Checks to see if the ticket assigned to the player is a full house winner
   *
   * @return boolean whether or not the player has won the full house scenario
   */
  public boolean isFullHouseWinner() {
    fullHouseWinner = ticket.fullHouseWinner();
    return fullHouseWinner;
  }

  /**
   * Checks to see if the ticket assigned to the player is a top line winner
   *
   * @return boolean whether or not the player has won the top line scenario
   */
  public boolean isTopLineWinner() {
    topLineWinner = ticket.topLineWinner();
    return topLineWinner;
  }

  /**
   * Retrieves the total number of ticket hits so far in the game
   *
   * @return int representing the ticket hits for this ticket
   */
  public int totalTicketHits() {
    return ticket.getNumberOfHits();
  }

  /**
   * Checks to see if the number exists in the board. If so, it will be marked
   *
   * @param number int to check
   * @return boolean whether or not the number exist in the board
   */
  public boolean checkNumber(int number) {
    return ticket.selectNumber(number);
  }

  /**
   * Getter for the player id
   *
   * @return int the players id
   */
  public int getPlayerId() {
    return playerId;
  }

  /**
   * Sets the early five winner status for the player
   *
   * @param earlyFiveWinner boolean whether or not the player has won the early 5 winner status
   */
  public void setEarlyFiveWinner(boolean earlyFiveWinner) {
    this.earlyFiveWinner = earlyFiveWinner;
  }

  /**
   * Prints the summary for the player
   */
  public void printPlayerSummary() {
    List<String> results = new ArrayList<>();
    if (earlyFiveWinner) {
      results.add("Early Five");
    }
    if (topLineWinner) {
      results.add("Top Line");
    }
    if (fullHouseWinner) {
      results.add("Full House");
    }
    if (results.isEmpty()) {
      System.out.println("Player #" + playerId + ": Nothing");
    } else {
      StringBuilder str = new StringBuilder();
      str.append(results.remove(0));
      if (!results.isEmpty()) {
        str.append(" and ").append(results.remove(0));
      }
      if (!results.isEmpty()) {
        str.insert(0, results.remove(0) + ", ");
      }

      System.out.println("Player #" + playerId + ": " + str.toString());
    }
  }
}
