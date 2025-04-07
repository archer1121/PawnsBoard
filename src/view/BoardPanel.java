package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.ReadonlyPawnsBoardModel;
import model.cell.CardCell;
import model.cell.Cell;
import model.cell.ScoringCell;

/**
 * A panel representing the game board in the Pawns game GUI.
 * It draws the cells, handles mouse clicks, and displays the current board state.
 */
public class BoardPanel extends JPanel {
  private static final int CELL_SIZE = 60;
  private final ReadonlyPawnsBoardModel model;
  private ViewListener listener;
  private Point selectedCell = null;

  /**
   * Constructs a BoardPanel using a read-only game model.
   *
   * @param model the read-only model representing the board state
   */
  public BoardPanel(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setBackground(Color.WHITE);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int row = e.getX() / CELL_SIZE;
        int col = e.getY() / CELL_SIZE;
        if (row >= 0 && row < model.getNumRows() && col >= 0 && col < model.getNumCols()) {
          if (selectedCell != null && selectedCell.x == row && selectedCell.y == col) {
            selectedCell = null;
          } else {
            selectedCell = new Point(row, col);
          }
          if (listener != null) {
            listener.handleCellClick(row, col);
          }
          repaint();
        }
      }
    });
  }

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
        Cell cell = model.getBoard()[r][c];

        String cellString = "";

        // color based on cell
        if (cell instanceof ScoringCell) {
          g2d.setColor(Color.WHITE);
          cellString = cell.textualPrint();
        } else if (cell instanceof CardCell) {
          g2d.setColor(Color.GREEN);
        } else {
          g2d.setColor(Color.LIGHT_GRAY);
        }
        g2d.fillRect(x, y, cellSize, cellSize);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, cellSize, cellSize);

        // Draw player color circles on left and right sections
        if (c == 1) {
          g2d.setColor(Color.RED);
          g2d.fillOval(x + (cellSize / 4), y + (cellSize / 4), cellSize / 2, cellSize / 2);
        } else if (c == numCols - 2) {
          g2d.setColor(Color.BLUE);
          g2d.fillOval(x + (cellSize / 4), y + (cellSize / 4), cellSize / 2, cellSize / 2);
        }

        // Draw cell text (e.g., score)
        if (!cellString.equals("")) {
          Font font = new Font("Arial", Font.BOLD, 30);
          g2d.setFont(font);

          FontMetrics fontMetrics = g2d.getFontMetrics();
          int textWidth = fontMetrics.stringWidth(cellString);
          int textHeight = fontMetrics.getHeight();

          int xPos = x + (cellSize - textWidth) / 2;
          int yPos = y + (cellSize + textHeight) / 2 - fontMetrics.getDescent();

          g2d.drawString(cellString, xPos, yPos);
        }
      }
    }

    // selection overlay
    if (selectedCell != null) {
      g2d.setColor(Color.CYAN);
      int x = selectedCell.x * cellSize;
      int y = selectedCell.y * cellSize;
      g2d.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(model.getNumCols() * CELL_SIZE, model.getNumRows() * CELL_SIZE);
  }

  /**
   * Sets the ViewListener that will handle cell click events.
   *
   * @param listener the listener to set
   */
  public void setClickListener(ViewListener listener) {
    this.listener = listener;
  }
}
