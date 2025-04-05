package controller;

import view.ViewListener;

/**
 * The CompositeController class implements the ViewListener interface and combines
 * two controllers (controller1 and controller2). It delegates the handling of
 * view events (such as card clicks, cell clicks, and key presses) to both controllers.
 * This allows for a composite pattern, where multiple controllers can be used together
 * to handle view events.
 */
public class CompositeController implements ViewListener {
  private final ViewListener controller1;
  private final ViewListener controller2;

  /**
   * Constructs a CompositeController with two ViewListener controllers.
   *
   * @param controller1 the first controller to delegate to.
   * @param controller2 the second controller to delegate to.
   */
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
