import model.PawnsBoardModel;
import view.PawnsBoardSwingView;
import view.ViewListener;

/**
 * Class used to house our main method.
 */
public final class PawnsBoard {
  /**
   * Main class used as entry point for program.
   * @param args standard java
   */
  public static void main(String[] args) {
    PawnsBoardModel model = new PawnsBoardModel();
    model.initBoard(5, 5);

    PawnsBoardSwingView view = new PawnsBoardSwingView(model);

    view.addClickListener(new ViewListener() {
      @Override
      public void handleCardClick(int cardIndex) {
        System.out.println("Card " + cardIndex + " clicked");
      }

      @Override
      public void handleCellClick(int row, int col) {
        System.out.println("Cell clicked at (" + row + "," + col + ")");
      }

      @Override
      public void handleKeyPress(String key) {
        System.out.println("Key pressed: " + key);
      }
    });

    // No need for view.display(); since setVisible(true) is in the constructor.
  }
}
