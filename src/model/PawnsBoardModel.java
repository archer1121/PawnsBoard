package model;

import model.cell.Cell;
import model.deck.Card;
import model.cell.EmptyCell;
import model.cell.ScoringCell;

public class PawnsBoardModel implements Board{
  private Cell[][] board;

  @Override
  public Cell[][] getBoard() {
    return this.board;
  }

  @Override
  public int getNumRows() {
    //if board isnt null need to put logic here
    return this.board[0].length;
  }

  @Override
  public int getNumCols() {
    return this.board.length;
  }

  /**
   * Create the board as a 2D array, given the size. We will add a column on both sides
   * for the scoring blocks.
   * To initialize, we also must place 3 pawns in each player's first row.
   *
   * @param rows num rows
   * @param cols num playable cols
   */
  @Override
  public void initBoard(int rows, int cols) {
    this.board = new Cell[rows][cols+2]; // +2 for the scoring rows

    //now we need a scoring block in the first and last columns and empty cell in all others
    for (int row=0; row < this.getNumRows(); row++) {
      for (int col=0; col<this.getNumCols(); col++) {

        //want a scoring block in each end and starting column
        if (col == 0 || col == this.getNumCols()-1) {
          this.board[row][col]= new ScoringCell(0);
        }
        else {
          this.board[row][col] = new EmptyCell();
        }
      }
    }
  }

  /**
   * @param card
   * @param row  starts at index 0
   * @param col  starts at index 0
   */
  @Override
  public void placeCard(Card card, int row, int col) {

  }
}
