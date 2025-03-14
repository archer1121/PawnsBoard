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

  @Override
  public Cell[][] getBoard() {
    return this.board;
  }

  @Override
  public int getNumCols() {
    // Return the number of rows in the board
    return this.board[0].length;
  }

  @Override
  public int getNumRows() {
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

    //now we need a scoring block in the first and last columns and empty cell in all others
    //we also need a pawn in each player's first row
    for (int row = 0; row < this.getNumRows(); row++) {
      for (int col = 0; col < this.getNumCols(); col++) {

        //want a scoring block in each end and starting column
        if (col == 0 || col == this.getNumCols() - 1) {
          this.board[row][col] = new ScoringCell(0);
        }

        else if (col == 1) {
          this.board[row][col] = new PawnGroupCell(1, PlayerColor.RED);
        }
        else if (col == this.getNumCols() - 2) {
          this.board[row][col] = new PawnGroupCell(1, PlayerColor.BLUE);
        }

        else {
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

  public void placeCard(Card card, int row, int col) {

    this.board[row][col] = new CardCell(card);
  }

  /**
   * This method will calculate and insert new scoring cells with the proper scores.
   */
  public void scoreTheBoard() {

    for (int row = 0; row < board.length; row++) {
      int redScore = 0;
      int blueScore = 0;
      for (int col = 0; col < this.board[0].length; col++) {
        //if it's a card
        if (this.board[row][col] instanceof CardCell) {

          //if the card is red
          if (((CardCell) this.board[row][col]).getCardColor().equals(PlayerColor.RED)) {
            redScore += ((CardCell) this.board[row][col]).getCardValue();
          }

          //if the card is blue
          if (((CardCell) this.board[row][col]).getCardColor().equals(PlayerColor.BLUE)) {
            blueScore += ((CardCell) this.board[row][col]).getCardValue();
          }
        }
      }

      //if red has more red gets the points, if equal no points
      if (redScore > blueScore) {
        this.board[row][0] = new ScoringCell(redScore);
      }
      //if blue has more blue gets points
      if (redScore < blueScore) {
        this.board[row][this.board.length - 1] = new ScoringCell(blueScore);
      }
    }
  }
}
