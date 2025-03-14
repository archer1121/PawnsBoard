import model.cell.EmptyCell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class tests the behavior of the EmptyCell class.
 */
public class TestEmptyCell {

  private EmptyCell emptyCell;

  /**
   * Sets up the test environment before each test.
   * Initializes an EmptyCell instance.
   */
  @BeforeEach
  public void setUp() {
    emptyCell = new EmptyCell();  // Initialize the EmptyCell
  }

  /**
   * Tests that the EmptyCell is properly initialized.
   */
  @Test
  public void testEmptyCellInitialization() {
    // Ensure the EmptyCell is properly initialized
    assertNotNull(emptyCell, "EmptyCell should not be null.");
  }

  /**
   * Tests that the textualPrint method of EmptyCell returns the expected value.
   */
  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns "_" as expected
    assertEquals("_", emptyCell.textualPrint(), "textualPrint should return '_' for EmptyCell.");
  }
}
