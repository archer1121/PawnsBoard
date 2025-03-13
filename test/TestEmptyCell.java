import model.cell.EmptyCell;
import model.cell.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmptyCell {

  private EmptyCell emptyCell;

  @BeforeEach
  public void setUp() {
    emptyCell = new EmptyCell();  // Initialize the EmptyCell
  }

  @Test
  public void testEmptyCellInitialization() {
    // Ensure the EmptyCell is properly initialized
    assertNotNull(emptyCell, "EmptyCell should not be null.");
  }

  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns "_" as expected
    assertEquals("_", emptyCell.textualPrint(), "textualPrint should return '_' for EmptyCell.");
  }
}
