package model;

import java.util.ArrayList;

/**
 * @author Jan Eriksson & Ulrika Fahlen
 * @Version 1.0
 * @since 14/01/16
 */
public abstract class TicTacHelper {

  private TicTacHelper(){

  }

  public static boolean rowCheck(TileState[] winCheck){
    boolean isWin = true;
    for (TileState ts : winCheck){
      if(!ts.equals(winCheck[0]) || ts == TileState.UNOCCUPIED){
        isWin = false;
      }
    }
    return isWin;
  }
}
