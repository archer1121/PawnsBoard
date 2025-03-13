package model.deck;

import model.PlayerColor;

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
   */
  public static List<Card> loadDeck(String filePath) throws FileNotFoundException {
    List<Card> deck = new ArrayList<>(); // List to store loaded cards
    File file = new File(filePath);

    // Try-with-resources to ensure the scanner is closed automatically
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        // Skip blank lines
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
          continue;
        }

        // Read card metadata (name, cost, value)
        String[] cardInfo = line.split(" ");

        // Ensure the metadata is properly formatted (exactly 3 elements)
        if (cardInfo.length != 3) {
          continue;  // Skip this card due to invalid metadata
        }

        String name = cardInfo[0];                   // Card name
        int cost;
        int value;

        // Try parsing cost and value and skip the card if invalid
        try {
          cost = Integer.parseInt(cardInfo[1]);
          value = Integer.parseInt(cardInfo[2]);
        } catch (NumberFormatException e) {
          continue;  // Skip this card due to invalid cost or value
        }

        // Read the 5x5 influence grid
        char[][] grid = new char[5][5];
        boolean isValidGrid = true; // Flag to track grid validity

        for (int i = 0; i < 5; i++) {
          if (!scanner.hasNextLine()) {
            isValidGrid = false; // Mark as invalid if not enough grid rows
            break;
          }
          String row = scanner.nextLine().trim();

          // Ensure each row is exactly 5 characters long
          if (row.length() != 5) {
            isValidGrid = false; // Mark as invalid if grid row is too short
            break;
          }

          grid[i] = row.toCharArray();
        }

        // Validate the influence grid (ensure 'C' is at position (2,2))
        if (isValidGrid && grid[2][2] != 'C') {
          isValidGrid = false; // Mark as invalid if 'C' is not at (2,2)
        }

        // If the card is valid, add it to the deck
        if (isValidGrid) {
          deck.add(new Card(name, cost, value, grid, PlayerColor.RED));
        }
      }
    }

    return deck; // Return the fully loaded deck
  }
}
