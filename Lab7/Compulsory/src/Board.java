import java.util.List;

public class Board extends Thread {
    List<Token> tokenList;

    /**
     * retin statusul jocului
     * jocul se termina daca nu mai sunt tokeni pe tabla
     * sau daca cineva a reusit sa adune o suma predefinita standard
     */
    private boolean isGameRunning = true;
    public static int JACKPOT = 200; //suma

    public boolean isGameRunning() {
        return isGameRunning;
    }

    /**
     * modific jocul si opresc jucatorii sa mai extraga tokenuri de pe tabla
     */
    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    public Board(List<Token> tokens) {
        this.tokenList = tokens;
    }

    public synchronized Token getToken() {
        return tokenList.size() > 0 ? tokenList.get(0) : null;
    }

    public void removeToken(Token token) {
        this.tokenList.remove(token);
    }

    public List<Token> getTokenList() {
        return this.tokenList;
    }
}

