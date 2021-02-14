/*

Write a Java application that implements the following operations:
Display on the screen the message "Hello World!". Run the application. If it works, go to step 2 :)
Define an array of strings languages, containing {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"}
Generate a random integer n: int n = (int) (Math.random() * 1_000_000);
Compute the result obtained after performing the following calculations:
  multiply n by 3;
  add the binary number 10101 to the result;
  add the hexadecimal number FF to the result;
  multiply the result by 6;
Compute the sum of the digits in the result obtained in the previous step. This is the new result. While the new result has more than one digit, continue to sum the digits of the result.
Display on the screen the message: "Willy-nilly, this semester I will learn " + languages[result].
 */

public class Main {
    public static String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
    public static int n = (int)(Math.random() * 1_000_000);

    public static void main(String[] args) {
        // Step 1
        displayHelloWorld();

        // Step 2
        int nr = sumDigits(991);
        System.out.println("Suma cifrelor nr. 99 este " + nr);

        int result = computeResult(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    private static void displayHelloWorld() {
        System.out.println("Hello World!");
    }

    private static int computeResult(int number) {
        number = number * 3;
        number = number + Integer.parseInt("10101", 2);
        number = number + Integer.parseInt("FF", 16);
        number = number * 6;

        return sumDigits(number);
    }

    private static int sumDigits(int number) {
        int sum;
        int uc;

        do {
            sum = 0;
            while (number != 0) {
                uc = number % 10;
                sum = sum + uc;
                number = number / 10;
            }

            number = sum;
        } while (sum > 9);

        return sum;
    }
}
