package view;

import controller.GameController;
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
  private GameController controller; // Add controller reference

  /**
   * Our main view using java swing
   * @param model of game already init
   */
  public PawnsBoardSwingView(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setTitle("Pawns Board Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    BoardPanel boardPanel = new BoardPanel(this.model);
    add(boardPanel, BorderLayout.CENTER);

    // Add HandPanel to bottom
    HandPanel handPanel = new HandPanel(model);
    handPanel.setClickListener(new ViewListener() {
      @Override
      public void handleCardClick(int cardIndex) {
        if (controller != null) {
          controller.handleCardClick(cardIndex); // Delegate to controller
        }
      }
      @Override
      public void handleCellClick(int row, int col) {
        if (controller != null) {
          controller.handleCellClick(row, col); // Delegate to controller
        }
      }
      @Override
      public void handleKeyPress(String key) {
        if (controller != null) {
          controller.handleKeyPress(key); // Delegate to controller
        }
      }
    });
    add(handPanel, BorderLayout.SOUTH);

    // Mouse listener for cell clicks
    boardPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int cellSize = Math.min(getWidth() / model.getNumCols(), getHeight() / model.getNumRows());
        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;
        selectedCell = new Point(col, row);
        if (controller != null) {
          controller.handleCellClick(row, col); // Delegate to controller
        }
        repaint();
      }
    });

    // Keyboard listener for key events
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (controller != null) {
            controller.handleKeyPress("Confirm"); // Notify controller for confirmation
          }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          if (controller != null) {
            controller.handleKeyPress("Cancel"); // Notify controller for cancellation
          }
          selectedCardIndex = -1;
          selectedCell = null;
          repaint();
        }
      }
    });

    setFocusable(true);
    setVisible(true); // This ensures the window is displayed.
  }

  // Set the GameController to integrate it with the view
  public void setController(GameController controller) {
    this.controller = controller; // Store the controller reference
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
