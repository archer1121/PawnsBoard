import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.SimpleComputerPlayer;
import model.deck.Card;
import model.deck.DeckLoader;
import view.PawnsBoardSwingView;
import controller.GameController;
import controller.CompositeController;

import java.io.FileNotFoundException;
import java.util.List;

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
      List<Card> redDeck = DeckLoader.loadDeck(redDeckPath);
      List<Card> blueDeck = DeckLoader.loadDeck(blueDeckPath);

      // Create and initialize the model.
      PawnsBoardModel model = new PawnsBoardModel();
      model.initBoard(3, 5);

      // Create a single shared view.
      PawnsBoardSwingView view = new PawnsBoardSwingView(model);
      view.setTitle("Pawns Board Game");

      // Instantiate players based on the player type argument.
      PlayerModel redPlayer;
      if ("human".equalsIgnoreCase(redPlayerType)) {
        redPlayer = new PlayerModel(PlayerColor.RED);
      } else {
        redPlayer = new SimpleComputerPlayer(PlayerColor.RED);
      }
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

      // Create controllers for each player.
      GameController redController = new GameController(model, view, redPlayer);
      GameController blueController = new GameController(model, view, bluePlayer);

      // Use a composite controller to forward events to both controllers.
      CompositeController compositeController = new CompositeController(redController, blueController);
      view.setController(compositeController);

      // Start the game (notify listeners for the initial turn).
      model.startGame();

      view.setVisible(true);
    } catch (FileNotFoundException e) {
      System.err.println("Deck file not found: " + e.getMessage());
      System.exit(1);
    }
  }
}
