import model.PawnsBoardModel;
import model.PlayerColor;
import model.PlayerModel;
import model.SimpleComputerPlayer;
import view.PawnsBoardSwingView;
import controller.GameController;
import controller.CompositeController;

public class PawnsBoard {

  public static void main(String[] args) {
    // Create and initialize the model
    PawnsBoardModel model = new PawnsBoardModel();
    model.initBoard(3, 5);

    // Create a single view (one window)
    PawnsBoardSwingView view = new PawnsBoardSwingView(model);
    view.setTitle("Pawns Board Game");

    // Create two players: one human (Red) and one computer (Blue)
    PlayerModel redPlayer = new PlayerModel(PlayerColor.RED);
    SimpleComputerPlayer bluePlayer = new SimpleComputerPlayer(PlayerColor.BLUE);

    // Create controllers for each player, each with access to the shared view
    GameController redController = new GameController(model, view, redPlayer);
    GameController blueController = new GameController(model, view, bluePlayer);

    // Create a composite controller that forwards view events to both controllers
    CompositeController compositeController = new CompositeController(redController, blueController);

    // Set the composite controller as the view's controller (which is a ViewListener)
    view.setController(compositeController);

    view.setVisible(true);
  }
}
