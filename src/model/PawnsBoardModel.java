package model;

import model.cell.CardCell;
import model.cell.Cell;
import model.deck.Card;
import model.cell.EmptyCell;
import model.cell.PawnGroupCell;
import model.cell.ScoringCell;

/**
 * The PawnsBoardModel class represents the board in the game. It manages the grid, including
 * the placement of cards and pawns, and initializes the board with scoring blocks on the sides.
 */

//INVARIANT:
// The board must always have at least 1 row and
// an odd number of columns (≥3) excluding scoring cells.

public class PawnsBoardModel implements Board {

  private Cell[][] board;

  /**
   * Constructor does not auto-initialize, initBoard() must be explicitly called.
   */
  public PawnsBoardModel() {

    this.board = null;
  }

  @Override
  public Cell[][] getBoard() {
    checkBoardInitialized();
    return this.board;
  }

  @Override
  public int getNumCols() {
    checkBoardInitialized();
    return this.board[0].length;
  }

  @Override
  public int getNumRows() {
    checkBoardInitialized();
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
    if (rows < 1 || cols < 3 || cols % 2 == 0) {
      throw new IllegalArgumentException("Invalid board dimensions: must have at " +
              "least 1 row and an odd number of playable columns (≥3).");
    }

    this.board = new Cell[rows][cols + 2]; // +2 for the scoring columns

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols + 2; col++) {
        // Scoring cells at both ends
        if (col == 0 || col == cols + 1) {
          this.board[row][col] = new ScoringCell(0);
        }
        // First playable column has red pawns
        else if (col == 1) {
          this.board[row][col] = new PawnGroupCell(1, PlayerColor.RED);
        }
        // Last playable column has blue pawns
        else if (col == cols) {
          this.board[row][col] = new PawnGroupCell(1, PlayerColor.BLUE);
        }
        // Empty cells in between
        else {
          this.board[row][col] = new EmptyCell();
        }
      }
    }
  }

  /**
   * Places a card at the specified location on the board.
   *
   * @param card The playing card to be placed.
   * @param row The row where the card will be placed (starts at index 0).
   * @param col The column where the card will be placed (starts at index 0).
   */
  public void placeCard(Card card, int row, int col) {
    checkBoardInitialized();
    if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols()) {
      throw new IndexOutOfBoundsException("Invalid row or column for placing a card.");
    }
    this.board[row][col] = new CardCell(card);
  }

  /**
   * Updates scoring cells based on the number of red and blue cards in each row.
   */
  public void scoreTheBoard() {
    checkBoardInitialized();

    for (int row = 0; row < board.length; row++) {
      int redScore = 0;
      int blueScore = 0;

      for (int col = 0; col < board[row].length; col++) {
        if (board[row][col] instanceof CardCell) {
          CardCell cardCell = (CardCell) board[row][col];
          if (cardCell.getCardColor() == PlayerColor.RED) {
            redScore += cardCell.getCardValue();
          } else if (cardCell.getCardColor() == PlayerColor.BLUE) {
            blueScore += cardCell.getCardValue();
          }
        }
      }

      // Assign scores to the left (red) and right (blue) scoring columns
      if (redScore > blueScore) {
        board[row][0] = new ScoringCell(redScore);
      } else if (blueScore > redScore) {
        board[row][board[0].length - 1] = new ScoringCell(blueScore);
      }
    }
  }

  /**
   * Ensures that the board has been initialized before attempting to access it.
   */
  private void checkBoardInitialized() {
    if (this.board == null) {
      throw new IllegalStateException("Board has not been initialized. Call initBoard() first.");
    }
  }
}
