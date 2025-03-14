import model.cell.ScoringCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class tests the behavior of the ScoringCell class, ensuring
 * that the score is correctly initialized, updated, and printed.
 */
public class TestScoringCell {

  private ScoringCell scoringCell;

  /**
   * Initializes the ScoringCell before each test.
   */
  @BeforeEach
  public void setUp() {
    // Initialize ScoringCell with a score of 5
    scoringCell = new ScoringCell(5);
  }

  /**
   * Tests the initialization of the ScoringCell.
   * Ensures that the scoring cell is not null after initialization.
   */
  @Test
  public void testScoringCellInitialization() {
    // Ensure the ScoringCell is properly initialized
    assertNotNull(scoringCell, "ScoringCell should not be null.");
  }

  /**
   * Tests the textual print of the ScoringCell.
   * Ensures that the textual representation returns the correct score.
   */
  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns the correct score as a string
    assertEquals("5", scoringCell.textualPrint(),
        "textualPrint should return the correct score as a string.");
  }

  /**
   * Tests the update of the score in the ScoringCell.
   * Ensures that after updating the score, the textual print reflects the new score.
   */
  @Test
  public void testUpdateScoreCell() {
    // Update the score and ensure it's correctly reflected
    scoringCell.updateScoreCell(10);
    assertEquals("10", scoringCell.textualPrint(),
        "After updating the score, textualPrint should reflect the new score.");
  }
}
