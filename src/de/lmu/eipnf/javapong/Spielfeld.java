package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Version 0.0.6
 * Diese Klasse repräsentiert das Spielfeld und enthält alle Elemente und die Steuerung der Padels.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Spielfeld extends JPanel implements KeyListener, Runnable { // Spielfeld ist ein JPanel und implementiert das Interface Keylistener

    int ballXpos; // Ballposition X-Achse
    int ballYpos; // Ballposition Y-Achse

    int paddelRechtsYpos; // Paddelposition Rechts Y-Achse
    int paddelLinksYpos; // Paddelposition Links Y-Achse

    int spielstandSpieler1; // Spielstand für Spieler 1
    int spielstandSpieler2; // Spielstand für Spieler 2

    boolean paddelLinksHochBewegen; // Zustand von linken Paddel; true = in Bewegeung, false = nicht in Bewegung
    boolean paddelLinksRunterBewegen; // Zustand von rechtem Paddel; true = in Bewegeung, false = nicht in Bewegung
    boolean paddelRechtsHochBewegen; // Zustand von rechtem Paddel; true = in Bewegeung, false = nicht in Bewegung
    boolean paddelRechtsRunterBewegen; // Zustand von rechtem Paddel; true = in Bewegeung, false = nicht in Bewegung

    boolean animationGestartet; // Zustand, ob Animations-Thread schon gestartet ist

    JLabel spielstand; // Anzeige der Punkte
    JLabel spielerinfo; // Anzeige der Punkte

    public Spielfeld() { // Konstruktor; Bisher ohne weitere Funktion
        paddelLinksYpos = 250; // Anfangswert des linken Paddels in Y-Richtung
        paddelRechtsYpos = 250; // Anfangswert des rechten Paddels in Y-Richtung
        paddelLinksHochBewegen = false; // Paddel anfangs nicht in Bewegung
        paddelLinksRunterBewegen = false; // Paddel anfangs nicht in Bewegung
        spielstandSpieler1 = 0; // Spielstand-Variable für Spieler 1
        spielstandSpieler2 = 0; // Spielstand-Variable für Spieler 2
        animationGestartet = false;

        spielstand = new JLabel("Aktueller Spielstand " + spielstandSpieler1 + ":" + spielstandSpieler2); // Ein neues Objekt vom Typ JLabel wird erstellt und mit der Beschriftung initialisiert
        spielerinfo = new JLabel("Leertaste zum Starten drücken!");

        spielstand.setFont(new Font("Calibri", Font.BOLD, 20)); // Setzt den Font der Score-Anzeige auf Calibri, Fett und Schriftgröße 20
        spielstand.setForeground(new Color(0x0EE00)); // Setzt die Farbe auf Grün bzw. Hexadezimal 0EE00s
        spielstand.setHorizontalAlignment(JLabel.CENTER);
        spielerinfo.setFont(new Font("Calibri", Font.BOLD, 26)); // Setzt den Font der Spielerinfo-Anzeige auf Calibri, Fett und Schriftgröße 20
        spielerinfo.setForeground(new Color(0x0EE00)); // Setzt die Farbe auf Grün bzw. Hexadezimal 0EE00s
        spielerinfo.setHorizontalAlignment(JLabel.CENTER);

        this.setLayout(new BorderLayout()); // Verwende den Layoutmanager "BorderLayout"
        this.add(spielstand, BorderLayout.NORTH); // Füge die Spielstandanzeige dem Spielfeld hinzu
        this.add(spielerinfo, BorderLayout.CENTER); // Füge die Spielerinfo dem Spielfeld hinzu

        new Thread(this).start(); // Starte den Animations-Thread

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (animationGestartet == true) { // Zeichne den Ball erst, wenn das Spiel beginnt.
            g2d.setColor(Color.red);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillOval(ballXpos, ballYpos, 20, 20);
            movePaddels(); // Bewege die Paddels

        }

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, paddelLinksYpos, 20, 100);
        g2d.fillRect(580, paddelRechtsYpos, 20, 100);

    }

    // Innerhalb der Run-Methode wird die Animation gesteuert.
    @SuppressWarnings("static-access")
    @Override
    public void run() {
        int schrittweiteXRichtung = -1; // Schrittweite des Balls
        int schrittweiteYRichtung = -1; // Schrittweite des Balls
        AudioPlayer beepPlayer = new AudioPlayer();

        while (true) { // Gameloop

            try {

                if (animationGestartet) { // Nur wenn die Animatoin läuft
                    // Ballgrenzen abfragen
                    if ((ballXpos == 560) && ((ballYpos > paddelRechtsYpos) && (ballYpos < (paddelRechtsYpos + 100)))) {
                        schrittweiteXRichtung = schrittweiteXRichtung * -1;
                        beepPlayer.playWave("paddle.wav");

                    }
                    if ((ballXpos == 20) && ((ballYpos > paddelLinksYpos) && (ballYpos < (paddelLinksYpos + 100)))) {
                        schrittweiteXRichtung = schrittweiteXRichtung * -1;
                        beepPlayer.playWave("paddle.wav");
                    }

                    if (ballXpos == 600) {
                        beepPlayer.playWave("lose.wav");
                        wertePunkte(1, 0);
                    }
                    if (ballXpos == 0) {
                        beepPlayer.playWave("lose.wav");
                        wertePunkte(0, 1);
                    }

                    if (ballYpos == 0) {
                        schrittweiteYRichtung = schrittweiteYRichtung * -1;
                    }

                    if (ballYpos == 580) {
                        schrittweiteYRichtung = schrittweiteYRichtung * -1;
                    }

                    // Aktualisiere die Position des Balls um den Offset
                    ballXpos = ballXpos + schrittweiteXRichtung;
                    ballYpos = ballYpos + schrittweiteYRichtung;
                }
                repaint(); // Aktualsisiere das Bild
                Thread.currentThread().sleep(4); // Lege den Thread für fünf Millisekunden schlafen

            } catch (InterruptedException e) { // Fehlerbehandlung
                e.printStackTrace();
            }

        }

    }

    // Hier werden die Paddel gesteuert
    private void movePaddels() {
        if (((paddelLinksYpos) > 0) && paddelLinksHochBewegen == true) { // Wenn die oberste linke Ecke noch nicht erreicht wurde UND der Schläger sich nach oben bewegen soll
            paddelLinksYpos = paddelLinksYpos - 4;
        }

        if (((paddelLinksYpos) < 600 - 100) && paddelLinksRunterBewegen == true) {
            paddelLinksYpos = paddelLinksYpos + 4;
        }

        if (((paddelRechtsYpos) > 0) && paddelRechtsHochBewegen == true) {
            paddelRechtsYpos = paddelRechtsYpos - 4;
        }

        if (((paddelRechtsYpos) < 600 - 100) && paddelRechtsRunterBewegen == true) { // 100 von 600 abziehen, da das Paddel 100 Pixel lang ist.
            paddelRechtsYpos = paddelRechtsYpos + 4;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { // Wenn Taste gedrückt und wieder losgelassen wurde

    }

    @Override
    public void keyPressed(KeyEvent e) { // Wenn eine Taste gedrückt

        if ((e.getKeyCode() == KeyEvent.VK_SPACE) && (animationGestartet == false)) { // Wenn die Leertaste gedrückt wurde und die Animations nicht bereits läuft
            animationGestartet = true; // Merke, dass die Animation gestartet wurde
            spielerinfo.setVisible(false); // Blende Spielerinfo aus
            ballXpos = 200; // Setze Ball auf Ausgangsposition
            ballYpos = 100; // Setze Ball auf Ausgangsposition

        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // Wenn die Taste == untere Pfeiltaste
            // System.out.println("Untere Pfeiltaste");
            paddelRechtsRunterBewegen = true;

        } else if (e.getKeyCode() == KeyEvent.VK_UP) { // Wenn die Taste == obere Pfeiltaste
            // System.out.println("Obere Pfeiltaste");
            paddelRechtsHochBewegen = true;
        } else if (e.getKeyChar() == 'w') { // Wenn die Taste == obere Pfeiltaste
            // System.out.println("W pressed");
            paddelLinksHochBewegen = true;
            // }
        } else if (e.getKeyChar() == 's') { // Wenn die Taste == obere Pfeiltaste
            // System.out.println("W");
            paddelLinksRunterBewegen = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { // Wenn Taste losgelassen wurde

        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // Wenn die Taste == untere Pfeiltaste
            // System.out.println("Untere Pfeiltaste");
            paddelRechtsRunterBewegen = false;

        } else if (e.getKeyCode() == KeyEvent.VK_UP) { // Wenn die Taste == obere Pfeiltaste
            // System.out.println("Obere Pfeiltaste");
            paddelRechtsHochBewegen = false;
        } else if (e.getKeyChar() == 'w') { // Wenn die Taste == obere Pfeiltaste
            // System.out.println("W pressed");
            paddelLinksHochBewegen = false;
            // }
        } else if (e.getKeyChar() == 's') { // Wenn die Taste == obere Pfeiltaste
            // System.out.println("W");
            paddelLinksRunterBewegen = false;

        }

    }

    // Paddels und Ball auf Ausgangsposition und Punktestand aktualisieren
    public void wertePunkte(int punktSpieler1, int punktSpieler2) {
        animationGestartet = false;
        paddelLinksYpos = 250;
        paddelRechtsYpos = 250;
        spielerinfo.setVisible(true);
        spielstandSpieler1 = spielstandSpieler1 + punktSpieler1;
        spielstandSpieler2 = spielstandSpieler2 + punktSpieler2;
        spielstand.setText("Aktueller Spielstand " + spielstandSpieler1 + ":" + spielstandSpieler2);
    }

    public void neustart() { // Setze die Werte auf den Anfang zurück
        paddelLinksYpos = 250;
        paddelRechtsYpos = 250;
        spielerinfo.setVisible(true);
        spielstandSpieler1 = 0;
        spielstandSpieler2 = 0;
        spielstand.setText("Aktueller Spielstand 0 : 0");
        animationGestartet = false;

    }
}