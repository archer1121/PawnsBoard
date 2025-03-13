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
   *
   * @param board
   * @return
   */
  public String textualPrint(Board board) {
    Cell[][] grid = board.getBoard(); // Get the board's 2D array

    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < board.getNumRows(); row++) {
      sb.append("[s] "); // left scoring column
      for (int col = 0; col < board.getNumCols(); col++) {
        sb.append(grid[row][col].textualPrint()).append(" "); // Append cell representation
      }
      sb.append("[s]\n"); // right scoring column
    }
    return sb.toString();
  }
}
