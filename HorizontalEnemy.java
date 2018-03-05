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

public class HorizontalEnemy extends Enemy{
	
	//contructor
	public HorizontalEnemy(int x, int y, boolean d){
		super(x, y, d);
	}
	
	//update method
	public void update(){
		xPos += direction*moveSpeed; //move left or right
		if (direction==1){
			if (xPos>850){ //loop back onto screen
				xPos = -50;
			}
		} else {
			if (xPos<-50){
				xPos = 850;
			}
		}
	}
}