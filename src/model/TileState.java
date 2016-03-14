package model;

/**
 * @author Jan Eriksson & Ulrika Fahlen
 * @Version 1.0
 * @since 13/01/16
 */
public enum TileState {
  CROSS {
    @Override
    public String toString() {
      return "X";
    }
  },
  NOUGHT {
    @Override
    public String toString() {
      return "O";
    }
  },
  UNOCCUPIED {
    @Override
    public String toString() {
      return " ";
    }
  },
  FROZEN;
}
