package model.cell;
import model.Deck.Card;


public class CardCell implements Cell {

  private Card card;

  public CardCell(Card c) {
    this.card = card;
  }
  @Override
  public String textualPrint() {
    return "";
  }
}
