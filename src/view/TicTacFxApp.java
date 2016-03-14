package view;

import control.TicTacControl;
import control.TicTacControlImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.TicTacModelImpl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlén
 * @Version 1.0
 * @since 21/01/16
 */
public class TicTacFxApp extends Application implements Initializable {

  TicTacControl ticTacControl;

  @FXML
  Label currentPlayer;

  @FXML
  GridPane gridPane;

  @FXML
  Button exitGame;

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Lägg ett kryss, få en kyss");
    Parent root = FXMLLoader.load(getClass().getResource("TicTacFxView.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void runFxGame() {
    Application.launch();
  }

  private void setCellContent() {
    ObservableList<Node> gpChildren = gridPane.getChildren();

    for (Node node : gpChildren) {
      if (node instanceof Label) {
        Label label = (Label) node;

        Integer colIndex = GridPane.getColumnIndex(label);
        Integer rowIndex = GridPane.getRowIndex(label);

        colIndex = (colIndex == null) ? 0 : colIndex;
        rowIndex = (rowIndex == null) ? 0 : rowIndex;

        label.setText(ticTacControl.getTile(rowIndex, colIndex));
      }
    }
  }

  public void onClick(MouseEvent event){
    Label label = (Label)event.getSource();
    Integer colIndex = GridPane.getColumnIndex(label);
    Integer rowIndex = GridPane.getRowIndex(label);
    colIndex = (colIndex == null) ? 0 : colIndex;
    rowIndex = (rowIndex == null) ? 0 : rowIndex;
    if(!ticTacControl.tileClick(rowIndex,colIndex)){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Aj aj...");
      alert.setContentText("...Ernst rynkar på pannan och Gillar INTE va du just gjorde");
      alert.setHeaderText("Fel fel fel, upptaget! :) ");
      alert.showAndWait();
    }

    setCellContent();

    if(label.getText().equals("O")){
      currentPlayer.setText("X");
    } else {
      currentPlayer.setText("O");
    }
  }

  public void exitGame(){
    Platform.exit();
    System.exit(0);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    ticTacControl = TicTacControlImpl.INSTANCE;
    ticTacControl.setModel(new TicTacModelImpl());

    setCellContent();

    currentPlayer.setText(ticTacControl.getStateSymbol());
  }
}
