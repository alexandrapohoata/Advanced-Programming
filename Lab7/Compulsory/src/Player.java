import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable
{
    private final String name;
    private final Board board;

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

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public boolean isWinner()
    {
        int sum = 0;
        for(Token token:tokens){
            sum += token.getValue();
        }
        pointsNumber = this.tokens.size();

        return sum > Board.JACKPOT;
    }

    //suprascrie metoda run deoarece am implementat interfata Runnable
    @Override
    public void run()
    {
        // fiecare jucator extrage succesiv un token din board

        Token token;
        while(board.isGameRunning()) {

            // extrage de pe tabla
            // adaugam token-ul jucatorului
            token = board.getToken();

            if(token == null)
                break;

            System.out.println(this.name + " a luat token-ul " + token.getNumber() + "(valoare token = " + token.getValue() + ")");
            tokens.add(token);

            synchronized (this)
            {
                board.removeToken(token);
            }

            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

