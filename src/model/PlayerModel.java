package model;

import model.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel {

  private final PlayerColor color;
  private final List<Card> hand; // List of cards in the player's hand

  public PlayerModel(PlayerColor color) {
    this.color = color;
    this.hand = new ArrayList<>(); // Initialize the hand as an empty list
  }

  /**
   * Gets the player's hand of cards.
   *
   * @return A list of cards in the player's hand
   */
  public List<Card> getHand() {
    return hand;
  }

  /**
   * Adds a card to the player's hand.
   *
   * @param card The card to add to the hand
   */
  public void addCardToHand(Card card) {
    hand.add(card);
  }

  /**
   * Removes a card from the player's hand.
   *
   * @param card The card to remove from the hand
   */
  public void removeCardFromHand(Card card) {
    hand.remove(card);
  }

  /**
   * Gets the player's color.
   *
   * @return The color of the player
   */
  public PlayerColor getPlayerColor() {
    return color;
  }

  /**
   * Sets whether it's the player's turn.
   *
   * @param isTurn A boolean indicating if it's the player's turn
   */
  public void setTurn(boolean isTurn) {
    // Implement logic to set if it's the player's turn
  }

  // Other player-related methods...
}
