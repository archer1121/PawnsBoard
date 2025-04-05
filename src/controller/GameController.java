package controller;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.deck.Card;
import model.ModelListener;
import view.PawnsBoardSwingView;
import view.ViewListener;

import javax.swing.JOptionPane;

public class GameController implements ViewListener, ModelListener {

  private final PawnsBoardModel model;
  private final PawnsBoardSwingView view;
  private final PlayerModel player;

  // Fields to store temporary selections
  private int selectedCardIndex = -1;
  private int selectedRow = -1;
  private int selectedCol = -1;

  public GameController(PawnsBoardModel model, PawnsBoardSwingView view, PlayerModel player) {
    this.model = model;
    this.view = view;
    this.player = player;
    // Subscribe to model notifications so that turn changes can be communicated.
    this.model.addModelListener(this);
  }

  @Override
  public void handleCardClick(int cardIndex) {
    if (!playerIsActive()) {
      JOptionPane.showMessageDialog(view, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    // Store the selected card index for later confirmation.
    selectedCardIndex = cardIndex;
    System.out.println("Player " + player.getPlayerColor() + " selected card at index " + cardIndex);
  }

  @Override
  public void handleCellClick(int row, int col) {
    if (!playerIsActive()) {
      JOptionPane.showMessageDialog(view, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    // Store the selected cell coordinates.
    selectedRow = row;
    selectedCol = col;
    System.out.println("Player " + player.getPlayerColor() + " selected cell (" + row + ", " + col + ")");
    view.repaint();
  }

  @Override
  public void handleKeyPress(String key) {
    if (!playerIsActive()) {
      JOptionPane.showMessageDialog(view, "Not your turn!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
    if ("Confirm".equals(key)) {
      // Ensure both a card and a cell are selected.
      if (selectedCardIndex == -1 || selectedRow == -1 || selectedCol == -1) {
        JOptionPane.showMessageDialog(view, "Please select a card and a cell before confirming.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
        return;
      }
      // Retrieve the card from the player's hand.
      Card card = getCardFromPlayerHand(selectedCardIndex);
      if (card == null) {
        JOptionPane.showMessageDialog(view, "Selected card is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
        clearSelections();
        return;
      }
      // Validate the move with the model.
      if (!model.canPlaceCard(card, selectedRow, selectedCol)) {
        JOptionPane.showMessageDialog(view, "Cannot place card at the selected cell.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
        clearSelections();
        return;
      }
      // Attempt to place the card.
      try {
        model.placeCard(card, selectedRow, selectedCol);
        player.removeCardFromHand(card);  // Remove card after successful placement.
        model.switchTurn();  // Switch turns after a valid move.
      } catch (Exception e) {
        JOptionPane.showMessageDialog(view, "Error placing card: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      }
      clearSelections();
    } else if ("Cancel".equals(key)) {
      // Clear temporary selections and inform the player.
      clearSelections();
      JOptionPane.showMessageDialog(view, "Move canceled.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  // ModelListener callback: Notified when the turn changes.
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
    JOptionPane.showMessageDialog(view, "Game Over! Winner: " + winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);
  }

  // Check if this controller's player is the active player.
  private boolean playerIsActive() {
    return player.getPlayerColor() == model.getCurrentPlayerColor();
  }

  // Returns the card from the player's hand based on the selected index.
  private Card getCardFromPlayerHand(int cardIndex) {
    if (cardIndex >= 0 && cardIndex < player.getHand().size()) {
      return player.getHand().get(cardIndex);
    }
    return null;
  }

  // Clears any temporary selections (both card and cell).
  private void clearSelections() {
    selectedCardIndex = -1;
    selectedRow = -1;
    selectedCol = -1;
    // Optionally, you can also instruct the view to clear any visual selection indicators.
  }
}
