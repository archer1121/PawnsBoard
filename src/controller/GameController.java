package controller;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.SimpleComputerPlayer;
import model.deck.Card;
import model.cell.Cell;
import view.PawnsBoardSwingView;
import view.ViewListener;

public class GameController {

  private final PawnsBoardModel model;
  private final PawnsBoardSwingView view;

  private PlayerModel player1;
  private PlayerModel player2;
  private PlayerModel currentPlayer;
  private boolean isGameOver;


  public GameController(PawnsBoardModel model, PawnsBoardSwingView view) {
    this.model = model;
    this.view = view;

    // Initialize both players
    this.player1 = new PlayerModel(PlayerColor.RED);
    this.player2 = new SimpleComputerPlayer(PlayerColor.BLUE);

    // Start with red player's turn (human player)
    this.currentPlayer = player1;
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

  public void handleCardClick(int cardIndex) {
    if (isGameOver) return;

    Card card = getCardFromPlayerHand(cardIndex);
    if (card != null) {
      // Place the card based on the current player's turn
      if (currentPlayer.getPlayerColor() == PlayerColor.RED) {
        // Human player places card
        model.placeCard(card, 0, 1); // Example, assuming row 0, col 1 for Red
      } else {
        // Computer player places card
        if (player2 instanceof SimpleComputerPlayer) {
          ((SimpleComputerPlayer) player2).takeTurn(model);
        }
      }

      // Switch turn after placing card
      switchTurn();
    }
  }

  public void handleCellClick(int row, int col) {
    if (isGameOver) return;

    // Handle human player's interaction with the board
    if (model.getBoard()[row][col] instanceof Cell) {
      System.out.println("Cell clicked at (" + row + ", " + col + ")");
    }

    // Repaint the view to reflect changes
    view.repaint();
  }

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
    if (currentPlayer == player1) {
      currentPlayer.setTurn(false); // Red's turn is over
      currentPlayer = player2;  // Switch to Computer (Blue)
      currentPlayer.setTurn(true);  // Computer's turn
    } else {
      currentPlayer.setTurn(false); // Computer's turn is over
      currentPlayer = player1;    // Switch to Red (Human)
      currentPlayer.setTurn(true);  // Human's turn
    }

    System.out.println("It's " + currentPlayer.getPlayerColor() + "'s turn");
    model.scoreTheBoard(); // Recalculate scores after each turn
  }

  private Card getCardFromPlayerHand(int cardIndex) {
    return null; // Retrieve the card from the hand of the player
  }

  public void endGame() {
    isGameOver = true;
    System.out.println("Game Over!");
  }

  public PlayerColor getCurrentPlayerColor() {
    return currentPlayer.getPlayerColor();
  }

  public PlayerModel getCurrentPlayer() {
    return currentPlayer;
  }
}