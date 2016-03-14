import control.TicTacControlImpl;
import model.TicTacModelImpl;
import view.TicTacFrame;
import view.TicTacFxApp;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlen
 * @Version 1.0
 * @since 18/01/16
 */
public class TicTacGame implements Game {

  @Override
  public void startGame() {
    System.out.println("Någon har sagt att hörn är ett problem, men jag håller faktiskt inte riktigt med.");

    boolean fxView = false;

    if (fxView) {
      TicTacFxApp.runFxGame();
    } else {
      TicTacControlImpl.INSTANCE.setModel(new TicTacModelImpl());
      new TicTacFrame(TicTacControlImpl.INSTANCE);
    }
  }
}
