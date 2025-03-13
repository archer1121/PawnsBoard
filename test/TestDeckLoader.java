import model.Deck.Card;
import model.Deck.DeckLoader;

import java.io.FileNotFoundException;
import java.util.List;

public class TestDeckLoader {
  public static void main(String[] args) {
    try {
      List<Card> deck = DeckLoader.loadDeck("docs/deck.config");
      for (Card card : deck) {
        System.out.println(card);
      }
    } catch (FileNotFoundException e) {
      System.err.println("Config file not found: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.err.println("Error in config file: " + e.getMessage());
    }
  }
}
