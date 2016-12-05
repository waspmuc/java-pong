package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Version 0.0.1
 * Diese Klasse repräsentiert das Spiel und enthält eine Instanz vom Typ Spielfenster. Weitere Funktionen sind noch nicht nötig.
 */

public class Spiel {

    Spielfenster gamewindow; //Deklarierung eines Objekts vom Typ Spielfenster

    public Spiel() { //Konstruktor
        gamewindow = new Spielfenster(); //erstellung eines neuen Spielfenster-Obekts
    }

}
