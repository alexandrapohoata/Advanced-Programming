import java.util.List;

public class Game extends Thread
{
    final Board board;
    List<Player> players;
    private int gameIsRunning = 1;
    private int turn = 1;

    public Game(Board board) {
        this.board = board;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getTurn() {
        return turn % players.size();
    }

    public void startGame() throws InterruptedException {
        for (Player player : players)
            player.getThread().start();

        while(gameIsRunning == 1) {
            for (Player player : players) {
                if (player.isWinner()) {
                    System.out.println("Jucatorul " + player.getName() + " a strans primul suma. A castigat " + player.getPointsNumber() + " puncte.");
                    gameIsRunning = 0;
                    break;
                }
            }
            // daca tabla nu mai contine token-uri, oprim jocul
            if (board.getTokenList().size() == 0) {
                System.out.println("Nimeni nu a strans suma.");
                gameIsRunning = -1;
                break;
            }
            turn++;

            Thread.sleep(1000);
        }

        if(gameIsRunning == -1) {
            for (Player player : players)
                System.out.println("Jucatorul " + player.getName() + " a obtinut " + player.getPointsNumber() + " puncte.");
        }
    }

    public int getGameIsRunning() {
        return gameIsRunning;
    }
}


