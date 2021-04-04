import java.util.List;

public class Board {
    private final List<Token> tokenList;

    public static int JACKPOT = 200; //suma

    public Board(List<Token> tokens) {
        this.tokenList = tokens;
    }

    public synchronized Token getToken() {
        return tokenList.size() > 0 ? tokenList.get(0) : null;
    }

    public synchronized void removeToken(Token token) {
        this.tokenList.remove(token);
        notifyAll();
    }

    public List<Token> getTokenList() {
        return this.tokenList;
    }
}


