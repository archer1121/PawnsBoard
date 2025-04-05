package controller;

import view.ViewListener;

public class CompositeController implements ViewListener {
  private final ViewListener controller1;
  private final ViewListener controller2;

  public CompositeController(ViewListener controller1, ViewListener controller2) {
    this.controller1 = controller1;
    this.controller2 = controller2;
  }

  @Override
  public void handleCardClick(int cardIndex) {
    controller1.handleCardClick(cardIndex);
    controller2.handleCardClick(cardIndex);
  }

  @Override
  public void handleCellClick(int row, int col) {
    controller1.handleCellClick(row, col);
    controller2.handleCellClick(row, col);
  }

  @Override
  public void handleKeyPress(String key) {
    controller1.handleKeyPress(key);
    controller2.handleKeyPress(key);
  }
}
