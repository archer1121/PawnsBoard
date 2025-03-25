package model;

import model.cell.Cell;

/**
 * Read-only interface for the Pawns Board game model.
 * Contains all observation methods without mutation capabilities.
 */
public interface ReadonlyPawnsBoardModel {

  /**
   * Gets the current board as a 2D array of Cells.
   *
   * @return The 2D array representing the board.
   */
  Cell[][] getBoard();

  /**
   * Returns the number of rows in the board.
   *
   * @return The number of rows in the board.
   */
  int getNumRows();

  /**
   * Returns the number of columns in the board.
   *
   * @return The number of columns in the board.
   */
  int getNumCols();
}