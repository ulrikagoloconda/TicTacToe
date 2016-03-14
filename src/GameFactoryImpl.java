/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlén
 * @Version 1.0
 * @since 18/01/16
 */
public class GameFactoryImpl implements GameFactory {

  @Override
  public Game createGame(String gameName) {
    if (gameName.equals("TicTacToe")) {
      return new TicTacGame();
    } else {
      return new MockGame();
    }
  }
}
