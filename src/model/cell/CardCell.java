package model.cell;

import model.deck.Card;

/**
 * Represents a cell on the board that contains a card.
 * The CardCell class holds a single card and provides a method to
 * display it in a textual format (though it's currently empty).
 */
public class CardCell implements Cell {

  private Card card;

  /**
   * Constructor to create a CardCell with a given card.
   *
   * @param c the card to be stored in this cell
   */
  public CardCell(Card c) {
    this.card = c;
  }

  @Override
  public String textualPrint() {
    return "";
  }
}
