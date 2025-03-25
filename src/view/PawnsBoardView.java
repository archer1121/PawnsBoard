package view;

import model.ReadonlyPawnsBoardModel;

public interface PawnsBoardView {
  void display();
  void refresh();
  void addClickListener(ViewListener listener);
  void addKeyListener(ViewListener listener);
}