package model.cell;
import model.Deck.Card;
import model.PlayerColor;


public class CardCell implements Cell {

  private Card card;

  public CardCell(Card c) {
    this.card = card;
  }

  public int getCardValue() {
    return this.card.getValue();
  }

  public PlayerColor getCardColor() {
    return this.card.getColor();
  }


  @Override
  public String textualPrint() {
    return "C";
  }
}
