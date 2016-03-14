package model;

/**
 * @author Jan Eriksson & Ulrika Fahlen
 * @Version 1.0
 * @since 13/01/16
 */
public interface TicTacModel {
  boolean setTile(int row, int column);
  boolean isGameOver();
  String getTile(int row, int column);
  int getXMorale();
  int getOMorale();
  void incrementTurn();
  String getStateSymbol();


  void setGoMessage();
}
