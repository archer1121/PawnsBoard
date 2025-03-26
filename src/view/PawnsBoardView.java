package view;

import model.ReadonlyPawnsBoardModel;

/**
 * Interface to handle our view and necessary methods
 */
public interface PawnsBoardView {
  /**
   * Display board.
   */
  void display();

  /**
   * Refresh and repaint.
   */
  void refresh();

  /**
   * Init click listener
   * @param listener subclass for clicks
   */
  void addClickListener(ViewListener listener);

  /**
   * To handle key presses
   * @param listener subclass for key listening
   */
  void addKeyListener(ViewListener listener);
}