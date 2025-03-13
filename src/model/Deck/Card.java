package model.Deck; // Declares that this class belongs to the model.Deck package

/**
 * Represents a card in the game, containing attributes such as name, cost, value,
 * and an influence grid that determines its effect.
 */
public class Card {

  // Card attributes
  private final String name;        // Name of the card
  private final int cost;           // Cost required to play the card
  private final int value;          // Value or points the card provides
  private final char[][] influenceGrid; // A grid representing the card's influence pattern

  /**
   * Constructor to initialize a Card object with the given attributes.
   *
   * @param name The name of the card
   * @param cost The cost of playing the card
   * @param value The value associated with the card
   * @param influenceGrid A 2D character array representing the card's influence pattern
   */
  public Card(String name, int cost, int value, char[][] influenceGrid) {
    this.name = name;
    this.cost = cost;
    this.value = value;
    this.influenceGrid = influenceGrid;
  }

  /**
   * Gets the name of the card.
   *
   * @return The card's name
   */
  public String getName() { return name; }

  /**
   * Gets the cost of the card.
   *
   * @return The card's cost
   */
  public int getCost() { return cost; }

  /**
   * Gets the value of the card.
   *
   * @return The card's value
   */
  public int getValue() { return value; }

  /**
   * Gets the influence grid of the card.
   *
   * @return A 2D character array representing the card's influence pattern
   */
  public char[][] getInfluenceGrid() { return influenceGrid; }

  /**
   * Converts the card's attributes into a readable string format.
   *
   * @return A formatted string representing the card
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(" Cost: ").append(cost).append(" Value: ").append(value).append("\n");

    // Append each row of the influence grid to the string
    for (char[] row : influenceGrid) {
      sb.append(new String(row)).append("\n");
    }

    return sb.toString();
  }
}
