package model;

public class PlayerModel {
  private PlayerColor playerColor;
  private boolean isTurn;
  private int score;

  // Constructor
  public PlayerModel(PlayerColor playerColor) {
    this.playerColor = playerColor;
    this.isTurn = false;
    this.score = 0;
  }

  // Getters
  public PlayerColor getPlayerColor() {
    return playerColor;
  }

  public boolean isTurn() {
    return isTurn;
  }

  public int getScore() {
    return score;
  }

  // Setters
  public void setTurn(boolean isTurn) {
    this.isTurn = isTurn;
  }

  public void addScore(int points) {
    this.score += points;
  }

  // Reset score
  public void resetScore() {
    this.score = 0;
  }
}
