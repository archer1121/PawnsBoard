package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.ReadonlyPawnsBoardModel;
import model.cell.CardCell;
import model.cell.Cell;
import model.cell.PawnGroupCell;
import model.cell.ScoringCell;

/**
 * Class to house our main panel which is the game board and nothing else.
 */
public class BoardPanel extends JPanel {
  private static final int CELL_SIZE = 60;
  private final ReadonlyPawnsBoardModel model;
  private ViewListener listener;
  private Point selectedCell = null;

  /**
   * Main panel of the game board with just the squares.
   * @param model of game already init
   */
  public BoardPanel(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setBackground(Color.WHITE);
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int col = e.getX() / CELL_SIZE;
        int row = e.getY() / CELL_SIZE;
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

  @Override protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    // Drawing implementation
    int numRows = model.getNumRows();
    int numCols = model.getNumCols();
    int cellSize = Math.min(getWidth() / numCols, getHeight() / numRows);

    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        int x = c * cellSize;
        int y = r * cellSize;
        Cell cell = model.getBoard()[r][c];

        //color based on cell
        if (cell instanceof ScoringCell) {
          g2d.setColor(Color.YELLOW);
        } else if (cell instanceof CardCell) {
          g2d.setColor(Color.GREEN);
        } else if (cell instanceof PawnGroupCell) {
          g2d.setColor(Color.ORANGE);
        } else {
          g2d.setColor(Color.LIGHT_GRAY);
        }
        g2d.fillRect(x, y, cellSize, cellSize);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, cellSize, cellSize);


        g2d.drawString(cell.toString(), x + cellSize / 4, y + cellSize / 2);
      }
    }
    //selection overlay
    if (selectedCell != null) {
      g2d.setColor(Color.CYAN);
      int x = selectedCell.x * cellSize;
      int y = selectedCell.y * cellSize;
      g2d.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
    }
  }

  @Override public Dimension getPreferredSize() {
    return new Dimension(model.getNumCols() * CELL_SIZE, model.getNumRows() * CELL_SIZE);
  }

  /**
   * Init viewlistener.
   * @param listener for click handling
   */
  public void setClickListener(ViewListener listener) {
    this.listener = listener;
  }
}