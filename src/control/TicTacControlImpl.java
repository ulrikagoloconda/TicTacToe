package control;

import model.TicTacModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlen
 * @Version 1.0
 * @since 14/01/16
 */
public enum TicTacControlImpl implements TicTacControl {
  INSTANCE;

  TicTacModel ticTacModel;
  List<GenericObserver<TicTacControl>> observersList = new ArrayList<>();

  TicTacControlImpl() {
    ticTacModel = null;
  }

  @Override
  public void setModel(TicTacModel ticTacModel) {
       this.ticTacModel = ticTacModel;
  }

  @Override
  public boolean tileClick(int row, int column) {
    if (ticTacModel == null) {
      throw new ModelNotInitialisedException();
    }
    boolean returnBool;
    if (!ticTacModel.isGameOver()) {
      returnBool = ticTacModel.setTile(row, column);
      if (ticTacModel.isGameOver()) {
        ticTacModel.setGoMessage();
      }
    } else {
      returnBool = false;
    }
    notifyObservers();
    return returnBool;
  }

  // ToDo create a TicTac game gui and model.
  @Override
  public void startGame() {

  }

  // Todo close gui, exit application.
  @Override
  public void exitGame() {

  }

  @Override
  public String getStateSymbol() {
    return ticTacModel.getStateSymbol();
  }

  @Override
  public String getTile(int row, int column) {
    if (ticTacModel == null) {
      throw new ModelNotInitialisedException();
    }
    return ticTacModel.getTile(row, column);
  }

  @Override
  public void registerObserver(GenericObserver observer) {
    observersList.add(observer);
  }

  @Override
  public void removeObserver(GenericObserver observer) {
    observersList.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (GenericObserver<TicTacControl> observer : observersList) {
      observer.update(this);

    }
  }
}
