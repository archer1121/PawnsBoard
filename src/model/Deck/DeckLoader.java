package model.Deck; // Declares that this class belongs to the model.Deck package

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for loading a deck of cards from a file.
 */
public class DeckLoader {

  /**
   * Loads a deck of cards from a given file path.
   *
   * @param filePath The path to the deck configuration file.
   * @return A list of Card objects loaded from the file.
   * @throws FileNotFoundException If the file cannot be found.
   * @throws IllegalArgumentException If the file contains improperly formatted data.
   */
  public static List<Card> loadDeck(String filePath) throws FileNotFoundException {
    List<Card> deck = new ArrayList<>(); // List to store loaded cards
    File file = new File(filePath);

    // Try-with-resources to ensure the scanner is closed automatically
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        // Read next line
        String line = scanner.nextLine().trim(); // Trim whitespace

        // Skip empty or blank lines
        if (line.isEmpty()) {
          continue;
        }

        // Read card metadata (name, cost, value)
        String[] cardInfo = line.split(" ");

        // Ensure the metadata is properly formatted (exactly 3 elements)
        if (cardInfo.length != 3) {
          throw new IllegalArgumentException("Invalid card metadata format.");
        }

        String name = cardInfo[0];                   // Card name
        int cost = Integer.parseInt(cardInfo[1]);    // Card cost
        int value = Integer.parseInt(cardInfo[2]);   // Card value

        // Read the 5x5 influence grid
        char[][] grid = new char[5][5];
        for (int i = 0; i < 5; i++) {
          if (!scanner.hasNextLine()) {
            throw new IllegalArgumentException("Incomplete grid for card: " + name);
          }
          String row = scanner.nextLine().trim();

          // Ensure each row is exactly 5 characters long
          if (row.length() != 5) {
            throw new IllegalArgumentException("Invalid grid row length.");
          }

          grid[i] = row.toCharArray();
        }

        // Validate the influence grid (ensure 'C' is at position (2,2))
        if (grid[2][2] != 'C') {
          throw new IllegalArgumentException("Card " + name + " is missing 'C' at (2,2).");
        }

        // Add the newly created card to the deck
        deck.add(new Card(name, cost, value, grid));
      }
    }

    return deck; // Return the fully loaded deck
  }
}
