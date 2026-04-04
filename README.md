# ⏰ AlarmClock — Réveil en ligne de commande (Java)

Une application Java légère qui affiche l'heure en temps réel dans le terminal et déclenche une alarme sonore à l'heure choisie par l'utilisateur.

---

## Fonctionnalités

- Affichage de l'heure courante en temps réel (mise à jour chaque seconde)
- Saisie d'une heure d'alarme au format `HH:MM:SS` avec validation
- Déclenchement d'un son d'alarme à l'heure programmée
- Arrêt de l'alarme par simple pression de la touche `Entrée`
- Replanification automatique au lendemain si l'heure saisie est déjà passée

---

## Architecture

Le projet applique le **principe de responsabilité unique (SRP)** : chaque classe a un rôle précis et limité.

```
src/
└── com/artillery/
    ├── Main.java             # Point d'entrée — orchestration et injection des dépendances
    ├── TimeInputReader.java  # Lecture et validation de la saisie utilisateur
    ├── AlarmClock.java       # Logique métier — coordination du déclenchement
    ├── AlarmScheduler.java   # Planification via ScheduledExecutorService
    └── SoundPlayer.java      # Lecture du fichier audio et arrêt interactif
```

| Classe            | Responsabilité                                        |
|-------------------|-------------------------------------------------------|
| `Main`            | Instancie les composants et lance l'application       |
| `TimeInputReader` | Boucle de saisie avec validation du format `HH:mm:ss` |
| `AlarmClock`      | Coordonne le scheduler et le lecteur audio            |
| `AlarmScheduler`  | Gère le `ScheduledExecutorService` (horloge + alarme) |
| `SoundPlayer`     | Joue le son `.wav` et attend l'arrêt utilisateur      |

---

## Prérequis

- **Java 11** ou supérieur
- Un fichier audio `.wav` placé dans les ressources du projet

---

## Installation

```bash
# Cloner le dépôt
git clone https://github.com/saadou-habou/alarm-clock.git
cd alarm-clock
```

Placez votre fichier audio dans :

```
src/main/resources/audio/
```

---

## Compilation et exécution

### Avec Maven

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.artillery.Main"
```

### Avec javac (compilation manuelle)

```bash
javac -d out src/com/artillery/*.java
java -cp out com.artillery.Main
```

---

## Utilisation

```
Enter an alarm time (HH:MM:SS): 07:30:00
Alarm set for: 07:30:00

07:29:58
⏰ Alarm!
Press Enter to stop the alarm...
```

---

## Structure des ressources

```
src/main/resources/
└── audio/
    └── emergency-alarm-1000.wav   # Fichier son de l'alarme
```

> Le fichier audio doit être au format **WAV PCM** pour être compatible avec l'API `javax.sound.sampled`.

---

## Dépendances

Ce projet n'utilise **aucune bibliothèque externe**. Il repose uniquement sur :

- `java.time` — gestion du temps
- `java.util.concurrent` — planification des tâches
- `javax.sound.sampled` — lecture audio

---

## Licence

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
