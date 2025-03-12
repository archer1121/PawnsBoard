package model.cell;

/**
 * Each slot on the board is a cell. A cell can be either a ScoringCell, a CardCell,
 * or an EmptyCell.
 */
public interface Cell {

  public String textualPrint();
}
