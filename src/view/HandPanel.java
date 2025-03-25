package view;

import javax.swing.*;
import java.awt.*;

import model.ReadonlyPawnsBoardModel;

public class HandPanel extends JPanel {
  private static final int CARD_WIDTH = 80;
  private static final int CARD_HEIGHT = 120;
  private final ReadonlyPawnsBoardModel model;
  private ViewListener listener;
  private Integer selectedCardIndex = null;

  public HandPanel(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setBackground(Color.WHITE);
    // Mouse listener implementation
  }

  @Override protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    // Drawing implementation
  }

  @Override public Dimension getPreferredSize() {
    return new Dimension(800, CARD_HEIGHT + 20);
  }

  public void setClickListener(ViewListener listener) {
    this.listener = listener;
  }
}