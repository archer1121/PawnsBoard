package model;

import model.cell.Cell;

/**
 * This interface represents the board for the game. The board is a 2D array.
 * Each space on the board can hold a Card, a PawnGroup, EmptySpace, or a Scoring block.
 *
 * <p>
 * If the board is 3x5, we initialize it to be 3x7 because we need to add scoring blocks
 * on both sides of the row.
 * </p>
 */

public interface Board {

  /**
   * Gets the current board as a 2D array of Cells.
   *
   * @return The 2D array representing the board.
   */
  public Cell[][] getBoard();

  /**
   * Returns the number of rows in the board.
   *
   * @return The number of rows in the board.
   */
  public int getNumRows();

  /**
   * Returns the number of columns in the board.
   *
   * @return The number of columns in the board.
   */
  public int getNumCols();

  /**
   * Initializes the board with the specified number of rows and columns.
   * Adds scoring blocks on both sides of the board and places pawns in the first row.
   *
   * @param rows The number of rows in the board.
   * @param cols The number of playable columns (excluding the scoring columns).
   */
  public void initBoard(int rows, int cols);

}
