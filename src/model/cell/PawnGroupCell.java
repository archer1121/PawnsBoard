package model.cell;

import model.PlayerColor;

/**
 * Represents a cell containing a group of pawns.
 * This cell keeps track of the number of pawns and their color.
 */
public class PawnGroupCell implements Cell {

  private int numPawns;
  private PlayerColor pawnColor;

  /**
   * Constructor to create a PawnGroupCell with a specified number of pawns and color.
   * @param numPawns the number of pawns in the group
   * @param pawnColor the color of the pawns in the group
   */
  public PawnGroupCell(int numPawns, PlayerColor pawnColor) {
    this.numPawns = numPawns;
    this.pawnColor = pawnColor;
  }

  @Override
  public String textualPrint() {
    return Integer.toString(this.numPawns);
  }

  /**
   * Adds pawns to the current group.
   * @param numToAdd the number of pawns to add
   */
  public void addPawn(int numToAdd) {
    this.numPawns += numToAdd;
  }

  /**
   * Removes pawns from the current group.
   * @param numToRemove the number of pawns to remove
   */
  public void removePawns(int numToRemove) {
    this.numPawns -= numToRemove;
  }

  /**
   * Returns the color of this pawn group.
   * @return PlayerColor
   */
  public PlayerColor getPawnColor() {
    return this.pawnColor;
  }

  /**
   * Converts enemy pawns to the current player's color.
   * @param newColor the new color to convert enemy pawns to
   */
  public void convertPawns(PlayerColor newColor) {
    if (this.pawnColor != newColor) {
      this.pawnColor = newColor;
    }
  }

  /**
   * Gets the number of pawns in this cell.
   * @return number of pawns
   */
  public int getNumPawns() {
    return this.numPawns;
  }
}
