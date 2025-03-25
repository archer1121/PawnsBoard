package model.cell;

/**
 * Read-only interface for a cell on the board.
 */
public interface ReadonlyCell {
  /**
   * Returns a textual representation of the cell.
   * @return string representation
   */
  String textualPrint();
}