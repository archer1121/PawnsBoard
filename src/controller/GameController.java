package controller;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.deck.Card;
import view.PawnsBoardSwingView;
import view.ViewListener;

public class GameController implements ViewListener {

  private final PawnsBoardModel model;
  private final PawnsBoardSwingView view;
  private final PlayerModel player; // The player this controller is responsible for

  public GameController(PawnsBoardModel model, PawnsBoardSwingView view, PlayerModel player) {
    this.model = model;
    this.view = view;
    this.player = player;

    // Optionally, you can still register an internal listener if needed
    // But since this controller now implements ViewListener, it can be directly used.
  }

  @Override
  public void handleCardClick(int cardIndex) {
    if (!playerIsActive()) return;

    Card card = getCardFromPlayerHand(cardIndex);
    if (card != null) {
      // Example: Place the card at a specific location (adjust as per your game rules)
      if (model.canPlaceCard(card, 0, 1)) {
        model.placeCard(card, 0, 1);
        switchTurn();
      }
    }
  }

  @Override
  public void handleCellClick(int row, int col) {
    if (!playerIsActive()) return;
    System.out.println("Player " + player.getPlayerColor() + " clicked cell (" + row + ", " + col + ")");
    view.repaint();
  }

  @Override
  public void handleKeyPress(String key) {
    if (!playerIsActive()) return;
    if ("Confirm".equals(key)) {
      System.out.println("Player " + player.getPlayerColor() + " confirmed move");
      switchTurn();
    } else if ("Cancel".equals(key)) {
      System.out.println("Player " + player.getPlayerColor() + " canceled move");
      // Clear any temporary selections if needed
    }
  }

  // Helper: Determines if it's this player's turn (placeholder logic)
  private boolean playerIsActive() {
    // In a complete implementation, check with the model's turn state.
    return true;
  }

  // Helper: Switch turn logic (placeholder)
  private void switchTurn() {
    System.out.println("Switching turn from " + player.getPlayerColor());
    // Implement your turn switching logic here.
  }

  // Helper: Retrieve card from player's hand
  private Card getCardFromPlayerHand(int cardIndex) {
    if (cardIndex >= 0 && cardIndex < player.getHand().size()) {
      return player.getHand().get(cardIndex);
    }
    return null;
  }
}
