package view;

import javax.swing.*;
import java.awt.*;
import model.ReadonlyPawnsBoardModel;

public class PawnsBoardSwingView extends JFrame implements PawnsBoardView {
  private final ReadonlyPawnsBoardModel model;
  private final BoardPanel boardPanel;
  private final HandPanel handPanel;
  private ViewListener listener;

  public PawnsBoardSwingView(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setTitle("Pawns Board Game");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    boardPanel = new BoardPanel(model);
    handPanel = new HandPanel(model);

    add(boardPanel, BorderLayout.CENTER);
    add(handPanel, BorderLayout.SOUTH);

    pack();
    setMinimumSize(new Dimension(800, 600));
  }

  @Override public void display() { setVisible(true); }
  @Override public void refresh() { boardPanel.repaint(); handPanel.repaint(); }
  @Override public void addClickListener(ViewListener listener) {
    this.listener = listener;
    boardPanel.setClickListener(listener);
    handPanel.setClickListener(listener);
  }
  @Override public void addKeyListener(ViewListener listener) {
    this.listener = listener;
    // Key listener implementation
  }
}