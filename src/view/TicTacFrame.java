package view;

import control.GenericObserver;
import control.GenericSubject;
import control.ModelNotInitialisedException;
import control.TicTacControl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlén
 * @Version 1.0
 * @since 14/01/16
 */
public class TicTacFrame extends JFrame implements GenericObserver<TicTacControl> {
  JPanel panel;
  GridBagConstraints gbC;
  ArrayList<JButton> buttonList;
  TicTacControl ticTacControl;

  public TicTacFrame(TicTacControl ticTacControl){
    super("Tre I Rad");
    this.ticTacControl = ticTacControl;
    panel = new JPanel();
    buttonList = new ArrayList<>();
     GridBagLayout gb = new GridBagLayout();
    gbC = new GridBagConstraints();
    panel.setLayout(gb);
    add(panel);
    ticTacControl.registerObserver(this);
    addButtons();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    setPreferredSize(new Dimension(800,800));
    setVisible(true);
    pack();
  }

  private void addButtons() {
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++ ){
        JButton b = new JButton();
        b.setText(ticTacControl.getTile(i, j));
        b.setActionCommand("" + i + "" + j);
        b.addActionListener(e -> {
          String ac = b.getActionCommand();
          int r = Character.getNumericValue(ac.charAt(0));
          int c = Character.getNumericValue(ac.charAt(1));
          boolean moveOk = false;
          moveOk = ticTacControl.tileClick(r,c);
          if (!moveOk) {
            JOptionPane.showMessageDialog(this, "Tile not available.", "Gameplay error.",
                JOptionPane.ERROR_MESSAGE);
          }
        });
        buttonList.add(b);
        gbC.gridx = j;
        gbC.gridy = i;
        panel.add(b,gbC);
      }
    }
  }

  @Override
  public void update(TicTacControl subjectRef) {
    for (JButton b : buttonList) {
      String ac = b.getActionCommand();
      int r = Character.getNumericValue(ac.charAt(0));
      int c = Character.getNumericValue(ac.charAt(1));
      String s = subjectRef.getTile(r, c);
      b.setText(s);
    }
  }
}

