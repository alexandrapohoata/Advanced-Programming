import java.util.List;

public class Game extends Thread
{
    Board board;
    List<Player> players;
    private int gameIsRunning = 1;

    public Game(Board board, List<Player> players)
    {
        this.players = players;
        this.board = board;
        startGame();
    }

    private void startGame()
    {
        // pentru fiecare jucator creez thread-urile
        for (Player player : players) {
            Thread thread = new Thread(player);
            thread.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        while(gameIsRunning == 1)
        {
            // verificam daca jucatorul a adunat suma
            for(Player player : players) {
                if (player.isWinner()) {
                    System.out.println("Jucatorul " + player.getName() + " a strans primul suma. A castigat " + player.getPointsNumber() + " puncte.");
                    board.setGameRunning(false);
                    gameIsRunning = 0;
                    break;
                }
            }

            // daca tabla nu mai contine token-uri, oprim jocul
            if (board.getTokenList().size() == 0) {
                System.out.println("Nimeni nu a strans suma.");
                board.setGameRunning(false);
                gameIsRunning = -1;
                break;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(gameIsRunning == -1) {
            for (Player player : players)
                System.out.println("Jucatorul " + player.getName() + " a obtinut " + player.getPointsNumber() + " puncte.");
        }
    }
}

