package model.cell;

/**
 * Represents an empty cell on the board.
 * This class is used to represent spaces on the board that are unoccupied.
 */
public class EmptyCell implements Cell {

  @Override
  public String textualPrint() {
    return "_";
  }
}
