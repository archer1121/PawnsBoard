package model;

import model.cell.Cell;
import model.deck.Card;

/**
 * A simple AI-controlled player that plays the first valid card it can on its turn.
 */
public class SimpleComputerPlayer extends PlayerModel {

  /**
   * Constructs a simple computer player of the specified color.
   *
   * @param color the color of the player (RED or BLUE)
   */
  public SimpleComputerPlayer(PlayerColor color) {
    super(color);
  }

  /**
   * Takes a turn by placing the first valid card in the first valid position found.
   *
   * @param model the game model to interact with the board
   */
  public void takeTurn(PawnsBoardModel model) {
    for (int row = 0; row < model.getNumRows(); row++) {
      for (int col = 0; col < model.getNumCols(); col++) {
        if (model.getBoard()[row][col] instanceof Cell) {
          Card card = getHand().isEmpty() ? null : getHand().get(0);

          if (card != null && model.canPlaceCard(card, row, col)) {
            model.placeCard(card, row, col);
            removeCardFromHand(card);
            return;
          }
        }
      }
    }
  }
}
