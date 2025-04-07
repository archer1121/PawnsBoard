package controller;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.deck.Card;
import model.ModelListener;
import view.PawnsBoardSwingView;
import view.ViewListener;

import javax.swing.JOptionPane;

/**
 * The GameController handles communication between the view and model for a single player.
 * It processes player input, updates the view, and manages turn-based logic and card placement.
 */
public class GameController implements ViewListener, ModelListener {

  private final PawnsBoardModel model;
  private final PawnsBoardSwingView view;
  private final PlayerModel player;

  // Fields to store temporary selections
  private int selectedCardIndex = -1;
  private int selectedRow = -1;
  private int selectedCol = -1;

  /**
   * Constructs a new GameController for a player.
   *
   * @param model  the game model
   * @param view   the game view
   * @param player the player this controller manages
   */
  public GameController(PawnsBoardModel model, PawnsBoardSwingView view, PlayerModel player) {
    this.model = model;
    this.view = view;
    this.player = player;
    // Subscribe to model notifications so that turn changes can be communicated.
    this.model.addModelListener(this);
  }

  /**
   * Handles when the player clicks on a card.
   *
   * @param cardIndex the index of the clicked card
   */
  @Override
  public void handleCardClick(int cardIndex) {
    if (!playerIsActive()) {
      JOptionPane.showMessageDialog(
          view, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE
      );
      return;
    }
    selectedCardIndex = cardIndex;
    System.out.println("Player " + player.getPlayerColor() +
        " selected card at index " + cardIndex);
  }

  /**
   * Handles when the player clicks on a board cell.
   *
   * @param row the selected row
   * @param col the selected column
   */
  @Override
  public void handleCellClick(int row, int col) {
    if (!playerIsActive()) {
      JOptionPane.showMessageDialog(
          view, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE
      );
      return;
    }
    selectedRow = row;
    selectedCol = col;
    System.out.println("Player " + player.getPlayerColor() +
        " selected cell (" + row + ", " + col + ")");
    view.repaint();
  }

  /**
   * Handles when the player presses a key (e.g., Confirm or Cancel).
   *
   * @param key the pressed key
   */
  @Override
  public void handleKeyPress(String key) {
    if (!playerIsActive()) {
      JOptionPane.showMessageDialog(
          view, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    if ("Confirm".equals(key)) {
      if (selectedCardIndex == -1 || selectedRow == -1 || selectedCol == -1) {
        JOptionPane.showMessageDialog(
            view,
            "Please select a card and a cell before confirming.",
            "Invalid Move",
            JOptionPane.WARNING_MESSAGE
        );
        return;
      }

      Card card = getCardFromPlayerHand(selectedCardIndex);
      if (card == null) {
        JOptionPane.showMessageDialog(
            view,
            "Selected card is invalid.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        clearSelections();
        return;
      }

      if (!model.canPlaceCard(card, selectedRow, selectedCol)) {
        JOptionPane.showMessageDialog(
            view,
            "Cannot place card at the selected cell.",
            "Invalid Move",
            JOptionPane.ERROR_MESSAGE
        );
        clearSelections();
        return;
      }

      try {
        model.placeCard(card, selectedRow, selectedCol);
        player.removeCardFromHand(card);
        model.switchTurn();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
            view,
            "Error placing card: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
      }

      clearSelections();
    } else if ("Cancel".equals(key)) {
      clearSelections();
      JOptionPane.showMessageDialog(view, "Move canceled.", "Info",
          JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * Called when the turn changes in the model.
   *
   * @param currentPlayerColor the color of the player whose turn it is
   */
  @Override
  public void onTurnChanged(PlayerColor currentPlayerColor) {
    if (player.getPlayerColor() == currentPlayerColor) {
      view.updateStatus("It's your turn, " + player.getPlayerColor() + "!");
    } else {
      view.updateStatus("Waiting for your turn. Current turn: " + currentPlayerColor);
    }
  }

  /**
   * Called when the game ends.
   *
   * @param winner the color of the winning player
   */
  @Override
  public void onGameOver(PlayerColor winner) {
    view.updateStatus("Game Over! Winner: " + winner);
    JOptionPane.showMessageDialog(
        view, "Game Over! Winner: " + winner, "Game Over", JOptionPane.INFORMATION_MESSAGE
    );
  }

  // Returns true if it is this controller's player's turn
  private boolean playerIsActive() {
    return player.getPlayerColor() == model.getCurrentPlayerColor();
  }

  // Returns the selected card from the player's hand or null if invalid
  private Card getCardFromPlayerHand(int cardIndex) {
    if (cardIndex >= 0 && cardIndex < player.getHand().size()) {
      return player.getHand().get(cardIndex);
    }
    return null;
  }

  // Clears the current selection
  private void clearSelections() {
    selectedCardIndex = -1;
    selectedRow = -1;
    selectedCol = -1;
    // Optionally clear selection highlights in the view
  }
}
