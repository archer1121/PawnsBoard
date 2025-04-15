package adapter;

import model.PlayerColor;
import model.PlayerModel;
import providerCode.controller.IPlayer;
import providerCode.controller.StrategyPlayer;
import providerCode.view.PlayerActions;
import providerCode.view.PBView;
import providerCode.model.pawnsboard.IPawnsBoard;
import providerCode.strategy.Move;

public class ProviderPlayerAdapter implements IPlayer, StrategyPlayer {
  private final PlayerModel player;

  public ProviderPlayerAdapter(PlayerModel player) {
    this.player = player;
  }

  // Since this is a human player, using strategy is not applicable.
  @Override
  public Move playStrategy(IPawnsBoard model) {
    throw new UnsupportedOperationException("Human player does not use strategy.");
  }

  @Override
  public PlayerColor getPlayerColor() {
    return player.getPlayerColor();
  }

  @Override
  public void addObserver(PlayerActions controller) {
    // For a human player, the provider’s view will register its own listeners.
    // Optionally, store the observer if needed.
  }

  @Override
  public void getMove(IPawnsBoard model) {
    // For human players, moves are provided via UI interactions.
  }

  @Override
  public void removeListeners() {
    // Stub: no additional listeners to remove.
  }

  @Override
  public void alertTurnChange(PBView view) {
    // You can alert by displaying a message on the provider’s view.
    view.showMessage("It's now " + player.getPlayerColor() + "'s turn.");
  }

  @Override
  public boolean isAI() {
    // Return false since this adapter is for a human player.
    return false;
  }
}
