package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Version 0.0.2
 * Diese Klasse repräsentiert das Spielfeld und enthält alle Elemente und die Steuerung der Padels.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Spielfeld extends JPanel implements KeyListener, Runnable { // Spielfeld ist ein JPanel und implementiert das Interface Keylistener

    int ballXpos; // Ballposition X-Achse
    int ballYpos; // Ballposition Y-Achse

    int paddelRechtsYpos; // Paddelposition Rechts Y-Achse
    int paddelLinksYpos; // Paddelposition Links Y-Achse

    public Spielfeld() { // Konstruktor; Bisher ohne weitere Funktion
        ballXpos = 50;
        ballYpos = 50;
        paddelLinksYpos = 250;
        paddelRechtsYpos = 250;
        new Thread(this).start(); //Starte den Animations-Thread --> run-Methode
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(ballXpos, ballYpos, 20, 20);

        g2d.setColor(Color.CYAN);

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, paddelLinksYpos, 20, 100);
        g2d.fillRect(580, paddelRechtsYpos, 20, 100);

    }

    @Override
    public void keyTyped(KeyEvent e) { // Wenn Taste gedrückt und wieder losgelassen wurde

    }

    @Override
    public void keyPressed(KeyEvent e) { // Wenn eine Taste gedrückt

        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // Wenn die Taste == untere Pfeiltaste
            System.out.println("Untere Pfeiltaste");
            if ((paddelRechtsYpos + 15) < 520) { // Grenze abfragen
                paddelRechtsYpos = paddelRechtsYpos + 15; // Ball bewegen
                repaint(); // Neu zeichnen
            }

        } else if (e.getKeyCode() == KeyEvent.VK_UP) { // Wenn die Taste == obere Pfeiltaste
            System.out.println("Obere Pfeiltaste");
            if ((paddelRechtsYpos - 15) > -20) {
                paddelRechtsYpos = paddelRechtsYpos - 15;
                repaint();
            }

        } else if (e.getKeyChar() == 'w') { // Wenn die Taste == obere Pfeiltaste
            System.out.println("W");
            if ((paddelLinksYpos - 15) > -20) {
                paddelLinksYpos = paddelLinksYpos - 15;
                repaint();
            }
        } else if (e.getKeyChar() == 's') { // Wenn die Taste == obere Pfeiltaste
            System.out.println("W");
            if ((paddelLinksYpos - 15) < 480) {
                paddelLinksYpos = paddelLinksYpos + 15;
                repaint();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) { // Wenn Taste losgelassen wurde
    }

    // Innerhalb der Run-Methode wird die Animation gesteuert.
    @Override
    //noinspection InfiniteLoopStatement
    public void run() {
        int schrittweite = 1; //Schrittweite des Balls

        while (true) {

            try {

                // Ballgrenzen abfragen
//                if ((ballXpos >= (580)) || (ballYpos >= 580)) {
//                    schrittweite = -1;
//                } else if ((ballXpos <= 1) || (ballYpos <= 1)) {
//                    schrittweite = +1;
//                }

                // Aktualisiere die Position des Balls um den Offset
                ballXpos = ballXpos + schrittweite;
                ballYpos = ballYpos + schrittweite;
                repaint(); //Aktualsisiere das Bild

                Thread.currentThread().sleep(1); // Lege den Thread für fünf Millisekunden schlafen

            } catch (InterruptedException e) { // Fehlerbehandlung
                e.printStackTrace();
            }

        }

    }
}
