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

public class Game extends JFrame implements ActionListener{
	
	private Container c;
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	private GameOverPanel gameOverPanel;
	
	private CardLayout cards;
	
	//main method
	public static void main(String[] args){
		Game game = new Game("Marks Don't Matter");
	}
	
	//constructor method
	public Game(String title){
		super(title);
		setSize(800, 800);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		requestFocus();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cards = new CardLayout();
		setLayout(cards);
		c = getContentPane();
		
		menuPanel = new MenuPanel(this);
		add(menuPanel, "MENU");
		gamePanel = new GamePanel(this);
		add(gamePanel, "GAME");
		gameOverPanel = new GameOverPanel(this);
		add(gameOverPanel, "GAMEOVER");
	}
	
	//gameOver method
	public void gameOver(){
		cards.show(c, "GAMEOVER");
		gameOverPanel.readScores();
		JOptionPane.showMessageDialog(null, "You got "+gamePanel.getScore()+" points", "Game over", JOptionPane.INFORMATION_MESSAGE); //show score after losing
	}
	
	//actionPerformed method
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("START")||e.getActionCommand().equals("RETRY")){
			cards.show(c, "GAME");
			gamePanel.requestFocus();
			gamePanel.startGame();
		} else if (e.getActionCommand().equals("QUIT")){
			System.exit(0);
		} else if (e.getActionCommand().equals("INSTRUCTIONS")){
			JOptionPane.showMessageDialog(null, "Collect coins without hitting enemies to get points"
			+"\nWASD to move"+"\nPress Space to dash"+"\nClick on the screen to teleport"
			+"\nHold e to become invincible but unable to move"
			+"\nAll abilities have cooldowns which can be seen in the top left", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getActionCommand().equals("MENU")){
			cards.show(c, "MENU");
		}
	}
	
}