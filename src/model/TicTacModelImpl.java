package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jan Eriksson & Ulrika Goloconda Fahlén
 * @Version 1.0
 * @since 13/01/16
 */
public class TicTacModelImpl implements TicTacModel {

  TileState[][] board;
  String [][] boardMorale;
  int turn;
  TileState evenTurnState;
  TileState oddTurnState;

  Random randomEngine;

  public TicTacModelImpl() {
    board = new TileState[3][3];
    boardMorale = new String[3][3];
    randomEngine = new Random();
    initGame();
  }

  private void initGame() {
    for (int i = 0; i < board[0].length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = TileState.UNOCCUPIED;
        boardMorale[i][j] = String.valueOf(randomEngine.nextInt(100)+1);
      }
    }

    if (randomEngine.nextBoolean()) {
      evenTurnState = TileState.CROSS;
      oddTurnState = TileState.NOUGHT;
    } else {
      evenTurnState = TileState.NOUGHT;
      oddTurnState = TileState.CROSS;
    }
    turn = 1;
  }

  private TileState getTurnState() {
    if(turn%2 == 0) {
      return evenTurnState;
    } else {
      return oddTurnState;
    }
  }

  @Override
  public boolean setTile(int row, int column) {
    boolean moveOk = false;
    if(board[row][column]== TileState.UNOCCUPIED) {
      moveOk = true;
      board[row][column] = getTurnState();
      incrementTurn();
    }
    return moveOk;
  }


  @Override
  public boolean isGameOver() {
    boolean isGameOver = false;
    if(winCheck() || isFull()) {
      isGameOver = true;
    }
    return isGameOver;
  }

  private boolean isFull() {
    return (turn == 10) ? true : false;
  }

  /**
   * Creates a list of rows from the board matrix.
   *
   * @return A list of rows in a ArrayList.
   */
  private List flattenMatrix() {
    ArrayList<TileState[]> flattenedArray = new ArrayList();
    TileState[] tempRow = new TileState[3];

    // Collect rows
    for (int i = 0; i < board[0].length; i++) {
      flattenedArray.add(board[i]);
    }

    // Collect columns
    for (int i = 0; i < board[0].length; i++) {
      tempRow = new TileState[3];
      for (int j = 0; j < board[0].length; j++) {
        tempRow[j] = board[j][i];
      }
      flattenedArray.add(tempRow);
    }

    // Collect diagonals
    tempRow = new TileState[3];
    for (int i = 0; i < board[0].length; i++) {
      tempRow[i] = board[i][i];
    }
    flattenedArray.add(tempRow);

    tempRow = new TileState[3];
    for (int i = 0; i < board[0].length; i++) {
      tempRow[i] = board[i][2-i];
    }
    flattenedArray.add(tempRow);

    return flattenedArray;
  }

  private boolean winCheck() {
    boolean isWin = false;
    List<TileState[]> rows = flattenMatrix();
    for (TileState[] row : rows) {;
      if (TicTacHelper.rowCheck(row)) {
        isWin = true;

      }
    }
    return isWin;
  }

  @Override
  public String getTile(int row, int collumn) {
    String returnString = "";
    if(board[row][collumn] == TileState.UNOCCUPIED || board[row][collumn] == TileState.FROZEN){
      returnString = "" + boardMorale[row][collumn];
    }else {
      returnString = board[row][collumn].toString();
    }
    return returnString;
  }

  @Override
  public int getXMorale() {
    int moraleX = 0;
    for (int i = 0; i < boardMorale[0].length; i++){
      for (int j = 0; j < boardMorale[0].length; j++){
        if(board[i][j].equals(TileState.CROSS)){
         int k = Integer.valueOf(boardMorale[i][j]);
          moraleX += k;
        }
      }
    }
    return moraleX;
  }

  @Override
  public int getOMorale() {
    int moraleO = 0;
    for (int i = 0; i < boardMorale[0].length; i++){
      for (int j = 0; j < boardMorale[0].length; j++){
        if(board[i][j].equals(TileState.NOUGHT)){
          moraleO += Integer.valueOf(boardMorale[i][j]);
        }
      }
    }
    return moraleO;
  }

  @Override
  public void incrementTurn() {
    if (!isGameOver()) {
      turn++;
    }
  }

  @Override
  public String getStateSymbol() {
    return getTurnState().toString();
  }

  @Override
  public void setGoMessage() {
    if (winCheck()) {

      String morale;
      if (getTurnState() == TileState.CROSS) {
        morale = Integer.toString(getXMorale());
      } else {
        morale = Integer.toString(getOMorale());
      }

      boardMorale[0][0] = " ";
      boardMorale[0][1] = getTurnState().toString();
      boardMorale[0][2] = " ";
      boardMorale[1][0] = "W";
      boardMorale[1][1] = "I";
      boardMorale[1][2] = "N";
      for (int i = 0; i < morale.length(); i++) {
        if (i == 3){
          break;
        }
       boardMorale[2][i] = morale.substring(i, i+1);
     }
    } else {
      boardMorale[0][0] = "T";
      boardMorale[0][1] = "H";
      boardMorale[0][2] = "E";
      boardMorale[1][0] = " ";
      boardMorale[1][1] = " ";
      boardMorale[1][2] = " ";
      boardMorale[2][0] = "E";
      boardMorale[2][1] = "N";
      boardMorale[2][2] = "D";

    }
    for (int i = 0; i < board[0].length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = TileState.FROZEN;
      }
    }
  }
}
