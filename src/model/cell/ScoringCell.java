package model.cell;

/**
 * Represents a cell used for scoring in the game.
 * This cell holds an integer score and provides a way to update the score.
 */
public class ScoringCell implements Cell {
  private int score;

  /**
   * Constructor to create a ScoringCell with a specified score.
   * @param score the initial score for the cell
   */
  public ScoringCell(int score) {
    this.score = score;
  }

  @Override
  public String textualPrint() {
    return Integer.toString(this.score);
  }

  /**
   * Updates the score of the cell.
   * @param newScore the new score value
   */
  public void updateScoreCell(int newScore) {
    this.score = newScore;
  }

  /**
   * Gets the current score.
   * @return current score value
   */
  public int getScore() {
    return score;
  }
}
