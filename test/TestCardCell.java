import model.cell.CardCell;
import model.Deck.Card;
import model.PlayerColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCardCell {

  private CardCell cardCell;
  private Card card;

  @BeforeEach
  public void setUp() {
    card = new Card("Test Card", 5, 10, new char[5][5], PlayerColor.RED);  // Initialize a simple card
    cardCell = new CardCell(card);  // Create a CardCell with the card
  }

  @Test
  public void testCardCellInitialization() {
    // Ensure the card is properly assigned to the CardCell
    assertNotNull(cardCell, "CardCell should not be null.");
  }

  @Test
  public void testTextualPrint() {
    // Ensure textualPrint returns an empty string (as per the implementation)
    assertEquals("", cardCell.textualPrint(), "textualPrint should return an empty string.");
  }
}
