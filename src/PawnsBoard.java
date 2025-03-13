import model.PawnsBoardModel;
import model.Deck.DeckLoader;
import model.Deck.Card;
import view.PawnsBoardTextualView;

import java.util.List;

public class PawnsBoard {
  public static void main(String[] args) {
    // Step 1: Load deck configuration from file
    String deckFilePath = "docs/valid_deck.config";  // Update this path if needed
    List<Card> deck;

    try {
      deck = DeckLoader.loadDeck(deckFilePath);
    } catch (Exception e) {
      System.err.println("Error loading deck: " + e.getMessage());
      return;
    }

    // Step 2: Initialize the game model with a 3x5 board
    PawnsBoardModel model = new PawnsBoardModel();
    model.initBoard(3, 5);  // 3 rows, 5 columns

    // Step 3: Set up players (both players use the same deck)
    int handSize = Math.min(5, deck.size() / 2);
    List<Card> player1Hand = deck.subList(0, handSize);
    List<Card> player2Hand = deck.subList(handSize, handSize * 2);

    // Step 4: Initialize the textual view
    PawnsBoardTextualView view = new PawnsBoardTextualView();

    // Step 5: Game Loop
    boolean gameOver = false;
    boolean player1Turn = true;

    while (!gameOver) {
      System.out.println(view.textualPrint(model)); // Print the board state

      if (player1Turn) {
        if (!player1Hand.isEmpty()) {
          // Place a card if possible (this logic should be more refined)
          Card card = player1Hand.remove(0);
          model.placeCard(card, 1, 2);  // Example placement
        } else {
          gameOver = true;
        }
      } else {
        if (!player2Hand.isEmpty()) {
          Card card = player2Hand.remove(0);
          model.placeCard(card, 2, 3);  // Example placement
        } else {
          gameOver = true;
        }
      }

      player1Turn = !player1Turn;  // Switch turns
    }

    System.out.println("Game Over!");
  }
}
