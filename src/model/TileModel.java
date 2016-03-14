package model;

import java.util.Random;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlén
 * @Version 1.0
 * @since 13/01/16
 */
public class TileModel {
  private final int morale;
  private TileState tileState;

  TileModel(){
    tileState = TileState.UNOCCUPIED;
    Random rand = new Random();
    morale = rand.nextInt(1000)+1;
  }

  public TileState getTileState() {
    return tileState;
  }

  public void setTileState(TileState tileState) {
    this.tileState = tileState;
  }

  public int getMorale() {
    return morale;
  }
}
