package model.cell;

import model.Deck.Card;

public class ScoringCell implements Cell {
  private int score;

  public ScoringCell(int score) {
    this.score = score;
  }

  @Override
  public String textualPrint() {
    return Integer.toString(this.score);
  }

  public void updateScoreCell(int newScore) {
    this.score = newScore;
  }
}
