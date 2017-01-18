package de.lmu.eipnf.javapong;

import com.sun.prism.paint.Color;

/**
 * @author wasp
 *
 */
public class Ball {
    
    private int x_position;
    private int y_position;
    
    private int durchmesser;
    
    public Ball(){
	x_position = 50;
	y_position = 50;
	durchmesser = 20;;
    }

    public int getX_position() {
        return x_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public int getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }


    
    
    
}
