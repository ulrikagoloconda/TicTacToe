/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlén
 * @Version 1.0
 * @since 14/01/16
 */
public class TicTacMain {
  public static void main(String [] args){
    GameFactory gameFactory = new GameFactoryImpl();
    Game theGame = gameFactory.createGame("TicTacToe");
    theGame.startGame();

  }
}
