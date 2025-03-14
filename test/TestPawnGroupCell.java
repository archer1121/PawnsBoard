import model.cell.PawnGroupCell;
import model.PlayerColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class tests the behavior of the PawnGroupCell class.
 */
public class TestPawnGroupCell {

  private PawnGroupCell pawnGroupCell;

  /**
   * Sets up the test environment before each test.
   * Initializes a PawnGroupCell instance with 3 pawns and a red player color.
   */
  @BeforeEach
  public void setUp() {
    pawnGroupCell = new PawnGroupCell(3, PlayerColor.RED);
  }

  /**
   * Tests that the PawnGroupCell is properly initialized.
   */
  @Test
  public void testPawnGroupCellInitialization() {
    // Ensure the PawnGroupCell is properly initialized
    assertNotNull(pawnGroupCell, "PawnGroupCell should not be null.");
  }

  /**
   * Tests that the textualPrint method returns the correct number of pawns as a string.
   */
  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns the correct number of pawns as a string
    assertEquals("3", pawnGroupCell.textualPrint(), "textualPrint should return the correct number of pawns as a string.");
  }

  /**
   * Tests that the addPawn method correctly increases the number of pawns.
   */
  @Test
  public void testAddPawn() {
    // Add pawns to the group and verify the number of pawns increases correctly
    pawnGroupCell.addPawn(2);
    assertEquals("5", pawnGroupCell.textualPrint(), "After adding pawns, textualPrint should reflect the new number of pawns.");
  }
}
