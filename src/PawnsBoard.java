

import java.io.FileNotFoundException;
import java.util.List;

import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.SimpleComputerPlayer;
import model.deck.Card;
import model.deck.DeckLoader;
import view.PawnsBoardSwingView;
import controller.GameController;
import adapter.PawnsBoardModelAdapter;
import adapter.ProviderPlayerAdapter;

// Provider's view (after refactoring into your namespace)
import providerCode.view.PBFrame;

/**
 * The main entry point for the Pawns Board game.
 *
 * This class sets up the model, view, and controllers,
 * loads player decks, and initializes the game based on command-line arguments.
 *
 * Command-line arguments:
 *   args[0] - Path to the red player's deck file
 *   args[1] - Path to the blue player's deck file
 *   args[2] - Type of red player: "human" or "computer"
 *   args[3] - Type of blue player: "human" or "computer"
 */
public class PawnsBoard {
  public static void main(String[] args) {
    if (args.length < 4) {
      System.err.println("Usage: java -jar pawnsboard.jar <redDeckPath> <blueDeckPath> <redPlayerType> <bluePlayerType>");
      System.exit(1);
    }
    String redDeckPath = args[0];
    String blueDeckPath = args[1];
    String redPlayerType = args[2];
    String bluePlayerType = args[3];

    try {
      // Load decks from the provided file paths.
      // (This assumes your DeckLoader now supports an overload that accepts a PlayerColor.)
      List<Card> redDeck = DeckLoader.loadDeck(redDeckPath, PlayerColor.RED);
      List<Card> blueDeck = DeckLoader.loadDeck(blueDeckPath, PlayerColor.BLUE);

      // Create and initialize the model.
      PawnsBoardModel model = new PawnsBoardModel();
      model.initBoard(3, 5);

      // Instantiate Player 1 (for our view/controller).
      PlayerModel redPlayer;
      if ("human".equalsIgnoreCase(redPlayerType)) {
        redPlayer = new PlayerModel(PlayerColor.RED);
      } else {
        redPlayer = new SimpleComputerPlayer(PlayerColor.RED);
      }

      // Instantiate Player 2 (for the provider's view).
      PlayerModel bluePlayer;
      if ("human".equalsIgnoreCase(bluePlayerType)) {
        bluePlayer = new PlayerModel(PlayerColor.BLUE);
      } else {
        bluePlayer = new SimpleComputerPlayer(PlayerColor.BLUE);
      }

      // Add deck cards to each player's hand.
      for (Card card : redDeck) {
        redPlayer.addCardToHand(card);
      }
      for (Card card : blueDeck) {
        bluePlayer.addCardToHand(card);
      }

      // --- Set up our view and controller for Player 1 ---
      PawnsBoardSwingView viewP1 = new PawnsBoardSwingView(model);
      viewP1.setTitle("Player 1 (Our View)");
      GameController controllerP1 = new GameController(model, viewP1, redPlayer);
      viewP1.setController(controllerP1);

      // --- Set up the provider’s view for Player 2 ---
      // Create an adapter for our model that exposes hands using our players.
      PawnsBoardModelAdapter modelAdapter = new PawnsBoardModelAdapter(model, redPlayer, bluePlayer);
      // Wrap bluePlayer in a provider-compatible adapter.
      ProviderPlayerAdapter bluePlayerAdapter = new ProviderPlayerAdapter(bluePlayer);
      // Instantiate the provider's view.
      PBFrame viewP2 = new PBFrame(modelAdapter, bluePlayerAdapter);
      viewP2.setTitle("Player 2 (Provider's View)");
      // Optionally adjust location so the windows don't overlap.
      viewP2.setLocation(viewP1.getX() + viewP1.getWidth() + 20, viewP1.getY());

      // --- Display both views ---
      viewP1.setVisible(true);
      viewP2.setVisible(true);

      // Alert the provider's view that it is Player 2’s turn.
      bluePlayerAdapter.alertTurnChange(viewP2);

      // Start the game (this should notify listeners for turn changes, etc.).
      model.startGame();
    } catch (FileNotFoundException e) {
      System.err.println("Deck file not found: " + e.getMessage());
      System.exit(1);
    }
  }
}
