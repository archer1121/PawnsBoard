import org.junit.jupiter.api.Test;

import model.deck.DeckLoader;
import model.deck.Card;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TestDeckLoader {

  // 1. Test for valid deck
  @Test
  void testValidDeck() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/valid_deck.config");
      assertEquals(3, deck.size(), "Loaded deck should have 3 cards.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  @Test
  void testValidDeckContent() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/valid_deck.config");
      Card card = deck.get(0);
      assertEquals("Fireball", card.getName());
      assertEquals(3, card.getCost());
      assertEquals(5, card.getValue());
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  // 2. Test for invalid metadata (missing cost or value)
  @Test
  void testInvalidMetadata() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/invalid_metadata.config");
      assertEquals(1, deck.size(), "Only valid cards should be loaded.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  // 3. Test for missing 'C' in grid
  @Test
  void testMissingCInGrid() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/missing_C_in_grid.config");
      assertTrue(deck.isEmpty(), "Deck should be empty due to missing 'C' in the grid.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  @Test
  void testMissingCInGridSkipping() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/missing_C_in_grid.config");
      assertEquals(0, deck.size(), "Deck should not contain cards due to missing 'C' at (2,2).");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  // 4. Test for incomplete grid
  @Test
  void testIncompleteGrid() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/incomplete_grid.config");
      assertEquals(0, deck.size(), "Deck should have 1 valid card.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  @Test
  void testIncompleteGridSkipping() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/incomplete_grid.config");
      assertTrue(deck.isEmpty(), "Incomplete cards should be skipped.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  // 5. Test for empty deck
  @Test
  void testEmptyDeck() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/empty_deck.config");
      assertTrue(deck.isEmpty(), "Deck should be empty.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }

  @Test
  void testEmptyDeckSkipping() {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/empty_deck.config");
      assertEquals(0, deck.size(), "Deck should have no cards.");
    } catch (FileNotFoundException e) {
      fail("File not found: " + e.getMessage());
    }
  }
}
