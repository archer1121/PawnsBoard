package view;

import model.ReadonlyPawnsBoardModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Graphical view for the PawnsBoard game.
 */
public class PawnsBoardSwingView extends JFrame {
  private final ReadonlyPawnsBoardModel model;
  private final List<ViewListener> listeners = new ArrayList<>();
  private int selectedCardIndex = -1;
  private Point selectedCell = null;

  public PawnsBoardSwingView(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setTitle("Pawns Board Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    // Create the panels
    BoardPanel boardPanel = new BoardPanel(model);
    HandPanel handPanel = new HandPanel(model);

    // Set up the layout manager
    setLayout(new BorderLayout());

    // Add the board and hand panels to the frame
    add(boardPanel, BorderLayout.CENTER);  // Center for the board
    add(handPanel, BorderLayout.SOUTH);    // Bottom for the hand panel

    // Mouse listener for cell clicks in the board
    boardPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int cellSize = Math.min(getWidth() / model.getNumCols(), getHeight() / model.getNumRows());
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        selectedCell = new Point(col, row);
        notifyCellClick(row, col);
        repaint();
      }
    });

    // Keyboard listener for key events
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          notifyKeyPress("Confirm");
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          notifyKeyPress("Cancel");
          selectedCardIndex = -1;
          selectedCell = null;
          repaint();
        }
      }
    });

    setFocusable(true);
    setVisible(true); // This ensures the window is displayed.
  }

  /**
   * Registers a listener for view events.
   */
  public void addClickListener(ViewListener listener) {
    listeners.add(listener);
  }

  private void notifyCellClick(int row, int col) {
    for (ViewListener listener : listeners) {
      listener.handleCellClick(row, col);
    }
  }

  private void notifyKeyPress(String key) {
    for (ViewListener listener : listeners) {
      listener.handleKeyPress(key);
    }
  }
}
