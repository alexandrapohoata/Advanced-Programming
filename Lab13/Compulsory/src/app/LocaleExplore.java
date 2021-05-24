package app;

import java.io.*;
import java.util.Locale;
import java.util.Properties;

public class LocaleExplore {
    public static void main(String[] args) throws IOException {
        String request;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        com.LocaleExplore localeExplore = new com.LocaleExplore();
        Properties properties = new Properties();
        String propFile = "C:\\Users\\Alexandra\\Desktop\\FACULTATE\\AN II\\SEM 2\\Java\\Laboratoare\\Laborator 13\\Compulsory\\src\\res\\";
        propFile = propFile + (Locale.getDefault().getLanguage().equals("ro") ? "Messages_ro.properties" : "Messages.properties");
        InputStream inputStream = new FileInputStream(propFile);
        properties.load(inputStream);

        boolean stop = false;
        while (!stop) {
            System.out.print(properties.getProperty("prompt"));
            request = keyboard.readLine();

            switch (request) {
                case "locales" -> {
                    System.out.println("\n" + properties.getProperty("locales"));
                    localeExplore.DisplayLocales()
                            .forEach(l -> System.out.println(l + "::" + l.getDisplayCountry()));
                }

                case "locale.set" -> {
                    System.out.print("Enter language: ");
                    String lang = keyboard.readLine();
                    System.out.print("Enter country: ");
                    String country = keyboard.readLine();
                    localeExplore.setLocale(lang, country);
                    System.out.println(properties.getProperty("locale.set").replace("{0}", Locale.getDefault().getCountry()));
                }
                case "info" -> {
                    System.out.println(properties.getProperty("info").replace("{0}", Locale.getDefault().getCountry()));
                    localeExplore.info(Locale.getDefault());
                }
                case "exit" -> {
                    System.out.println("Bye.");
                    stop = true;
                }
                default -> System.out.println(properties.getProperty("invalid"));
            }
        }
    }
}
