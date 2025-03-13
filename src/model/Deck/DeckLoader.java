package model.Deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeckLoader {
  public static List<Card> loadDeck(String filePath) throws FileNotFoundException {
    List<Card> deck = new ArrayList<>();
    File file = new File(filePath);

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        // Read card metadata
        String[] cardInfo = scanner.nextLine().split(" ");
        if (cardInfo.length != 3) {
          throw new IllegalArgumentException("Invalid card metadata format.");
        }

        String name = cardInfo[0];
        int cost = Integer.parseInt(cardInfo[1]);
        int value = Integer.parseInt(cardInfo[2]);

        // Read 5x5 influence grid
        char[][] grid = new char[5][5];
        for (int i = 0; i < 5; i++) {
          if (!scanner.hasNextLine()) throw new IllegalArgumentException("Incomplete grid for card: " + name);
          String row = scanner.nextLine();
          if (row.length() != 5) throw new IllegalArgumentException("Invalid grid row length.");
          grid[i] = row.toCharArray();
        }

        // Validate grid (check for 'C' at correct position)
        if (grid[2][2] != 'C') {
          throw new IllegalArgumentException("Card " + name + " is missing 'C' at (2,2).");
        }

        deck.add(new Card(name, cost, value, grid));
      }
    }
    return deck;
  }
}
