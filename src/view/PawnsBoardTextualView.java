package view;

import model.Board;
import model.cell.Cell;

public class PawnsBoardTextualView {

  /**
   * This method will print out the board in a textual String for easy viewing.
   *
   * [s] [] [] [] [] [] [s]
   * [s] [] [] [] [] [] [s]
   * [s] [] [] [] [] [] [s]
   *
   * @param board
   * @return
   */
  public String textualPrint(Board board) {
    // Get the board's 2D array
    Cell[][] grid = board.getBoard();

    // StringBuilder to accumulate the printed board
    StringBuilder sb = new StringBuilder();

    // Loop through each row in the board
    for (int row = 0; row < board.getNumRows() - 1; row++) {
      sb.append("[s] "); // Append left scoring column

      // Loop through each column in the row, starting from 1 (skipping left scoring cell)
      // and stopping before the last column (right scoring cell)
      for (int col = 1; col < board.getNumCols() - 1; col++) {
        sb.append(grid[row][col].textualPrint()).append(" "); // Append cell's textual representation
      }

      sb.append("[s]\n"); // Append right scoring column
    }

    return sb.toString();
  }
}
