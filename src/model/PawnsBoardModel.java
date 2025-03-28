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
   * @param row  The row where the card will be placed (starts at index 0).
   * @param col  The column where the card will be placed (starts at index 0).
   */
  public void placeCard(Card card, int row, int col) {
    Cell cell = board[row][col];

    // Check if the cell is a PawnGroupCell
    if (cell instanceof PawnGroupCell) {
      PawnGroupCell pawnCell = (PawnGroupCell) cell;
      // Check if the player has enough pawns in the selected cell
      int pawnsInCell = pawnCell.getNumPawns();
      if (pawnsInCell >= card.getCost()) {
        // Remove the pawns and place the card in the cell
        pawnCell.removePawns(card.getCost());
        // We assume a CardCell is appropriate for this cell type
        board[row][col] = new CardCell(card);

        // Apply card's influence grid to nearby cells
        applyInfluence(card, row, col);
      }
    }
    // If the cell is not a PawnGroupCell, return false
  }


  private void applyInfluence(Card card, int row, int col) {
    char[][] influenceGrid = card.getInfluenceGrid();

    for (int i = 0; i < influenceGrid.length; i++) {
      for (int j = 0; j < influenceGrid[i].length; j++) {
        // Determine the affected cell's coordinates
        int affectedRow = row + (i - 2);  // Adjust by 2 for the center of the grid
        int affectedCol = col + (j - 2);

        // Make sure the cell is within bounds
        if (affectedRow >= 0 && affectedRow < board.length &&
            affectedCol >= 0 && affectedCol < board[0].length) {

          // Get the affected cell
          Cell affectedCell = board[affectedRow][affectedCol];

          // Apply the influence only if it's marked by '#'
          if (influenceGrid[i][j] == '#') {

            // Check if the affected cell is a PawnGroupCell, where we can apply pawn-related effects
            if (affectedCell instanceof PawnGroupCell) {
              PawnGroupCell pawnCell = (PawnGroupCell) affectedCell;

              if (pawnCell.getPawnColor() == null) {
                // Empty cell: add 1 pawn of the current player
                pawnCell.addPawn(1);
              } else if (pawnCell.getPawnColor() == card.getPlayerColor()) {
                // Cell already owned by the player: increase pawns by 1
                pawnCell.addPawn(1);
              } else {
                // Enemy cell: convert enemy pawns
                pawnCell.convertPawns(card.getPlayerColor());
              }
            }
            // If you want to add more actions for other cell types (like ScoringCell, EmptyCell, etc.), you can add further checks here.
          }
        }
      }
    }
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

  public boolean canPlaceCard(Card card, int row, int col) {
    // Check if the cell is valid for placing the card based on game rules
    Cell cell = getBoard()[row][col];
    if (cell instanceof Cell && hasPawnsToPlace(card, row, col)) {
      return true;
    }
    return false;
  }

  private boolean hasPawnsToPlace(Card card, int row, int col) {
    // Logic to check if the player has enough pawns in the cell to place the card
    return true; // Simplified; you need to implement this based on your game rules
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
