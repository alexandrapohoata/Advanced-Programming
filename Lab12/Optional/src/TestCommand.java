import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TestCommand {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String commandName = scanner.next();
            if (commandName.equalsIgnoreCase("exit"))
                break;

            String[] params = scanner.nextLine().trim().split("\\s+");

            if (commandName.equals("folder")) {
                String path = params[0];
                if (path == null)
                    continue;

                final File jarFile = new File(path);

                if (jarFile.isFile()) {
                    List<String> classNames = new ArrayList<>();
                    ZipInputStream zip = new ZipInputStream(new FileInputStream(params[0]));
                    for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                        if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                            // This ZipEntry represents a class. Now, what class does it represent?
                            String className = entry.getName().replace('/', '.'); // including ".class"
                            classNames.add(className.substring(0, className.length() - ".class".length()));
                        }
                    }
                    classNames.forEach(System.out::println);

                } else { // Run with IDE
                    try (Stream<Path> walk = Files.walk(Paths.get(params[0]))) {
                        List<String> result = walk.filter(Files::isRegularFile)
                                .map(Path::toString).collect(Collectors.toList());

                        result.forEach(System.out::println);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
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
}
