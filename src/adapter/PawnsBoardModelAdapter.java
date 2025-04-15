package adapter;

import java.util.List;
import providerCode.model.card.ICard;
import providerCode.model.cell.ICell;
import providerCode.model.pawnsboard.IPawnsBoardReadOnly;
import providerCode.model.pawnsboard.PlayerColor;
import model.PawnsBoardModel;
import model.PlayerModel;

public class PawnsBoardModelAdapter implements IPawnsBoardReadOnly {
  private final PawnsBoardModel model;
  private final PlayerModel redPlayer;
  private final PlayerModel bluePlayer;

  public PawnsBoardModelAdapter(PawnsBoardModel model, PlayerModel redPlayer, PlayerModel bluePlayer) {
    this.model = model;
    this.redPlayer = redPlayer;
    this.bluePlayer = bluePlayer;
  }

  @Override
  public int getWidth() {
    return model.getNumCols();
  }

  @Override
  public int getHeight() {
    return model.getNumRows();
  }

  @Override
  public boolean isGameOver() {
    // Stub implementation; update with your game logic if available.
    return false;
  }

  @Override
  public List<ICard> getHand(PlayerColor playerColor) {
    if (playerColor == PlayerColor.RED) {
      // Cast is needed if your Card type implements the provider's ICard.
      return (List<ICard>)(Object) redPlayer.getHand();
    } else {
      return (List<ICard>)(Object) bluePlayer.getHand();
    }
  }

  @Override
  public model.PlayerColor getTurn() {
    return model.getCurrentPlayerColor();
  }

  @Override
  public ICell getCell(int row, int col) {
    return (ICell) model.getBoard()[row][col];
  }

  @Override
  public int getPlayerScore(PlayerColor playerColor) {
    // Stub implementation; update if your model tracks score.
    return 0;
  }

  @Override
  public int getRemainingDeckSize(PlayerColor playerColor) {
    // Stub implementation.
    return 0;
  }

  @Override
  public int getPlayerRowScore(int row, PlayerColor playerColor) {
    // Stub implementation.
    return 0;
  }

  @Override
  public PlayerColor getWinner() {
    // Stub implementation.
    return null;
  }

  @Override
  public ICell[][] getBoard() {
    return (ICell[][]) model.getBoard();
  }

  @Override
  public List<ICard>[] getAllHands() {
    // Retrieve the hands from your players.
    List<ICard> redHand = (List<ICard>)(Object) redPlayer.getHand();
    List<ICard> blueHand = (List<ICard>)(Object) bluePlayer.getHand();
    // Create an array with red player at index 0 and blue player at index 1.
    @SuppressWarnings("unchecked")
    List<ICard>[] hands = new List[2];
    hands[0] = redHand;
    hands[1] = blueHand;
    return hands;
  }

  @Override
  public String getCurrentPlayer() {
    return model.getCurrentPlayerColor().toString();
  }
}
