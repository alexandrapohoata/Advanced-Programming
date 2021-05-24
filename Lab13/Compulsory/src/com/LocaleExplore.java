package com;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.*;

public class LocaleExplore {
    public List<Locale> DisplayLocales() {
        return Arrays.asList(DateFormat.getAvailableLocales().clone());
    }

    public void setLocale(String language, String country) {
        Locale.setDefault(new Locale(language, country));
    }

    public void info(Locale specific) {
        Locale loc = specific == null ? Locale.getDefault() : specific;

        System.out.println("Country: " + loc.getDisplayCountry());
        System.out.println("Language: " + loc.getLanguage() + " (" + loc.getDisplayLanguage(Locale.ENGLISH) + ")");
        System.out.println("Currency: " + Currency.getInstance(loc));

        DateFormatSymbols symbols = new DateFormatSymbols(loc);
        List<String> dayNames = Arrays.asList(symbols.getWeekdays().clone());
        System.out.println("Week Days: " + dayNames);

        dayNames = Arrays.asList(symbols.getMonths().clone());
        System.out.println("Months: " + dayNames);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
        Date today = new Date();
        System.out.printf("Today: %s%n", dateFormat.format(today));
    }
}
