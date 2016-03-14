package model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Jan Eriksson & Ulrika Fahlen
 * @Version 1.0
 * @since 14/01/16
 */
public class TicTacHelperTest {
  @Test
  public void testIsWinCross() throws Exception {
    TileState [] testArray = {TileState.CROSS, TileState.CROSS, TileState.CROSS};
    boolean result = TicTacHelper.rowCheck(testArray);
    assertTrue("Alla kryss ska la bli rätt", result);
  }

  @Test
  public void testIsWinNough() throws Exception {
    TileState [] testArray = {TileState.NOUGHT, TileState.NOUGHT, TileState.NOUGHT};
    boolean result = TicTacHelper.rowCheck(testArray);
    assertTrue("Alla ring ska la bli rätt", result);
  }

  @Test
  public void testNoWinCross() throws Exception {
    TileState [] testArray = {TileState.CROSS, TileState.NOUGHT, TileState.CROSS};
    boolean result = TicTacHelper.rowCheck(testArray);
    assertFalse("Det här ska inte ge vinst", result);
  }

  @Test
  public void testAllUnoccupied() throws Exception {
    TileState [] testArray = {TileState.UNOCCUPIED, TileState.UNOCCUPIED, TileState.UNOCCUPIED};
    boolean result = TicTacHelper.rowCheck(testArray);
    assertFalse("Alla tomma ska inte ge vinst.", result);
  }
}
