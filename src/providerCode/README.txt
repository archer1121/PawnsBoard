## Overview
Pawns Board is a two-playerColor game played on a board of cells and custom cards.
The game is a variation of a new card game called Queen’s Blood.
In the game, each playerColor has a color either red or blue, a deck of cards,
and a hand made from their respective decks.
To start the game, each playerColor is given a deck of cards to play with and by
default the Red Player is given the first move. This codebase implements the game
with structured rules, scoring, and board interactions. It ensures fair play and
allows future expansions.

## Jar file
Run any Jar files from the root folder of the project.
It should be in the same folder as where folders src, test, out, docs are contained.

## High Level Assumptions
- Players are familiar with turn-based strategy games.
- The board has fixed dimensions: an odd number of columns and a positive number of rows.
- Decks are loaded from configuration files, with a maximum of two copies of any card.
- Players alternate turns, placing cards based on pawn availability and card influence.

## Quick Start
Screenshots of the visual game representation can be found in
/test/cs3500/pawnsboard/view/viewscreenshots

To start a game of Pawns Board, follow these steps:

Textual
main can be found inside /src/PawnsBoard.java
 * This is 0 indexed so the top left cell is (0,0). The hand is also 0 indexed.
 * Edit the `main` method as shown below:
  public static void main(String[] args) {
    DeckReader deckReader = new DeckReader("deck"); // The name of the deck in the docs directory.
    List<Card> loadedDeck = deckReader.getDeck();
    ArrayList<Card> deckReaderDeck = new ArrayList<>(loadedDeck);

    PawnsBoardModel game = new PawnsBoardModel(5, 3); // 5 is the width and 3 is the height
    PawnsBoardTextualViewC view = new PawnsBoardTextualViewC(game);
    game.startGame(deckReaderDeck, deckReaderDeck, false, 5); // The first deck is for red and
    the second is for blue, false refers to whether the decks are shuffled, and 5 is the handsize
    gameplay(view, game); // If simulating a game
    System.out.println(game.isGameOver()); // This prints true or false whether the game is
    over after the game was simulated
  }

Visual
main can be found inside /src/cs3500/pawnsboard/PawnsBoardGame.java
 * This is 0 indexed so the top left cell is (0,0). The hand is also 0 indexed.
 * Edit the `main` method as shown below:
    public static void main(String[] args) {

      DeckReader deckReader = new DeckReader("deck"); // The name of the deck in the docs directory.
      List<Card> loadedDeck = deckReader.getDeck();
      ArrayList<Card> deckReaderDeck = new ArrayList<>(loadedDeck);

      IPlayer humanRed = new HumanPlayer(PlayerColor.RED);
      IPlayer humanBlue = new HumanPlayer(PlayerColor.BLUE);
      IPlayer fillFirstMachine = new MachinePlayer(PlayerColor.BLUE, new FillFirst());
      IPlayer maximizeRowScoreMachine = new MachinePlayer(PlayerColor.BLUE, new MaximizeRowScore());

      IPawnsBoard model = new PawnsBoardModel(7, 5); // Defines that the board is 7 x 5
      IPawnsBoardReadOnly<Card> readOnlyModel = new ViewModel(model);

      model.startGame(deckReaderDeck, deckReaderDeck, false, 5); // The first deck is for red and
      the second is for blue, false refers to whether the decks are shuffled, and 9 is the handsize

      PBView viewRed = new PBFrame(readOnlyModel, humanRed); // Creates a view for the BLUE
      playerColor
      PBView viewBlue = new PBFrame(readOnlyModel, humanBlue); // Creates a view for the
      BLUE playerColor

      IPBController controllerRed = new PBController(model, viewRed, humanRed); // Calls the
      controller to play the game for Red.
      IPBController controllerBlue = new PBController(model, viewBlue, humanBlue); // Calls the
      controller to play the game for Blue.

      controllerRed.playGame(model);
      controllerBlue.playGame(model);
    }
    * In order to swap between player types users of this program must change the inputs of the
    view and the controller. For instance if you wanted to replaced blue with a fillFirstMachine
    replace humanBlue with fillFirstMachine in both viewBlue and controllerBlue

