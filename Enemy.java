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

public class Enemy{
	
	protected int moveSpeed = 5;
	protected int direction;
	protected int size = 30;
	protected int red=200, green=100, blue=100;
	protected Color color;
	
	protected int xPos, yPos;
	
	//constructor
	public Enemy(int x, int y, boolean direct){
		xPos = x;
		yPos = y;
		color = new Color(red, green, blue);
		if (direct){
			direction = 1;
		} else {
			direction = -1;
		}
	}
	
	//get methods
	public int getCenterX(){
		return xPos+size/2;
	}
	
	public int getCenterY(){
		return yPos+size/2;
	}
	
	public int getSize(){
		return size;
	}
	
	//draw  method
	public void draw(Graphics2D g){
		g.setColor(color);
		drawEnemy(xPos, yPos, size, red, green, blue, g);
	}
	
	public void drawEnemy(int xPos, int yPos, int size, int red, int green, int blue, Graphics2D g){
		if (size>2){
			g.setColor(new Color(red, green, blue));
			g.drawOval(xPos, yPos, size, size);
			drawEnemy(xPos+2, yPos+2, size-4, (int)(red*0.75), (int)(green*0.5), (int)(blue*0.5), g);
		}
	}
	
	//empty update method for polymorphism
	public void update(){}
}