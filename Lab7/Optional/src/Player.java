import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private final Board board;
    private final Game game;
    private final int turn;
    private int totalSum = 0;
    private Thread thread;

    /**
     * tokenii ii retin intr-o lista de tokens
     */
    private final List<Token> tokens = new ArrayList<>();
    private int pointsNumber;

    public String getName() {
        return name;
    }

    /**
     * nr de puncte este determinat de cati tokeni a extras
     */

    public int getPointsNumber() {
        return pointsNumber;
    }

    public Player(String name, Board board, int turn, Game game) {
        thread = new Thread(this);
        this.name = name;
        this.board = board;
        this.turn = turn;
        this.game = game;
    }

    public Thread getThread() {
        return thread;
    }

    public synchronized boolean isWinner() {
        pointsNumber = this.tokens.size();
        return totalSum > Board.JACKPOT;
    }

    //suprascrie metoda run deoarece am implementat interfata Runnable
    @Override
    public void run() {
        // fiecare jucator extrage succesiv un token din board

        Token token;
        while (game.getGameIsRunning() == 1) {
            synchronized (this) {
                // extrage de pe tabla
                // adaugam token-ul jucatorului
                token = board.getToken();

                if (token == null)
                    break;

                board.removeToken(token);

                System.out.println(this.name + " a luat token-ul " + token.getNumber() + "(valoare token = " + token.getValue() + ")");
                tokens.add(token);

                totalSum += token.getValue();

                while (game.getTurn() == turn) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


