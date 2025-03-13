package model.Deck;

public class Card {
  private final String name;
  private final int cost;
  private final int value;
  private final char[][] influenceGrid;

  public Card(String name, int cost, int value, char[][] influenceGrid) {
    this.name = name;
    this.cost = cost;
    this.value = value;
    this.influenceGrid = influenceGrid;
  }

  public String getName() { return name; }
  public int getCost() { return cost; }
  public int getValue() { return value; }
  public char[][] getInfluenceGrid() { return influenceGrid; }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(" Cost: ").append(cost).append(" Value: ").append(value).append("\n");
    for (char[] row : influenceGrid) {
      sb.append(new String(row)).append("\n");
    }
    return sb.toString();
  }
}
