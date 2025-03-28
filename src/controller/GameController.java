package controller;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.deck.Card;
import model.cell.Cell;
import view.PawnsBoardSwingView;
import view.ViewListener;

public class GameController {

  private final PawnsBoardModel model;
  private final PawnsBoardSwingView view;

  private PlayerModel playerRed;
  private PlayerModel playerBlue;
  private PlayerModel currentPlayer;
  private boolean isGameOver;

  public GameController(PawnsBoardModel model, PawnsBoardSwingView view) {
    this.model = model;
    this.view = view;

    // Initialize both players
    this.playerRed = new PlayerModel(PlayerColor.RED);
    this.playerBlue = new PlayerModel(PlayerColor.BLUE);

    // Start with red player's turn
    this.currentPlayer = playerRed;
    this.currentPlayer.setTurn(true); // Red starts first
    this.isGameOver = false;

    // Register view listeners to handle user actions
    this.view.addClickListener(new ViewListener() {
      @Override
      public void handleCardClick(int cardIndex) {
        handleCardClick(cardIndex);
      }

      @Override
      public void handleCellClick(int row, int col) {
        handleCellClick(row, col);
      }

      @Override
      public void handleKeyPress(String key) {
        handleKeyPress(key);
      }
    });
  }

  // Handle card clicks (usually to play a card)
  public void handleCardClick(int cardIndex) {
    if (isGameOver) return;

    // Get the card from the player's hand (for simplicity, assuming player has a hand model)
    // You may need to adjust this to fit how your model is structured
    Card card = getCardFromPlayerHand(cardIndex);

    if (card != null) {
      // Logic for placing the card on the board
      // For example, use the model's `placeCard` method
      if (currentPlayer.getPlayerColor() == PlayerColor.RED) {
        // Assume player is placing card in the first available empty spot
        model.placeCard(card, 0, 1); // Example, assuming row 0, col 1 for Red Player
      } else {
        model.placeCard(card, 0, model.getNumCols() - 2); // Example, assuming row 0, last column for Blue Player
      }

      // Switch turn after placing card
      switchTurn();
    }
  }

  // Handle cell clicks (e.g., for pawn movement or card interactions)
  public void handleCellClick(int row, int col) {
    if (isGameOver) return;

    // Example: Handle pawn movement or card placement on cell click
    if (model.getBoard()[row][col] instanceof Cell) {
      System.out.println("Cell clicked at (" + row + ", " + col + ")");
    }

    // After the action, update the view
    view.repaint(); // Repaint the board to reflect the changes
  }

  // Handle key presses for actions like confirming or canceling moves
  public void handleKeyPress(String key) {
    if (isGameOver) return;

    if ("Confirm".equals(key)) {
      System.out.println("Move confirmed");
      switchTurn();
    } else if ("Cancel".equals(key)) {
      System.out.println("Move canceled");
    }
  }

  // Switch turns between players
  private void switchTurn() {
    if (currentPlayer == playerRed) {
      currentPlayer.setTurn(false); // Red's turn is over
      currentPlayer = playerBlue;  // Switch to Blue
      currentPlayer.setTurn(true);  // Blue's turn
    } else {
      currentPlayer.setTurn(false); // Blue's turn is over
      currentPlayer = playerRed;    // Switch to Red
      currentPlayer.setTurn(true);  // Red's turn
    }

    System.out.println("It's " + currentPlayer.getPlayerColor() + "'s turn");
    model.scoreTheBoard(); // Recalculate scores (could be called after each turn)
  }

  // Helper method to get a card from the player's hand
  private Card getCardFromPlayerHand(int cardIndex) {
    return null; // Return the card at cardIndex from the player's hand (implement this)
  }

  // End the game
  public void endGame() {
    isGameOver = true;
    System.out.println("Game Over!");
  }

  // Add a method to get the current player's color
  public PlayerColor getCurrentPlayerColor() {
    return currentPlayer.getPlayerColor();
  }

  // Add a method to get the current player's model
  public PlayerModel getCurrentPlayer() {
    return currentPlayer;
  }
}
