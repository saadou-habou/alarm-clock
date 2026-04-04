package com.artillery;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Responsabilité unique : lire et valider une heure saisie par l'utilisateur.
 * Ne connaît ni l'alarme, ni le son, ni le scheduler.
 */
public class TimeInputReader {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss", Locale.US);

    private final Scanner sc;

    public TimeInputReader(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Boucle jusqu'à obtenir une heure valide au format HH:mm:ss.
     * @return LocalTime valide saisi par l'utilisateur
     */
    public LocalTime readAlarmTime() {
        while (true) {
            System.out.print("Enter an alarm time (HH:MM:SS): ");
            String input = sc.nextLine().trim();
            try {
                LocalTime time = LocalTime.parse(input, FORMATTER);
                System.out.println("Alarm set for: " + time);
                return time;
            } catch (DateTimeParseException e) {
                System.out.println("[ERROR] Invalid format. Please use HH:MM:SS.");
            }
        }
    }
}
