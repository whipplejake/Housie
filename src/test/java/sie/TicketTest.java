package sie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TicketTest {

  private static final int COLUMNS_DEFAULT = 10;
  private static final int COLUMNS_MINIMUM = 1;
  private static final int NUMBERS_PER_ROW_DEFAULT = 5;
  private static final int NUMBERS_PER_ROW_MINIMUM = 1;
  private static final int RANGE_DEFAULT = 90;
  private static final int RANGE_MINIMUM = 1;
  private static final int ROWS_DEFAULT = 3;
  private static final int ROWS_MINIMUM = 1;

  private Ticket minTarget;
  private Ticket defaultTarget;
  private Ticket customTarget;

  @Before
  public void setUp() {
    minTarget = new Ticket(ROWS_MINIMUM, COLUMNS_MINIMUM, NUMBERS_PER_ROW_MINIMUM, RANGE_MINIMUM);
    defaultTarget = new Ticket(ROWS_DEFAULT, COLUMNS_DEFAULT, NUMBERS_PER_ROW_DEFAULT, RANGE_DEFAULT);
  }

  @After
  public void tearDown() {
    minTarget = null;
    defaultTarget = null;
    customTarget = null;
  }

  @Test
  public void testDefaultRangeAndAllWinners() {
    int hitCount = 0;

    // assert there has not been any hits on the target and no winners
    assert defaultTarget.getNumberOfHits() == hitCount;

    for (int i = 1; i <= RANGE_DEFAULT; i++) {
      if (defaultTarget.selectNumber(i))
        hitCount++;
    }

    // assert the number of hits and winners were tracked properly
    assert defaultTarget.getNumberOfHits() == hitCount;
    assert defaultTarget.topLineWinner();
    assert defaultTarget.fullHouseWinner();
    assert defaultTarget.fullHouseWinner();
  }

  @Test
  public void testMinimumRangeAndAllWinners() {
    int hitCount = 0;

    // assert there has not been any hits on the target and no winners
    assert minTarget.getNumberOfHits() == hitCount;

    for (int i = 1; i <= RANGE_MINIMUM; i++) {
      if (minTarget.selectNumber(i))
        hitCount++;
    }

    // assert the number of hits and winners were tracked properly
    assert minTarget.getNumberOfHits() == hitCount;
    assert minTarget.topLineWinner();
    assert minTarget.fullHouseWinner();
  }

  /** these tests are stubbed but could easily be implemeted
   @Test public void getNumberOfHits() {
   }

   @Test public  topLineWinner() {
   }

   @Test public void fullHouseWinner() {
   }

   @Test public void selectNumber() {
   }
   */
}
