package model.cell;

import model.deck.Card;
import model.PlayerColor;

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

  /**
   * The value of a card cell (typically the card's value).
   * @return int card value
   */
  public int getCardValue() {
    return this.card.getValue();
  }

  /**
   * The card color (either red or blue).
   * @return PlayerColor of the card
   */
  public PlayerColor getCardColor() {
    return this.card.getPlayerColor();
  }

  @Override
  public String textualPrint() {
    return "C"; // Placeholder for card visual representation
  }

  /**
   * Get the card stored in this cell.
   * @return the Card in the cell
   */
  public Card getCard() {
    return card;
  }

  /**
   * Sets the card in this cell.
   * @param card the card to set in the cell
   */
  public void setCard(Card card) {
    this.card = card;
  }
}
