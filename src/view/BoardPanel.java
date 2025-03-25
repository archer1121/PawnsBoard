package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.ReadonlyPawnsBoardModel;

public class BoardPanel extends JPanel {
  private static final int CELL_SIZE = 60;
  private final ReadonlyPawnsBoardModel model;
  private ViewListener listener;
  private Point selectedCell = null;

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
          if (listener != null) listener.handleCellClick(row, col);
          repaint();
        }
      }
    });
  }

  @Override protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    // Drawing implementation
  }

  @Override public Dimension getPreferredSize() {
    return new Dimension(model.getNumCols() * CELL_SIZE, model.getNumRows() * CELL_SIZE);
  }

  public void setClickListener(ViewListener listener) {
    this.listener = listener;
  }
}