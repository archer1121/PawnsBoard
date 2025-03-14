package model;

import model.cell.CardCell;
import model.cell.Cell;
import model.Deck.Card;
import model.cell.EmptyCell;
import model.cell.PawnGroupCell;
import model.cell.ScoringCell;

public class PawnsBoardModel implements Board{
  private Cell[][] board;

  @Override
  public Cell[][] getBoard() {
    return this.board;
  }

  @Override
  public int getNumCols() {
    //if board isnt null need to put logic here
    return this.board[0].length;
  }

  @Override
  public int getNumRows() {
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
    //we also need a pawn in each player's first row
    for (int row=0; row < this.getNumRows(); row++) {
      for (int col=0; col<this.getNumCols(); col++) {

        //want a scoring block in each end and starting column
        if (col == 0 || col == this.getNumCols()-1) {
          this.board[row][col]= new ScoringCell(0);
        }

        else if(col == 1) {
          this.board[row][col] = new PawnGroupCell(1, PlayerColor.RED);
        }
        else if(col == this.getNumCols()-2) {
          this.board[row][col] = new PawnGroupCell(1, PlayerColor.BLUE);
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
    this.board[row][col] = new CardCell(card);
  }

  public void scoreTheBoard() {

    for(int row = 0; row < board.length; row++) {
      int redScore = 0;
      int blueScore = 0;
      for(int col=0; col<this.board[0].length; col++) {
        //if it's a card
        if (this.board[row][col] instanceof CardCell) {

          //if the card is red
          if (((CardCell) this.board[row][col]).getCardColor().equals(PlayerColor.RED)) {
            redScore+=((CardCell) this.board[row][col]).getCardValue();
          }

          //if the card is blue
          if (((CardCell) this.board[row][col]).getCardColor().equals(PlayerColor.BLUE)) {
            blueScore+=((CardCell) this.board[row][col]).getCardValue();
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
