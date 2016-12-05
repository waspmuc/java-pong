package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Date 01.09.2015
 * @Version 0.0.5
 * <p>
 * Diese Klasse ist der Beginn des Spiels und erzeugt nur ein Objekt des Spiels.
 */

public class Main {

    public static void main(String[] args) {

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "JPong EiPNF WS15/16");
        new Spiel(); // Deklarierung und Erzeugung eines Objekts vom Typ Spiel.
    }
}
