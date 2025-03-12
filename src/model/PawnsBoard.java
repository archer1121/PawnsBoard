package model;

import model.cell.Cell;

public class PawnsBoard implements Board{
  private Cell[][] board;

  @Override
  public Board getBoard() {
    return this;
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
