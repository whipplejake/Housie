package sie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HousieDriver {

  private static final int COLUMNS_DEFAULT = 10;
  private static final int COLUMNS_MINIMUM = 1;
  private static final String EMPTY_STRING = "";
  private static final String NUMBER_STRING = "N";
  private static final int NUMBERS_PER_ROW_DEFAULT = 5;
  private static final int NUMBERS_PER_ROW_MINIMUM = 1;
  private static final int PLAYERS_MINIMUM = 2;
  private static final int RANGE_DEFAULT = 90;
  private static final int RANGE_MINIMUM = 1;
  private static final int ROWS_DEFAULT = 3;
  private static final int ROWS_MINIMUM = 1;
  private static final String QUIT_STRING = "Q";

  public static void main(String[] args) throws IOException {

    System.out.println(" **** Lets Play Housie ***** ");
    System.out.println();
    // Quit character can be uppercase or lowercase
    System.out.println(" Note: - Press 'Q' to quit any time. ");

    // command line reader
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // get number range
    int range = generateRange(reader);
    // get number of players
    int players = generatePlayers(reader);
    // get number of rows
    int rows = generateRows(reader);
    // get number of columns
    int columns = generateColumns(reader);
    // get numbers per row
    int numbersPerRow = generateNumbersPerRow(reader, columns);

    while ((numbersPerRow * rows) > range) {
      System.out.println("\nPlease enter a valid range such that each board can be filled with");
      System.out.println("unique numbers. n needs to be greater than " + (numbersPerRow * rows) + ".");

      range = generateRange(reader);
    }

    Housie housie = new Housie(range, players, rows, columns, numbersPerRow);

    System.out.println(">> Press '" + NUMBER_STRING + "' to generate next number.");
    String input;
    while (!(input = reader.readLine()).equalsIgnoreCase(QUIT_STRING)) {
      if (input.equalsIgnoreCase(NUMBER_STRING)) {
        if (housie.callNumber()) {
          System.out.println("***** Game Over *****");
          housie.printSummary();
          break;
        }
      } else {
        System.out.println(">> Please press '" + NUMBER_STRING + "' to generate a new number.");
      }
    }

  }

  /**
   * Private method that prompts user for a range of numbers
   * <p>
   * Assumption: default case is when nothing is entered
   *
   * @param reader input buffer reader
   * @return top end of range
   * @throws IOException
   */
  private static int generateRange(BufferedReader reader) throws IOException {
    // get number range
    int range = -1;
    while (range < RANGE_MINIMUM) {
      // ask user for input string
      System.out.print("\n>>Enter the positive integer range(1-n): ");
      String rangeString = reader.readLine();

      // handle default case
      if (rangeString.equalsIgnoreCase(EMPTY_STRING)) {
        return RANGE_DEFAULT;
      }

      // check input for quit character
      checkForQuitStatusAndExit(rangeString);

      // convert buffer reader to integer, check to make sure that the input is greater than 0
      // if those condidtions are not met, ask the user again
      try {
        range = Integer.parseInt(rangeString);
        if (range < RANGE_MINIMUM) {
          System.out.print("Please enter an integer greater than 0");
        }
      } catch (NumberFormatException e) {
        System.out.print("ERROR: Please enter a valid integer");
      }
    }
    return range;
  }

  /**
   * Private method that prompts the user for the number of players
   *
   * @param reader input BufferReader
   * @return number of players
   * @throws IOException
   */

  private static int generatePlayers(BufferedReader reader) throws IOException {
    int players = 0;
    while (players < PLAYERS_MINIMUM) {
      // ask user for input string
      System.out.print("\n>>Enter Number of players playing the game: ");
      String playersString = reader.readLine();

      // check input for quit character
      checkForQuitStatusAndExit(playersString);

      // convert buffer reader to integer, check to make sure that the input is greater than 1
      // if those conditions are not met, ask the user again
      try {
        players = Integer.parseInt(playersString);
        if (players < PLAYERS_MINIMUM) {
          System.out.print("Please enter an integer greater than 1");
        }
      } catch (NumberFormatException e) {
        System.out.print("ERROR: Please enter a valid integer");
      }
    }
    return players;
  }

  /**
   * Primate method to prompt the user for the number of rows
   * <p>
   * Assumption: default case is wanted when nothing is entered
   *
   * @param reader input BufferReader
   * @return number of rows, default is 3
   * @throws IOException
   */
  private static int generateRows(BufferedReader reader) throws IOException {
    int rows = 0;
    while (rows < ROWS_MINIMUM) {
      System.out.print("\n>>Enter the number of rows for the ticket: ");
      String rowString = reader.readLine();

      // handle default case
      if (rowString.equalsIgnoreCase(EMPTY_STRING)) {
        return ROWS_DEFAULT;
      }

      // check input for quit character
      checkForQuitStatusAndExit(rowString);

      // convert buffer reader to integer, check to make sure that the input is greater than 0
      // if those conditions are not met, ask the user again
      try {
        rows = Integer.parseInt(rowString);
        if (rows < ROWS_MINIMUM) {
          System.out.print("Please enter an integer greater than 0");
        }
      } catch (NumberFormatException e) {
        System.out.print("ERROR: Please enter a valid integer");
      }
    }
    return rows;
  }

  /**
   * Private method to return the number of columns
   * <p>
   * Assumption: default case is when nothing is entered
   *
   * @param reader input BufferReader
   * @return number of rows, default is 10
   * @throws IOException
   */
  private static int generateColumns(BufferedReader reader) throws IOException {
    int columns = 0;
    while (columns < COLUMNS_MINIMUM) {
      System.out.print("\n>>Enter the number of columns for the ticket: ");
      String columnString = reader.readLine();

      // handle default case
      if (columnString.equalsIgnoreCase(EMPTY_STRING)) {
        return COLUMNS_DEFAULT;
      }

      // check input for quit character
      checkForQuitStatusAndExit(columnString);

      // convert buffer reader to integer, check to make sure that the input is greater than 0
      // if those condidtions are not met, ask the user again
      try {
        columns = Integer.parseInt(columnString);
        if (columns < COLUMNS_MINIMUM) {
          System.out.println("Please enter an integer greater than 0");
        }
      } catch (NumberFormatException e) {
        System.out.println("ERROR: Please enter a valid integer");
      }
    }
    return columns;
  }

  /**
   * Private method to prompt the user for the amount of numbers per row
   * <p>
   * Assumption: numbers per row cannot be more than the number of columns
   * Assumption: default case is when nothing is entered
   *
   * @param reader  input BufferReader
   * @param columns number of columns for each ticket
   * @return
   * @throws IOException
   */
  private static int generateNumbersPerRow(BufferedReader reader, int columns) throws IOException {
    int numbersPerRow = 0;
    while (numbersPerRow < NUMBERS_PER_ROW_MINIMUM || (numbersPerRow * columns) < 0 || (numbersPerRow > columns)) {
      System.out.print("\n>>Enter the number of numbers per row for the ticket: ");
      String numbersString = reader.readLine();

      // handle default case
      if (numbersString.equalsIgnoreCase(EMPTY_STRING)) {
        return NUMBERS_PER_ROW_DEFAULT;
      }

      // check input for quit character
      checkForQuitStatusAndExit(numbersString);

      // convert buffer reader to integer, check to make sure that the input is greater than 0
      // if those conditions are not met, ask the user again
      try {
        numbersPerRow = Integer.parseInt(numbersString);
        if ((numbersPerRow < NUMBERS_PER_ROW_MINIMUM) || (numbersPerRow > columns)) {
          System.out.println(
              "Please enter an integer greater than 0 and less than or equal to the number of columns (" + columns
                  + ")");
        }
        // preventing an overflow situation
        else if ((numbersPerRow * columns) < 0) {
          System.out
              .println("Please enter an integer greater than 0 and less than " + (Integer.MAX_VALUE / columns) + ".");
        }
      } catch (NumberFormatException e) {
        System.out.println("ERROR: Please enter a valid integer");
      }
    }
    return numbersPerRow;
  }

  /**
   * Private helper method that checks to see if the user has entered the quit string
   * and exits
   *
   * @param str input string from BufferReader
   */
  private static void checkForQuitStatusAndExit(String str) {
    if (str.equalsIgnoreCase(QUIT_STRING)) {
      System.out.println("Thank you for playing -- Quitting....");
      System.exit(0);
    }
  }
}
