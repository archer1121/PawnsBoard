package model.cell;

public class PawnGroupCell implements Cell {

  private int numPawns;
  private PawnColor pawnColor;

  private enum PawnColor {RED, BLUE}

  public PawnGroupCell(int numPawns, PawnColor pawnColor) {
    this.numPawns = numPawns;
    this.pawnColor = pawnColor;
  }

  @Override
  public String textualPrint() {
  return Integer.toString(this.numPawns);
  }

  public void addPawn(int numToAdd) {
    this.numPawns += numToAdd;
  }
}
