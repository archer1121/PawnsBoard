package view;

import controller.GameController;
import model.ReadonlyPawnsBoardModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PawnsBoardSwingView extends JFrame {
  private final ReadonlyPawnsBoardModel model;
  private final List<ViewListener> listeners = new ArrayList<>();
  private ViewListener controller; // Now a generic view listener
  private JLabel statusLabel; // Label to display status messages

  public PawnsBoardSwingView(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setTitle("Pawns Board Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    // Create a status label at the top
    statusLabel = new JLabel("Welcome to Pawns Board Game");
    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(statusLabel, BorderLayout.NORTH);

    BoardPanel boardPanel = new BoardPanel(this.model);
    add(boardPanel, BorderLayout.CENTER);

    // Add HandPanel to bottom
    HandPanel handPanel = new HandPanel(model);
    handPanel.setClickListener(new ViewListener() {
      @Override
      public void handleCardClick(int cardIndex) {
        if (controller != null) {
          controller.handleCardClick(cardIndex);
        }
      }
      @Override
      public void handleCellClick(int row, int col) {
        if (controller != null) {
          controller.handleCellClick(row, col);
        }
      }
      @Override
      public void handleKeyPress(String key) {
        if (controller != null) {
          controller.handleKeyPress(key);
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
        if (controller != null) {
          controller.handleCellClick(row, col);
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
            controller.handleKeyPress("Confirm");
          }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          if (controller != null) {
            controller.handleKeyPress("Cancel");
          }
          repaint();
        }
      }
    });

    setFocusable(true);
    setVisible(true);
  }

  public void setController(ViewListener controller) {
    this.controller = controller;
  }

  // New method to update the status label
  public void updateStatus(String message) {
    statusLabel.setText(message);
  }

  public void addClickListener(ViewListener listener) {
    listeners.add(listener);
  }
}
