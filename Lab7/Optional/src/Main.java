/*  Create classes in order to model the problem. You may assume that all possible tokens are initially available, having random values.
    Each player will have a name and they must perform in a concurrent manner, extracting repeatedly tokens from the board.
    Simulate the game using a thread for each player.
    Pay attention to the synchronization of the threads when extracting tokens from the board.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main
{
    public static void main(String[] args) throws InterruptedException {

        // initializam n tokeni
        final int n = 50;
        Token[] token = IntStream.rangeClosed(1, n).mapToObj(Token::new).toArray(Token[]::new);
        List<Token> tokens = new ArrayList<>(Arrays.asList(token));

        // initializam tabla
        Board board = new Board(tokens);

        // instantiam jcuatorii
        Game game = new Game(board);
        String[] playerName = {"Ana", "Diana", "Mihai", "Nicu", "Irina", "Rares"};
        Player[] player = IntStream.rangeClosed(0, 5).mapToObj(i -> new Player(playerName[i], board, i+1, game)).toArray(Player[]::new);
        List<Player> players = new ArrayList<>(Arrays.asList(player));
        game.setPlayers(players);
        game.startGame();
    }
}


