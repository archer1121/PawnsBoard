package view;

import model.Board;
import model.cell.Cell;

/**
 * This class provides a textual representation of the PawnsBoard for easy viewing.
 * The board is printed with scoring columns on both sides and the cells in between.
 *
 * <p>
 * Example output:
 * [s] [] [] [] [] [] [s]
 * [s] [] [] [] [] [] [s]
 * [s] [] [] [] [] [] [s]
 * </p>
 */
public class PawnsBoardTextualView {

  /**
   * Prints the board in a textual format, including the scoring columns on the sides.
   * The cells are printed in between the scoring columns.
   *
   * <p>The output for a 3x5 board might look like this:</p>
   * <pre>
   * [s] [] [] [] [] [] [s]
   * [s] [] [] [] [] [] [s]
   * [s] [] [] [] [] [] [s]
   * </pre>
   *
   * @param board The board object containing the cells to be printed
   * @return A string representing the textual print of the board
   */
  public String textualPrint(Board board) {
    // Get the board's 2D array
    Cell[][] grid = board.getBoard();

    // StringBuilder to accumulate the printed board
    StringBuilder sb = new StringBuilder();

    // Loop through each row in the board
    for (int row = 0; row < board.getNumRows(); row++) {
      for (int col = 0; col < board.getNumCols(); col++) {
        sb.append(grid[row][col].textualPrint()).append(" "); // Append cell's textual representation
      }
      sb.append("\n");
    }

    return sb.toString().trim();
  }
}
