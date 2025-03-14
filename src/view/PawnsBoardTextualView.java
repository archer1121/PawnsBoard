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
    for (int row = 0; row < board.getNumRows(); row++) {
      for (int col = 0; col < board.getNumCols(); col++) {
        sb.append(grid[row][col].textualPrint()).append(" "); // Append cell's textual representation
      }
      sb.append("\n");
    }

    return sb.toString().trim();
  }
}
