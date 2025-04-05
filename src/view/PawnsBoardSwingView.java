package view;

import model.ReadonlyPawnsBoardModel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The PawnsBoardSwingView class renders the game board and hand panel using Swing.
 * It listens for user interactions and delegates them to a ViewListener (usually a controller).
 */
public class PawnsBoardSwingView extends JFrame {
  @SuppressWarnings("checkstyle:SingularField")
  private final JLabel statusLabel; // Label to display status messages

  private final ReadonlyPawnsBoardModel model;
  private ViewListener controller; // View listener for input events

  /**
   * Constructs a Swing-based view for the Pawns board game using the given model.
   *
   * @param model the read-only model representing the game state
   */
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

  /**
   * Assigns the controller (listener) that will handle UI events.
   *
   * @param controller the ViewListener to handle input events
   */
  public void setController(ViewListener controller) {
    this.controller = controller;
  }

  /**
   * Updates the status label with a new message.
   *
   * @param message the message to display
   */
  public void updateStatus(String message) {
    statusLabel.setText(message);
  }
}
