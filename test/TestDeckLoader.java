// Importing necessary classes
import model.Deck.Card;        // Importing the Card class from the model.Deck package
import model.Deck.DeckLoader;  // Importing the DeckLoader class from the model.Deck package

import java.io.FileNotFoundException; // Importing exception handling for file operations
import java.util.List;                // Importing List for storing a collection of cards

/**
 * This class is used to test the DeckLoader functionality.
 * It attempts to load a deck of cards from a configuration file
 * and prints the loaded cards to the console.
 */
public class TestDeckLoader {

  /**
   * The main method that executes the test.
   * It loads a deck of cards from the specified file and prints them.
   *
   * @param args Command-line arguments (not used in this program)
   */
  public static void main(String[] args) {
    try {
      // Load the deck of cards from the given configuration file
      List<Card> deck = DeckLoader.loadDeck("docs/deck.config");

      // Iterate through the loaded deck and print each card
      for (Card card : deck) {
        System.out.println(card);
      }

    } catch (FileNotFoundException e) {
      // Handles the case where the configuration file is not found
      System.err.println("Config file not found: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      // Handles errors in the configuration file format or content
      System.err.println("Error in config file: " + e.getMessage());
    }
  }
}
