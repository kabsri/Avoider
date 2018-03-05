/*////////////////////
Kabilan Sriranjan
Ms. Strelkovska
ICS4U1
06/11/15
Summative
////////////////////*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Coin{
	
	private int xPos, yPos;
	private int size=20;
	private int r=200, g=200, b=50;
	private Color color;
	
	public Coin(){
		color = new Color(r, g, b);
		xPos = (int)(Math.random()*440)+20;
		yPos = (int)(Math.random()*440)+20;
	}
	
	public void draw(Graphics2D g){
		g.setColor(color);
		g.fillOval(xPos, yPos, size, size);
	}
	
	public void reset(){
		xPos = (int)(Math.random()*570)+85; //randomly place near center
		yPos = (int)(Math.random()*570)+85;
	}
	
	public int getCenterX(){
		return xPos+size/2;
	}

	public int getCenterY(){
		return yPos+size/2;
	}
	
	public int getSize(){
		return size;
	}
}