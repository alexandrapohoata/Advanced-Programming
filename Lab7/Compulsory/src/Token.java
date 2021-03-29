public class Token {

    private final int number;
    private final int value;

    public Token(int number) {
        this.number = number;
        this.value = (int) (Math.random()*100);
    }

    public int getNumber() {
        return number;
    }

    public int getValue() {
        return value;
    }
}

