package view;

public interface ViewListener {
  void handleCardClick(int cardIndex);
  void handleCellClick(int row, int col);
  void handleKeyPress(String key);
}