JAR
The jar file can be found in the root folder. Launching that file will simply run the game.

## Key Components
    1. Controller
        * Updates model and view after user interaction.
        * Listens for card selections, grid cell selections, and move confirmations or passes.
        * Also controls reading in deck configurations.
    2. Model
        * Manages game logic for the game. This holds all the data for the game such as card
        * representation, cell representation, and the model class itself
        * which handles logic and specific data. The model is 0 Indexed. So the top left cell
        * would be (0,0). The hand is also 0 indexed.
    3. View
        * Handles textual rendering and visual rendering of the board,
          allowing players to view the current state of the game.
        * Important to note that the panel has its own grid system that needs to be translated
          for the controller. This is described in PBPanel in the first JavaDoc of that class.
    4. Strategy
        * Defines AI behavior for computer-controlled players.
    5. Docs
        * Contains configuration files (decks) used for gameplay, which are read by the
          `DeckReader`.

## Key Subcomponents
    - Controller
        * DeckReader - Reads and parses deck configuration files.
                       Placed inside of controller
        * IPBController - Interface defining controller behavior.
        * PBController - Responsible for controlling the game flow between the view and the model.
        * IPlayer - Defines the traits of a player that can interact with a game of pawnsBoard.
        * StrategyPlayer - Simple interface for a player that can give make moves according to some strategy.
        * HumanPlayer - Defines the basic traits a human player has and their playercolor.
        * MachinePlayer - Defines the basic traits a computer player has and their playercolor.
    - Model
        **card**
            * Card - Represents a playable card with name, cost, value, and influence.
            * ICard - Interface defining card behavior.
            * InfluenceCell - Defines how cards affect board cells. Can either be EMPTY, INFLUENCE,
                              or CARD
        **cell**
            * ACell - Abstract representation of a board cell.
            * CardCell - A cell containing a placed card.
            * PawnCell - A cell containing pawns.
            * EmptyCell - A cell with no pawns or cards.
            * ICell - Interface for all cell types.
        **pawnsboard**
            * IPawnsBoard - The interface which defines all methods used by the model.
            * IPawnsBoardReadOnly - An interface for all of the non-mutable methods in pawnsBoard.
            * PawnsBoardModel - Main game model handling board state, rules, scoring,
                                playerColor turns, game state such as whether the game is over or
                                not.
            * PlayerColor - Represents a playerColor (Red or Blue).
            * ViewModel - Represents an implementation of a read-only pawns board model.
    - View
        * PawnsBoardTextualView - Interface defining textual representation behavior.
        * PawnsBoardTextualViewC - Renders the board as text.
        * PBFrame - Displays the board and handles some user interaction.
        * PBPanel - Extends JPanel and does the visual representation of the game board.
        * PBPosn - Represents a position on the PawnsBoard with row and column coordinates.
        * PBView - Defines methods for updating and interacting with the graphical user interface.
        * ViewActions - Defines methods for handling keyboard input and mouse clicks on the board.
    - Strategy
        * FillFirst - A strategy where the first card and location that can be played on is chosen.
        * MaximizeRowScore - A strategy where the first card and location in a row that will allow
                             the playerColor to gain a higher row score than the opponent is chosen.
        * Move - Class defining a move, includes the location to place the card at and the card's
                 index in the hand.
        * PBStrategy - Interface for strategy function objects that choose the next move according
                       to the strategy rules.
        * Log files for these two strategies can be found at /test.cs3500/pawnsboard/strategy/
    * PawnsBoard.java
        - Entry point for running the game. This currently runs the textual version.
    * PawnsBoardGame.java
        - Entry point for running the game. This currently runs the windowed/visual version.

## Source organization
/src/cs3500/pawnsboard/
├── controller/   # Handles user input and game logic coordination
├── model/      # Game logic
    ├── card/           # Represents a generic card with a name, cost, value, and influence grid
    ├── cell/           # Manages representation of board cells and their state for game logic
    ├── pawnsBoard/     # Main game logic, allows for card placement, drawing cards, ect.
