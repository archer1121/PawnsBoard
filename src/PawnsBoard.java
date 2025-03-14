import model.PawnsBoardModel;
import model.deck.DeckLoader;
import model.deck.Card;
import view.PawnsBoardTextualView;

import java.util.ArrayList;
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
    List<Card> player1Hand = new ArrayList<>(deck.subList(0, handSize));
    List<Card> player2Hand = new ArrayList<>(deck.subList(handSize, handSize * 2));


    // Step 4: Initialize the textual view
    PawnsBoardTextualView view = new PawnsBoardTextualView();

    // Step 5: Game Loop
    boolean gameOver = false;
    boolean player1Turn = true;

    if (!deck.isEmpty()) {
      model.placeCard(deck.get(0), 0, 2);  // Player 1 places card
      System.out.println("\nAfter Player 1's Move:");
      model.scoreTheBoard();
      System.out.println(view.textualPrint(model));
    }
    if (deck.size() > 1) {
      model.placeCard(deck.get(1), 2, 3);  // Player 2 places card
      System.out.println("\nAfter Player 2's Move:");
      model.scoreTheBoard();
      System.out.println(view.textualPrint(model));
    }

    if (deck.size() > 2) {
      model.placeCard(deck.get(2), 0, 1);  // Player 1 places another card
      System.out.println("\nAfter Player 1's Second Move:");
      model.scoreTheBoard();
      System.out.println(view.textualPrint(model));
    }

    // End the game after a few moves
    System.out.println("\nGame Over!");

  }
}