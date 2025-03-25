package model;

import model.cell.Cell;
import model.deck.Card;

/**
 * Mutable interface for the Pawns Board game model.
 * Extends the read-only interface and adds mutation methods.
 */
public interface Board extends ReadonlyPawnsBoardModel {

  /**
   * Initializes the board with the specified number of rows and columns.
   * Adds scoring blocks on both sides of the board and places pawns in the first row.
   *
   * @param rows The number of rows in the board.
   * @param cols The number of playable columns (excluding the scoring columns).
   */
  void initBoard(int rows, int cols);

  /**
   * Places a card at the specified location on the board.
   *
   * @param card The playing card to be placed.
   * @param row The row where the card will be placed (starts at index 0).
   * @param col The column where the card will be placed (starts at index 0).
   */
  void placeCard(Card card, int row, int col);

  /**
   * Calculates and updates the scores on the board.
   */
  void scoreTheBoard();
}