├── strategy/   # Defines strategies for making moves in the game
├── view/       # Text-based and Visual board rendering
├── PawnsBoardGame      # Main execution class (Visual)
└── PawnsBoard  # Main execution class (Textual)

## Deck Configuration
CARD_NAME COST VALUE
XXXXX
XXXXX
XXXXX
XXXXX
XXXXX

The X's represent empty Cells. A C can be placed anywhere but is almost always placed in the center.
Then I's can be placed anywhere on the grid replacing X's and the I's represent the respective
cards influence. The grid is always 5x5. Cost can either 1, 2, or 3. Value can be anything > 0.

## Rules
    ## Game Setup
    The board is a rectangular grid with:
        An odd number of columns.
        At least one row.
    Each playerColor (Red and Blue) starts with:
        A deck of cards loaded from a configuration file.
        A starting hand dealt from their deck.
    The game begins with:
        An empty board, except for pawns placed in the first and last columns.

    ## Turn Structure
    Players take turns, with Red always going first.
    On a turn, the playerColor:
        Draws a card (if their deck is not empty).
        Chooses one of the following actions:
            Pass: Ends their turn without playing a card.
            Place a Card:
                Puts a card on the board.
                Applies the card’s influence to the board (adding or converting pawns).
    The turn then switches to the other playerColor.

    ## Placing Cards
    A card can only be placed on a cell that:
        Contains pawns owned by the current playerColor.
        Has a number of pawns that meets or exceeds the card’s cost.
    When a card is placed:
        It replaces the pawns in that cell.
        Its influence grid is activated.

    ## Influence Mechanics
    Each card has a 5x5 influence grid that affects surrounding cells.
    Influenced cells can:
        Gain an additional pawn (if empty or owned by the playerColor).
        Convert opponent’s pawns to the current playerColor’s control.

    ## Game End & Scoring
    The game ends when both players pass consecutively or the board is filled with cards.
    Scoring is calculated as follows:
        Each row’s score is the sum of the value of owned cards in that row.
        The playerColor with the higher row score wins that row.
        The playerColor with the highest total score across all rows wins the game.
        If the total scores are tied, the game ends in a draw.

## Game functionality for the Visual version of the game.
    - A user can click on a card in their hand or a position on the board,
      and it will be highlighted to indicate selection.
    - The selected card or position will change to a cyan color.
    - A user can deselect this by clicking that cell again.
    - The controller will print a message to System.out, displaying the index of the selected card or
      position and who clicked there.

Move Confirmation or Pass:
    - A user can indicate whether they want to confirm their move or pass by using keyboard input.
    - E is used to confirm the placement
    - P is used to pass the turn.
    - Any other key will prompt the user to use E or P.
    - This will print a message to System.out, specifying whether the key
      press indicated a confirmation, pass, or for them to try again.

## Changes for part 2
    ## Changes to the Model
        Model was split up into two interfaces IPawnsBoard and IPawnsBoardReadOnly.
        IPawnsBoardReadOnly contains all of the observation methods of the model and IPawnsBoard
        extends IPawnsBoardReadOnly in addition to containing all the mutator methods.
        This was done because the View should not be able to access the Mutable methods
        of the model.

        New methods added to the model : getWinner, getBoard, getAllHands, and getCurrentPlayer
        getWinner: Returns a Player after analyzing the scores to determine who has won only if
        the game is over.
        getBoard: Returns a copy of the current board.
        getAllHands: Returns a copy of both playerColor's hands in an array where the first
        index contains the red hand and the second index contains the blue hand.
        getCurrentPlayer: Returns a String of either Red or Blue depending on who's turn it is.

## Changes for part 3
    ## Changes to the Model
    Added multiple new methods to the model. These methods include addGameEventListener,
    removeGameEventListener, notifyTurnChange, and notifyGameOver.
    These methods allow or method to utilize its listeners to notify the controller of
    any game changes or updates.

    ## Changes to Players
    Changed how Players were defined and passed into the controller. Before we just passed in the
    PlayerColor but now a Player is an IPlayer and can either be a HumanPlayer or MachinePlayer.
    A HumanPlayer has a PlayerColor and a MachinePlayer has a PlayerColor and a Strategy associated
    with it.