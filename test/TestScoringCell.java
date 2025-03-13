import model.cell.ScoringCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestScoringCell {

  private ScoringCell scoringCell;

  @BeforeEach
  public void setUp() {
    // Initialize ScoringCell with a score of 5
    scoringCell = new ScoringCell(5);
  }

  @Test
  public void testScoringCellInitialization() {
    // Ensure the ScoringCell is properly initialized
    assertNotNull(scoringCell, "ScoringCell should not be null.");
  }

  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns the correct score as a string
    assertEquals("5", scoringCell.textualPrint(), "textualPrint should return the correct score as a string.");
  }

  @Test
  public void testUpdateScoreCell() {
    // Update the score and ensure it's correctly reflected
    scoringCell.updateScoreCell(10);
    assertEquals("10", scoringCell.textualPrint(), "After updating the score, textualPrint should reflect the new score.");
  }
}
