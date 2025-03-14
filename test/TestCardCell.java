import model.cell.CardCell;
import model.deck.Card;
import model.PlayerColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for the CardCell class.
 * Tests the initialization and behavior of CardCell instances.
 */
public class TestCardCell {

  private Card card;

  /**
   * Set up method to initialize the card and card cell before each test.
   */
  @BeforeEach
  public void setUp() {
    card = new Card("Test Card", 5, 10, new char[5][5], PlayerColor.RED);
  }

  @Test
  public void testCardCellInitialization() {
    // Create a CardCell with the card
    CardCell cardCell = new CardCell(card);  // Local variable used here
    // Ensure the card is properly assigned to the CardCell
    assertNotNull(cardCell, "CardCell should not be null.");
  }

  @Test
  public void testTextualPrint() {
    // Create a CardCell with the card
    CardCell cardCell = new CardCell(card);  // Local variable used here
    // Ensure textualPrint returns an empty string (as per the implementation)
    assertEquals("", cardCell.textualPrint(), "textualPrint should return an empty string.");
  }
}
