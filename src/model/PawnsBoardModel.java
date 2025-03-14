package model;

import model.cell.Cell;
import model.deck.Card;
import model.cell.EmptyCell;
import model.cell.ScoringCell;

/**
 * The PawnsBoardModel class represents the board in the game. It manages the grid, including
 * the placement of cards and pawns, and initializes the board with scoring blocks on the sides.
 */
public class PawnsBoardModel implements Board {

  private Cell[][] board;

  @Override
  public Cell[][] getBoard() {
    return this.board;
  }

  @Override
  public int getNumRows() {
    // Return the number of rows in the board
    return this.board[0].length;
  }

  @Override
  public int getNumCols() {
    return this.board.length;
  }

  /**
   * Initializes the board with the specified number of rows and columns, adding scoring blocks
   * on the left and right sides of the board.
   *
   * @param rows The number of rows to initialize on the board.
   * @param cols The number of playable columns on the board.
   */
  @Override
  public void initBoard(int rows, int cols) {
    this.board = new Cell[rows][cols + 2]; // +2 for the scoring columns

    // Initialize the board with scoring blocks on both ends and empty cells in the middle
    for (int row = 0; row < this.getNumRows(); row++) {
      for (int col = 0; col < this.getNumCols(); col++) {

        // Add a scoring block on the first and last columns
        if (col == 0 || col == this.getNumCols() - 1) {
          this.board[row][col] = new ScoringCell(0);
        } else {
          this.board[row][col] = new EmptyCell();
        }
      }
    }
  }

  /**
   * Places a card at the specified location on the board.
   * This method is currently unimplemented but will be responsible for placing cards
   * on the board at a given row and column.
   *
   * @param card The playing card to be placed.
   * @param row The row where the card will be placed (starts at index 0).
   * @param col The column where the card will be placed (starts at index 0).
   */
  @Override
  public void placeCard(Card card, int row, int col) {
    // Method body intentionally left empty for now
    // This method will be implemented later
  }
}
