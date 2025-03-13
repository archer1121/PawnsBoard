package model;

import model.cell.Cell;
import model.Deck.Card;

/**
 * This interface represents the board for the game. The board is a 2D array.
 * Each space on the board can hold a Card, a PawnGroup, EmptySpace, or a Scoring block.
 *
 * If the board is 3x5, we initialize it to be 3x7 because we need to add scoring blocks
 * on both sides of the row.
 */
public interface Board {

  public Board getBoard();
  public int getNumRows();
  public int getNumCols();

  /**
   * Create the board as a 2D array, given the size. We will add a column on both sides
   * for the scoring blocks.
   * To initialize, we also must place 3 pawns in each player's first row.
   * @param rows num rows
   * @param cols num playable cols
   */
  public void initBoard(int rows, int cols);

  /**
   *
   * @param card is a playing card
   * @param row starts at index 0
   * @param col starts at index 0
   */
  public void placeCard(Card card, int row, int col);




}
