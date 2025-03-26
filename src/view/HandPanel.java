package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.ReadonlyPawnsBoardModel;

public class HandPanel extends JPanel {
  private static final int CARD_WIDTH = 80;
  private static final int CARD_HEIGHT = 120;
  private static final int GAP = 10;

  private final ReadonlyPawnsBoardModel model;
  private ViewListener listener;
  private Integer selectedCardIndex = null;

  // For now, we assume there are 5 cards in the hand.
  // Later, you can replace this with model.getHand().size() (or similar) once your model exposes the hand.
  private final int numCards = 5;

  /**
   * The separate panel to hold the cards
   * @param model of the game already init
   */
  public HandPanel(ReadonlyPawnsBoardModel model) {
    this.model = model;
    setBackground(Color.WHITE);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        // Cards are drawn starting at GAP from the left and at y=GAP from the top.
        for (int i = 0; i < numCards; i++) {
          int cardX = GAP + i * (CARD_WIDTH + GAP);
          int cardY = GAP;
          if (x >= cardX && x <= cardX + CARD_WIDTH &&
                  y >= cardY && y <= cardY + CARD_HEIGHT) {
            selectedCardIndex = i; // mark the card as selected
            if (listener != null) {
              listener.handleCardClick(i);
            }
            repaint();
            break;
          }
        }
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    int startX = GAP;
    int startY = GAP;

    // Loop through and draw each card.
    for (int i = 0; i < numCards; i++) {
      // Highlight if selected.
      if (selectedCardIndex != null && selectedCardIndex == i) {
        g2d.setColor(Color.CYAN);
      } else {
        g2d.setColor(Color.WHITE);
      }
      g2d.fillRect(startX, startY, CARD_WIDTH, CARD_HEIGHT);

      // Draw the card border.
      g2d.setColor(Color.BLACK);
      g2d.drawRect(startX, startY, CARD_WIDTH, CARD_HEIGHT);

      // Draw card label text (you can replace this with card details later)
      String cardText = "Card " + (i + 1);
      FontMetrics fm = g2d.getFontMetrics();
      int textWidth = fm.stringWidth(cardText);
      int textHeight = fm.getAscent();
      int textX = startX + (CARD_WIDTH - textWidth) / 2;
      int textY = startY + (CARD_HEIGHT + textHeight) / 2;
      g2d.drawString(cardText, textX, textY);

      // Update x-coordinate for the next card.
      startX += CARD_WIDTH + GAP;
    }
  }

  @Override
  public Dimension getPreferredSize() {
    // Make room for all cards plus some padding.
    int width = GAP + numCards * (CARD_WIDTH + GAP);
    int height = CARD_HEIGHT + 2 * GAP;
    return new Dimension(width, height);
  }

  /**
   * Init view listener
   * @param listener to handle clicks
   */
  public void setClickListener(ViewListener listener) {
    this.listener = listener;
  }
}
