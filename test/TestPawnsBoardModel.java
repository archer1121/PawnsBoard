import model.PawnsBoardModel;
import model.deck.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPawnsBoardModel {

  private PawnsBoardModel board;

  @BeforeEach
  public void setUp() {
    // Initialize board before each test
    board = new PawnsBoardModel();
  }


  @Test
  public void testBoardInitializationWithDifferentSizes() {
    // Test board initialization with different sizes
    board.initBoard(5, 3);

    // The board should have 5 rows and 3 playable columns, plus 2 scoring columns
    assertEquals(5, board.getNumRows(), "Board should have 5 rows");
    assertEquals(5, board.getNumCols(), "Board should have 5 columns (3 playable + 2 for scoring)");
  }

}
