package control;

import model.TicTacModel;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlen
 * @Version 1.0
 * @since 14/01/16
 */
public interface TicTacControl extends GenericSubject {
  void setModel(TicTacModel ticTacModel);
  boolean tileClick(int row, int column) throws ModelNotInitialisedException;
  void startGame();
  void exitGame();

  String getStateSymbol();
  String getTile(int row, int column);
}
