package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Version 0.0.6
 * Diese Klasse repräsentiert das Spielfenster in dem das JPanel enthalten ist, sowie die Punktestandanzeige.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame { // Spielfenster leitet von JFrame ab und ist somit ein JFrame.

    Spielfeld spielfeld; // Spielfeldinstanz

    JMenuBar menueleiste; // JMenuBar enthält alle Menüs
    JMenu menue; // JMenu kann Menüeinträge enthalten
    JMenuItem menueNeustart; // JMenuItem ist der "eigentliche Menüeintrag"

    public Spielfenster() { // In dem Konstruktor werden alle Konfigurationen vorgenommen. Er wird bei der Erstellung des Objekts EINMAL aufgerufen.

        // Erzeugen
        spielfeld = new Spielfeld(); // Ein neues Objekt vom Typ Spielfeld wird erstellt
        menueleiste = new JMenuBar(); // Neue Menüleiste, um Menüs aufzunehmen
        menue = new JMenu("Spiel"); // Menü mit dem Titel "Spiel"
        menueNeustart = new JMenuItem("Spiel neu starten"); // Neuer Menü-Eintrag zum Menü "Spiel"

        // Konfigurieren
        menueNeustart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                spielfeld.neustart();
            }
        });

        spielfeld.setFocusable(true); // Ermöglicht, dass die Keylistener auf die Tasteneingaben reagieren
        spielfeld.setBackground(new Color(0x212121)); // Setzt die die Hintergrundfarbe des Spielfelds auf Schwarz bzw. Hexadezimal 212121
        spielfeld.setPreferredSize(new Dimension(600, 600));
        this.setTitle("JPong - EiPNF WS 2015/2016 (v0.0.6)"); // Titel für das Spielfenster
        this.setSize(new Dimension(600, 600)); // Setzt die Größe des Spielfensters auf 600x600 Pixel
        this.setLocation(500, 100); // Setzt die Position des Spielfensters
        this.setVisible(true); // Mache das Fenster sichtbar
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Beende den Prozess/das Programm, wenn das Fenster geschlossen wird
        this.setJMenuBar(menueleiste); // Setze die Menüleiste für diese Klasse, also das Spielfenster

        // Hinzufügen
        spielfeld.addKeyListener(spielfeld); // Fügt die Keylistener dem Spielfeld hinzu. Besonderheit: Da das Spielfeld selbst die Keylistener implementiert, kann wird es hier sich selbst zugewiesen
        this.add(spielfeld); // Füge dem Spielfenster (this) das Spielfeld hinzu
        menue.add(menueNeustart); // Füge Menüeintrag dem Menü hinzu
        menueleiste.add(menue); // Füge Menü der Menüleiste hinzu
        this.add(spielfeld); // Füge diesem Spielefenster (this) das Spielfeld, also das JPanel hinzu

        // Am Ende ausführen, um die Inhalte auf dem JFrame "zusammen zu packen".
        this.pack();

    }
}
