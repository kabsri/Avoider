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

public class VerticalEnemy extends Enemy{
	
	public VerticalEnemy(int x, int y, boolean d){
		super(x, y, d);
	}
	
	//same as horizontal but vertical
	public void update(){
		yPos += direction*moveSpeed;
		if (direction == 1){
			if (yPos>750){
				yPos = -50;
			}
		} else {
			if (yPos<-50){
				yPos = 750;
			}
		}
	}
}