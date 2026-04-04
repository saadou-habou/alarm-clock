package com.artillery;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Responsabilité unique : planifier et gérer le cycle de vie du scheduler.
 * Ne connaît ni le son, ni la saisie utilisateur.
 */
public class AlarmScheduler {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    /**
     * Lance l'horloge (tick chaque seconde) et déclenche onAlarm à l'heure donnée.
     *
     * @param alarmTime heure cible
     * @param onAlarm   action à exécuter lors du déclenchement
     */
    public void start(LocalTime alarmTime, Runnable onAlarm) {
        startClock();
        scheduleAlarm(alarmTime, onAlarm);
    }

    private void startClock() {
        scheduler.scheduleAtFixedRate(() -> {
            LocalTime now = LocalTime.now();
            System.out.printf("\r%02d:%02d:%02d",
                    now.getHour(), now.getMinute(), now.getSecond());
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void scheduleAlarm(LocalTime alarmTime, Runnable onAlarm) {
        long delay = Duration.between(LocalTime.now(), alarmTime).toMillis();
        if (delay < 0) {
            delay += 24L * 60 * 60 * 1000; // planifie pour le lendemain si l'heure est passée
        }

        scheduler.schedule(() -> {
            System.out.println("\n⏰ Alarm!");
            onAlarm.run();
            scheduler.shutdown();
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }
}
