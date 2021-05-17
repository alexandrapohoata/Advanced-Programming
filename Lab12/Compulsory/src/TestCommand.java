import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class TestCommand {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandName = scanner.next();
            if (commandName.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                CmdClass cmd = new CmdClass();
                cmd.execute(commandName);
                cmd.test();

            } catch (ClassNotFoundException e) {
                System.err.println(e);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
