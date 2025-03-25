package model.cell;

import model.deck.Card;
import model.PlayerColor;


/**
 * Represents a cell on the board that contains a card.
 * The CardCell class holds a single card and provides a method to
 * display it in a textual format (though it's currently empty).
 */
public class CardCell implements Cell {

  /**
   * Constructor to create a CardCell with a given card.
   *
   * @param c the card to be stored in this cell
   */
  public CardCell(Card c) {
    // No need for a class-level field to store the card
    // Card is used locally in textualPrint method
  }

  public int getCardValue() {
    return 0;
  }

  public PlayerColor getCardColor() {
    return PlayerColor.RED;
  }


  // We will accept C for now.
  @Override
  public String textualPrint() {
    return "C";
  }
}
