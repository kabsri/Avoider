/*////////////////////
Kabilan Sriranjan
Ms. Strelkovska
ICS4U1
06/11/15
Summative
////////////////////*/

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
	
	private Player player;
	private ArrayList<Enemy> enemies;
	private Coin coin;
	private Timer tm;
	private int time;
	private Game game;
	
	//constructor method
	public GamePanel(Game game){
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		this.game = game;
	}
	
	//paintComponent method
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		for (int i=0; i<enemies.size(); i++){
			enemies.get(i).draw(g2);
		}
		player.draw(g2);
		coin.draw(g2);
	}
	
	//startGame method
	public void startGame(){
		coin = new Coin();
		enemies = new ArrayList<Enemy>();
		player = new Player(400, 400, enemies, coin);
		time = 0;
		tm = new Timer(10, this);
		tm.start();		
	}
	
	//updateHighscores method
	public void updateHighscores(int score){
		int scores[] = new int[5];
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("Highscores.txt")));
			for (int i=0; i<scores.length; i++){ //save all scores to array
				scores[i] = Integer.parseInt(br.readLine());
			}
			br.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		scores[4] = Math.max(scores[4], score); //if new score is higher than lowest score replace lowest score
		sortScores(scores);
		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Highscores.txt")));
			for (int i=0; i<scores.length; i++){
				bw.write(""+scores[i]);
				bw.newLine();
			}
			bw.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//sortScores method
	public void sortScores(int[] scores){ //selection sorting
		int max;
		int maxIndex;
		for (int i=0; i<scores.length; i++){
			max = scores[i];
			maxIndex = i;
			for (int j=i; j<scores.length; j++){
				if (scores[j]>max){
					max = scores[j];
					maxIndex = j;
				}
			}
			scores[maxIndex] = scores[i];
			scores[i] = max;
		}
	}
	
	//get methods
	public int getScore(){
		return player.getScore();
	}
	
	//keyboard methods
	public void keyPressed(KeyEvent e){
		player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		player.keyReleased(e);
	}
	
	//mouse methods
	public void mousePressed(MouseEvent e){
		player.mousePressed(e);
	}
	
	//unused methods from interfaces
	public void keyTyped(KeyEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	
	//actionPerformed method
	public void actionPerformed(ActionEvent e){
		time++;
		if (time>=200){ //every 200 ticks, add on horizontal enemy and one vertical enemy, left/right and up/down are determined randomly
			if ((int)(Math.random()*2)==0){
				enemies.add(new HorizontalEnemy((int)(Math.random()*770)-790, (int)(Math.random()*770)+15, true));
			} else {
				enemies.add(new HorizontalEnemy((int)(Math.random()*770)-790, (int)(Math.random()*780)+15, false));
			}
			if ((int)(Math.random()*2)==0){
				enemies.add(new VerticalEnemy((int)(Math.random()*770)+15, (int)(Math.random()*770)-790, true));
			} else {
				enemies.add(new VerticalEnemy((int)(Math.random()*770)+15, (int)(Math.random()*770)-790, false));
			}
			time = 0;
		}
		for (int i=0; i<enemies.size(); i++){
			enemies.get(i).update();
		}
		player.update();
		repaint();
		if (player.isDead()){ //if game is over go to game over screen
			tm.stop();
			updateHighscores(player.getScore());
			game.gameOver();
		}
	}
}