package com.artillery;

import java.time.LocalTime;

/**
 * Responsabilité unique : coordonner le déclenchement de l'alarme.
 * Délègue la planification à AlarmScheduler et le son à SoundPlayer.
 * N'implémente plus Runnable — ce n'est plus son rôle.
 */
public class AlarmClock {

    private final LocalTime alarmTime;
    private final AlarmScheduler scheduler;
    private final SoundPlayer soundPlayer;

    public AlarmClock(LocalTime alarmTime, AlarmScheduler scheduler, SoundPlayer soundPlayer) {
        this.alarmTime = alarmTime;
        this.scheduler = scheduler;
        this.soundPlayer = soundPlayer;
    }

    /**
     * Démarre l'horloge et programme le déclenchement de l'alarme.
     */
    public void start() {
        scheduler.start(alarmTime, this::onAlarmTriggered);
    }

    private void onAlarmTriggered() {
        // Le son est joué dans un thread non bloquant pour ne pas bloquer le scheduler
        Thread soundThread = new Thread(soundPlayer::playAndWaitForStop);
        soundThread.setDaemon(false);
        soundThread.start();
    }
}
