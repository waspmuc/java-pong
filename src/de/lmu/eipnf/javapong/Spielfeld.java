package de.lmu.eipnf.javapong;

/**
 * @author Michael Kirsch
 * @Version 0.0.1
 * Diese Klasse repräsentiert das Spielfeld und enthält alle Elemente und die Steuerung der Padels.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Spielfeld extends JPanel implements KeyListener { //Spielfeld ist ein JPanel und implementiert das Interface Keylistener 

    public void Spielfeld() { //Konstruktor; Bisher ohne weitere Funktion

    }

    @Override
    public void keyTyped(KeyEvent e) { //Wenn Taste gedrückt und wieder losgelassen wurde

    }

    @Override
    public void keyPressed(KeyEvent e) { //Wenn eine Taste gedrückt
	if (e.getKeyCode() == KeyEvent.VK_LEFT) { //Wenn die Taste == linke Pfeiltaste
	    System.out.println("Linke Pfeiltaste");

	}

	else if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //Wenn die Taste == rechte Pfeiltaste
	    System.out.println("Rechte Pfeiltaste");
	}

	else if (e.getKeyCode() == KeyEvent.VK_DOWN) { //Wenn die Taste == untere Pfeiltaste
	    System.out.println("Untere Pfeiltaste");

	}

	else if (e.getKeyCode() == KeyEvent.VK_UP) { //Wenn die Taste == obere Pfeiltaste
	    System.out.println("Obere Pfeiltaste");

	}

    }

    @Override
    public void keyReleased(KeyEvent e) { //Wenn Taste losgelassen wurde
    }

}
