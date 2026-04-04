package com.artillery;

import java.time.LocalTime;
import java.util.Scanner;

/**
 * Point d'entrée : instancie les composants et lance l'application.
 * Ne contient aucune logique métier.
 */
public class Main {
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);

        // Saisie et validation du temps
        TimeInputReader inputReader = new TimeInputReader(sc);
        LocalTime alarmTime = inputReader.readAlarmTime();

        // Injection des dépendances
        AlarmScheduler scheduler = new AlarmScheduler();
        SoundPlayer soundPlayer = new SoundPlayer(sc);
        AlarmClock alarmClock = new AlarmClock(alarmTime, scheduler, soundPlayer);

        // Lancement
        alarmClock.start();
    }
}
