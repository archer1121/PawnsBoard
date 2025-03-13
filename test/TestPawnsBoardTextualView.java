import model.PawnsBoardModel;
import model.cell.ScoringCell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.PawnsBoardTextualView;

import static org.junit.jupiter.api.Assertions.*;

public class TestPawnsBoardTextualView {

  private PawnsBoardModel board;
  private PawnsBoardTextualView view;

  @BeforeEach
  public void setUp() {
    // Initialize board and view before each test
    board = new PawnsBoardModel();
    view = new PawnsBoardTextualView();
  }

  @Test
  public void testTextualPrint() {
    // Initialize the board with 3 rows and 5 playable columns
    board.initBoard(3, 5);

    // The expected textual output
    String expectedOutput = "[s] _ _ _ _ _ [s]\n" +
        "[s] _ _ _ _ _ [s]\n" +
        "[s] _ _ _ _ _ [s]\n";

    // Get the actual output from the view's textualPrint method
    String actualOutput = view.textualPrint(board);

    // Assert that the expected output matches the actual output
    assertEquals(expectedOutput, actualOutput, "Textual print of the board should match the expected output.");
  }

  @Test
  public void testTextualPrintWithDifferentBoardSize() {
    // Initialize the board with 4 rows and 6 playable columns
    board.initBoard(4, 6);

    // The expected textual output
    String expectedOutput = "[s] _ _ _ _ _ _ [s]\n" +
        "[s] _ _ _ _ _ _ [s]\n" +
        "[s] _ _ _ _ _ _ [s]\n" +
        "[s] _ _ _ _ _ _ [s]\n";

    // Get the actual output from the view's textualPrint method
    String actualOutput = view.textualPrint(board);

    // Assert that the expected output matches the actual output
    assertEquals(expectedOutput, actualOutput, "Textual print of the board with different sizes should match the expected output.");
  }

  @Test
  public void testTextualPrintEmptyBoard() {
    // Initialize the board with 0 rows and 0 columns (edge case)
    board.initBoard(0, 0);

    // The expected textual output for an empty board
    String expectedOutput = "";

    // Get the actual output from the view's textualPrint method
    String actualOutput = view.textualPrint(board);

    // Assert that the expected output matches the actual output
    assertEquals(expectedOutput, actualOutput, "Textual print of an empty board should be an empty string.");
  }

  @Test
  public void testTextualPrintWithScoringCells() {
    // Initialize the board with 3 rows and 5 playable columns
    board.initBoard(3, 5);

    // Set a ScoringCell with a score (modifying the cells in the scoring columns)
    board.getBoard()[0][0] = new ScoringCell(10);  // Set score at first column
    board.getBoard()[2][6] = new ScoringCell(20);  // Set score at last column

    // The expected textual output
    String expectedOutput = "[s] 10 _ _ _ _  [s]\n" +
        "[s] _ _ _ _ _ [s]\n" +
        "[s] 20 _ _ _ _ [s]\n";

    // Get the actual output from the view's textualPrint method
    String actualOutput = view.textualPrint(board);

    // Assert that the expected output matches the actual output
    assertEquals(expectedOutput, actualOutput, "Textual print of the board with ScoringCells should match the expected output.");
  }
}
