/*////////////////////
Kabilan Sriranjan
Ms. Strelkovska
ICS4U1
06/11/15
Summative
////////////////////*/

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameOverPanel extends JPanel{
	
	private JButton retryButton, menuButton;
	private TextArea textArea;
	private int[] scores = new int[5];
	private String scoresString = "";
	
	//constructor method
	public GameOverPanel(Game game){
		setLayout(new BorderLayout());
		
		retryButton = new JButton("RETRY");
		retryButton.setActionCommand("RETRY");
		retryButton.addActionListener(game);
		add(retryButton, BorderLayout.NORTH);
		
		menuButton = new JButton("MENU");
		menuButton.setActionCommand("MENU");
		menuButton.addActionListener(game);
		add(menuButton, BorderLayout.SOUTH);
		
		textArea = new TextArea();
		textArea.setEditable(false);
		add(textArea, BorderLayout.CENTER);
	}
	
	//readScores method
	public void readScores(){
		try{
			scoresString = "";
			BufferedReader br = new BufferedReader(new FileReader(new File("Highscores.txt")));
			for (int i=0; i<scores.length; i++){
				scoresString += br.readLine()+"\n";
			}
			br.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		textArea.setText(null);
		textArea.setText(scoresString);
	}

}