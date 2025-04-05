package model;

import model.cell.CardCell;
import model.cell.Cell;
import model.deck.Card;
import model.cell.EmptyCell;
import model.cell.PawnGroupCell;
import model.cell.ScoringCell;
import java.util.ArrayList;
import java.util.List;

/**
 * The PawnsBoardModel class represents the board in the game.
 * It manages the grid, including the placement of cards and pawns,
 * and notifies listeners when important events occur.
 */
public class PawnsBoardModel implements Board {

  private Cell[][] board;
  private List<ModelListener> modelListeners; // List of listeners to notify
  private PlayerColor currentPlayerColor; // Keeps track of whose turn it is

  /**
   * Constructor does not auto-initialize, initBoard() must be explicitly called.
   */
  public PawnsBoardModel() {
    this.board = null;
    this.modelListeners = new ArrayList<>();
  }

  @Override
  public Cell[][] getBoard() {
    checkBoardInitialized();
    return this.board;
  }

  /**
   * Returns player color for use in controller.
   * @return Player
   */
  public PlayerColor getCurrentPlayerColor() {
    return currentPlayerColor;
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
   * Initializes the board with the specified number of rows and columns,
   * adding scoring blocks on the left and right sides of the board.
   *
   * @param rows The number of rows.
   * @param cols The number of playable columns.
   */
  @Override
  public void initBoard(int rows, int cols) {
    if (rows < 1 || cols < 3 || cols % 2 == 0) {
      throw new IllegalArgumentException("Invalid board dimensions: must have at " +
              "least 1 row and an odd number of playable columns (≥3).");
    }

    this.board = new Cell[rows][cols + 2]; // +2 for scoring columns

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
   * @param card The card to be placed.
   * @param row  The row (starting at index 0).
   * @param col  The column (starting at index 0).
   */
  public void placeCard(Card card, int row, int col) {
    Cell cell = board[row][col];
    if (cell instanceof PawnGroupCell) {
      PawnGroupCell pawnCell = (PawnGroupCell) cell;
      int pawnsInCell = pawnCell.getNumPawns();
      if (pawnsInCell >= card.getCost()) {
        pawnCell.removePawns(card.getCost());
        board[row][col] = new CardCell(card);
        applyInfluence(card, row, col);
      }
    }
  }

  private void applyInfluence(Card card, int row, int col) {
    char[][] influenceGrid = card.getInfluenceGrid();
    for (int i = 0; i < influenceGrid.length; i++) {
      for (int j = 0; j < influenceGrid[i].length; j++) {
        int affectedRow = row + (i - 2);
        int affectedCol = col + (j - 2);
        if (affectedRow >= 0 && affectedRow < board.length &&
                affectedCol >= 0 && affectedCol < board[0].length) {
          Cell affectedCell = board[affectedRow][affectedCol];
          if (influenceGrid[i][j] == '#') {
            if (affectedCell instanceof PawnGroupCell) {
              PawnGroupCell pawnCell = (PawnGroupCell) affectedCell;
              if (pawnCell.getPawnColor() == null) {
                pawnCell.addPawn(1);
              } else if (pawnCell.getPawnColor() == card.getPlayerColor()) {
                pawnCell.addPawn(1);
              } else {
                pawnCell.convertPawns(card.getPlayerColor());
              }
            }
          }
        }
      }
    }
  }

  /**
   * Updates scoring cells based on card values in the board.
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
      if (redScore > blueScore) {
        board[row][0] = new ScoringCell(redScore);
      } else if (blueScore > redScore) {
        board[row][board[0].length - 1] = new ScoringCell(blueScore);
      }
    }
  }

  public boolean canPlaceCard(Card card, int row, int col) {
    Cell cell = getBoard()[row][col];
    return (cell instanceof Cell) && hasPawnsToPlace(card, row, col);
  }

  private boolean hasPawnsToPlace(Card card, int row, int col) {
    return true; // Simplified for now
  }

  private void checkBoardInitialized() {
    if (this.board == null) {
      throw new IllegalStateException("Board has not been initialized. Call initBoard() first.");
    }
  }

  // ================= Model-Status Listener Methods ==================

  /**
   * Adds a ModelListener to be notified of game status changes.
   *
   * @param listener the listener to add.
   */
  public void addModelListener(ModelListener listener) {
    modelListeners.add(listener);
  }

  /**
   * Notifies all registered listeners that the turn has changed.
   */
  private void notifyTurnChanged() {
    for (ModelListener listener : modelListeners) {
      listener.onTurnChanged(currentPlayerColor);
    }
  }

  /**
   * Starts the game and notifies listeners of the initial turn.
   */
  public void startGame() {
    // For example, start with the Red player's turn.
    currentPlayerColor = PlayerColor.RED;
    notifyTurnChanged();
  }

  /**
   * Switches the turn and notifies listeners.
   */
  public void switchTurn() {
    currentPlayerColor = (currentPlayerColor == PlayerColor.RED) ? PlayerColor.BLUE : PlayerColor.RED;
    scoreTheBoard(); // Recalculate scores after the turn switch
    notifyTurnChanged();
  }
}
