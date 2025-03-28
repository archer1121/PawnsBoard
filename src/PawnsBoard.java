import model.PawnsBoardModel;
import controller.GameController;
import view.PawnsBoardSwingView;

//TODO: Finish HandPanel and add functionality to controller

public class PawnsBoard {

  public static void main(String[] args) {
    // Create the model and view
    PawnsBoardModel model = new PawnsBoardModel();

    model.initBoard(3,5);

    PawnsBoardSwingView view = new PawnsBoardSwingView(model);

    // Create the game controller and pass the model and view
    GameController controller = new GameController(model, view);

    // Start the game
    view.setController(controller);
    view.setVisible(true);
  }
}
