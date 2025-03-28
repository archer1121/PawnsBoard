package model;

import model.cell.Cell;
import model.deck.Card;

public class SimpleComputerPlayer extends PlayerModel {

  public SimpleComputerPlayer(PlayerColor color) {
    super(color);
  }

  public void takeTurn(PawnsBoardModel model) {
    // Simple strategy: Place card in the first available spot
    for (int row = 0; row < model.getNumRows(); row++) {
      for (int col = 0; col < model.getNumCols(); col++) {
        // Check if the cell is valid for the computer to place a card
        // For simplicity, assume it places the first valid card
        if (model.getBoard()[row][col] instanceof Cell) {
          // Get the first card from the computer's hand
          Card card = getHand().isEmpty() ? null : getHand().get(0);  // Get the first card (simplified)

          if (card != null && model.canPlaceCard(card, row, col)) {
            model.placeCard(card, row, col);
            removeCardFromHand(card);  // Remove the card from the hand after placing it
            return;
          }
        }
      }
    }
  }
}
