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

  /**
   * Our main view using java swing
   * @param model of game already init
   */
  public PawnsBoardSwingView(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setTitle("Pawns Board Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    BoardPanel boardPanel = new BoardPanel();
    add(boardPanel, BorderLayout.CENTER);

    //add to bottom
    HandPanel handPanel = new HandPanel(model);
    handPanel.setClickListener(new ViewListener() {
      @Override
      public void handleCardClick(int cardIndex) {
        System.out.println("Card " + cardIndex + " clicked");
      }
      @Override
      public void handleCellClick(int row, int col) {
        // handle
      }
      @Override
      public void handleKeyPress(String key) {
        System.out.println("Key pressed: " + key);
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

  private class BoardPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      int numRows = model.getNumRows();
      int numCols = model.getNumCols();
      int cellSize = Math.min(getWidth() / numCols, getHeight() / numRows);

      for (int r = 0; r < numRows; r++) {
        for (int c = 0; c < numCols; c++) {
          int x = c * cellSize;
          int y = r * cellSize;
          g2d.setColor(Color.LIGHT_GRAY);
          g2d.fillRect(x, y, cellSize, cellSize);
          g2d.setColor(Color.BLACK);
          g2d.drawRect(x, y, cellSize, cellSize);

          if (selectedCell != null && selectedCell.x == c && selectedCell.y == r) {
            g2d.setColor(Color.CYAN);
            g2d.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
          }
        }
      }
    }
  }
}
