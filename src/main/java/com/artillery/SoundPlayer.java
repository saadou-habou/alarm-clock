package com.artillery;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Responsabilité unique : jouer le son d'alarme et attendre l'arrêt utilisateur.
 * Ne connaît ni le scheduler, ni la logique de temps.
 */
public class SoundPlayer {

    private static final String AUDIO_PATH = "audio/emergency-alarm-1000.wav";

    private final Scanner sc;

    public SoundPlayer(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Joue le son d'alarme et attend que l'utilisateur appuie sur Entrée pour l'arrêter.
     * Méthode bloquante — à appeler dans un thread dédié.
     */
    public void playAndWaitForStop() {
        URL resource = getClass().getClassLoader().getResource(AUDIO_PATH);

        if (resource == null) {
            System.out.println("[ERROR] Audio file not found: " + AUDIO_PATH);
            return;
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(resource);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            System.out.println("Press Enter to stop the alarm...");
            sc.nextLine();

            clip.stop();
            clip.close();

        } catch (UnsupportedAudioFileException e) {
            System.out.println("[ERROR] Unsupported audio format.");
        } catch (LineUnavailableException e) {
            System.out.println("[ERROR] Audio line unavailable.");
        } catch (IOException e) {
            System.out.println("[ERROR] Error reading audio file.");
        }
    }
}
