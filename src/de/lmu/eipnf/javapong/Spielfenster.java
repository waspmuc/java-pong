package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Version 0.0.1
 * Diese Klasse repräsentiert das Spielfenster in dem das JPanel enthalten ist, sowie die Punktestandanzeige. 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Spielfenster extends JFrame { //Spielfenster leitet von JFrame ab und ist somit ein JFrame.

    Spielfeld spielfeld; //Spielfeldinstanz
    JLabel score; //Anzeige der Punkte

    public Spielfenster() { //In dem Konstruktor werden alle Konfigurationen vorgenommen. Er wird bei der Erstellung des Objekts EINMAL aufgerufen.

	//Erzeugen
	spielfeld = new Spielfeld(); //Ein neues Objekt vom Typ Spielfeld wird erstellt
	score = new JLabel("Aktueller Spielstand 0 : 0 "); //Ein neues Objekt vom Typ JLabel wird erstellt und mit der Beschriftung initialisiert
	
	
	//Konfigurieren
	score.setFont(new Font("Calibri", Font.BOLD, 20)); //Setzt den Font der Score-Anzeige auf Calibri, Fett und Schriftgröße 20
	score.setForeground(new Color(0x0EE00)); //Setzt die Farbe auf Grün bzw. Hexadezimal 0EE00
	
	spielfeld.setFocusable(true); //Ermöglicht, dass die Keylistener auf die Tasteneingaben reagieren
	spielfeld.setBackground(new Color(0x212121)); //Setzt die die Hintergrundfarbe des Spielfelds auf Schwarz bzw. Hexadezimal 212121
	spielfeld.setPreferredSize(new Dimension(600,600));

	this.setSize(new Dimension(600, 600)); //Setzt die Größe des Spielfensters auf 600x600 Pixel	
	this.setLocation(500, 100); //Setzt die Position des Spielfensters
	this.setVisible(true); //Mache das Fenster sichtbar
	this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Beende den Prozess/das Programm, wenn das Fenster geschlossen wird


	//Hinzufügen
	spielfeld.addKeyListener(spielfeld); //Fügt die Keylistener dem Spielfeld hinzu. Besonderheit: Da das Spielfeld selbst die Keylistener implementiert, kann wird es hier sich selbst zugewiesen	
	spielfeld.add(score); // Füge die Scoreanzeige dem Spielfeld hinzu
	
	this.add(spielfeld); // Füge diesem Spielefenster (this) das Spielfeld, also das JPanel hinzu

	
	// Am Ende ausführen
	this.pack();
    }
}
