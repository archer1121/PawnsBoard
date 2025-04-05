package model;

/**
 * Interface for receiving notifications about game status changes,
 * such as when the active player's turn changes or when the game is over.
 */
public interface ModelListener {
  /**
   * Called when the active player's turn changes.
   *
   * @param currentPlayerColor the color of the player whose turn it is.
   */
  void onTurnChanged(PlayerColor currentPlayerColor);

  /**
   * Called when the game is over.
   *
   * @param winner the color of the winning player.
   */
  void onGameOver(PlayerColor winner);
}

