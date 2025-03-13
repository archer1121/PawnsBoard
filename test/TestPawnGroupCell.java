import model.cell.PawnGroupCell;
import model.PlayerColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPawnGroupCell {

  private PawnGroupCell pawnGroupCell;

  @BeforeEach
  public void setUp() {
    // Initialize PawnGroupCell with 3 pawns and a red player color
    pawnGroupCell = new PawnGroupCell(3, PlayerColor.RED);
  }

  @Test
  public void testPawnGroupCellInitialization() {
    // Ensure the PawnGroupCell is properly initialized
    assertNotNull(pawnGroupCell, "PawnGroupCell should not be null.");
  }

  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns the correct number of pawns as a string
    assertEquals("3", pawnGroupCell.textualPrint(), "textualPrint should return the correct number of pawns as a string.");
  }

  @Test
  public void testAddPawn() {
    // Add pawns to the group and verify the number of pawns increases correctly
    pawnGroupCell.addPawn(2);
    assertEquals("5", pawnGroupCell.textualPrint(), "After adding pawns, textualPrint should reflect the new number of pawns.");
  }
}
