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

public class MenuPanel extends JPanel{
	
	JButton startButton, quitButton, instructionsButton;
	
	//constructor method
	public MenuPanel(Game game){
		setLayout(new FlowLayout());
		
		startButton = new JButton("START");
		startButton.setActionCommand("START");
		startButton.addActionListener(game);
		add(startButton);
		
		instructionsButton = new JButton("INSTRUCTIONS");
		instructionsButton.setActionCommand("INSTRUCTIONS");
		instructionsButton.addActionListener(game);
		add(instructionsButton);
		
		quitButton = new JButton("QUIT");
		quitButton.setActionCommand("QUIT");
		quitButton.addActionListener(game);
		add(quitButton);
	}
	
}