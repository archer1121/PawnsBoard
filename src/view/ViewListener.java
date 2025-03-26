package view;

/**
 * Used to implement user actions and change model state.
 */
public interface ViewListener {
  /**
   * Handle card click area.
   * @param cardIndex of card in hand
   */
  void handleCardClick(int cardIndex);

  /**
   * Handle when a cell is clicked on.
   * @param row of card in board starting at 0
   * @param col of card in board starting at 1
   */
  void handleCellClick(int row, int col);

  /**
   * Handle when a key is pressed to update model.
   * @param key on keyboard
   */
  void handleKeyPress(String key);
}