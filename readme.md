Demonstration of the real Queen's Blood game: https://www.youtube.com/watch?v=TpeQ3bEc8K4

if we want a 3x5 gameboard, we actually make it 3x7 with s representing a scoring cell
[s][][][][][][s]\n
[s][][][][][][s]\n
[s][][][][][][s]\n


3rd phase changes. Two controllers one for each player. 
Also added a model listener interface. 
Also ensured that player actions are not done out of turn, 
also added label to show whose turn it is. 
Also, we now take a command line argument to run the game.
java -jar out/artifacts/PawnsBoard_jar/PawnsBoard.jar docs/redDeck.txt docs/blueDeck.txt human human
is an example, where human is the strategy.

