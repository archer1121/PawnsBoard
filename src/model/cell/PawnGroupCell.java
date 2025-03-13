package model.cell;
import model.PlayerColor;

public class PawnGroupCell implements Cell {

  private int numPawns;
  private PlayerColor pawnColor;



  public PawnGroupCell(int numPawns, PlayerColor pawnColor) {
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
