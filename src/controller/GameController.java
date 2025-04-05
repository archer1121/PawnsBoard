package controller;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.deck.Card;
import model.ModelListener;
import view.PawnsBoardSwingView;
import view.ViewListener;

public class GameController implements ViewListener, ModelListener {

  private final PawnsBoardModel model;
  private final PawnsBoardSwingView view;
  private final PlayerModel player;

  public GameController(PawnsBoardModel model, PawnsBoardSwingView view, PlayerModel player) {
    this.model = model;
    this.view = view;
    this.player = player;
    // Subscribe to model notifications
    this.model.addModelListener(this);
  }

  @Override
  public void handleCardClick(int cardIndex) {
    if (!playerIsActive()) return;

    Card card = getCardFromPlayerHand(cardIndex);
    if (card != null && model.canPlaceCard(card, 0, 1)) {
      model.placeCard(card, 0, 1); // Example placement
      model.switchTurn();
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
      model.switchTurn();
    } else if ("Cancel".equals(key)) {
      System.out.println("Player " + player.getPlayerColor() + " canceled move");
    }
  }

  // ModelListener callback: called when the turn changes.
  @Override
  public void onTurnChanged(PlayerColor currentPlayerColor) {
    if (player.getPlayerColor() == currentPlayerColor) {
      view.updateStatus("It's your turn, " + player.getPlayerColor() + "!");
    } else {
      view.updateStatus("Waiting for your turn. Current turn: " + currentPlayerColor);
    }
  }

  @Override
  public void onGameOver(PlayerColor winner) {
    view.updateStatus("Game Over! Winner: " + winner);
  }

  private boolean playerIsActive() {
    // In a full implementation, check the model's current turn.
    return true;
  }

  private Card getCardFromPlayerHand(int cardIndex) {
    if (cardIndex >= 0 && cardIndex < player.getHand().size()) {
      return player.getHand().get(cardIndex);
    }
    return null;
  }
}
