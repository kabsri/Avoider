/*////////////////////
Kabilan Sriranjan
Ms. Strelkovska
ICS4U1
06/11/15
Summative
////////////////////*/

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Player{
	
	private int moveSpeed = 4;
	private int size = 30;
	private int red=100, green=100, blue=200;
	private Color color;
	private ArrayList<Enemy> enemies;
	private Coin coin;
	
	private int xPos, yPos;
	private int xVel, yVel;
	private boolean left, right, up, down;
	private int score;
	private boolean dead;
	
	private int teleportCD;
	private final int TELEPORT_CD = 400;
	private boolean justTeleported;
	private boolean invincible;
	private int invincibleCD;
	private final int INVINCIBLE_CD = 100;
	private int speedUp;
	private int speedUpCD;
	private final int SPEED_UP_CD = 30;
	
	//constructor method
	public Player(int x, int y, ArrayList<Enemy> enemies, Coin coin){
		color = new Color(red, green, blue);
		xPos = x;
		yPos = y;
		this.enemies = enemies;
		this.coin = coin;
		
		xVel = 0;
		yVel = 0;
		left = false;
		right = false;
		up = false;
		down = false;
		score = 0;
		dead = false;
		
		teleportCD = TELEPORT_CD;
		justTeleported = false;
		
		invincible = false;
		invincibleCD = INVINCIBLE_CD;
		
		speedUp = 0;
		speedUpCD = SPEED_UP_CD;
	}
	
	//collision method
	public boolean collision(){
		for (int i=0; i<enemies.size(); i++){ 
			int xDistance = getCenterX()-enemies.get(i).getCenterX();
			int yDistance = getCenterY()-enemies.get(i).getCenterY();
			int distance = (int)(Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2)));
			if (distance<=(size+enemies.get(i).getSize())/2){ //distance between centers of circles is less then the sum of their radii
				return true;
			}
		}
		return false;
	}
	
	//collect method
	public boolean collect(){
		int xDistance = getCenterX()-coin.getCenterX();
		int yDistance = getCenterY()-coin.getCenterY();
		int distance = (int)(Math.sqrt(Math.pow(xDistance,2)+Math.pow(yDistance,2)));
		if (distance<=(size+coin.getSize())/2){ //same as collision
			return true;
		}
		return false;
	}
	
	//draw method
	public void draw(Graphics2D g){
		g.setColor(color);
		g.fillOval(xPos, yPos, size, size);
		
		double speedUpRatio = (double)speedUpCD/SPEED_UP_CD;
		g.setColor(new Color(250-(int)(200*speedUpRatio), 250-(int)(200*speedUpRatio), 250-(int)(200*speedUpRatio))); //gradient for speedUp cooldown
		g.fillOval(5, 5, 10, 10);
		
		double teleportRatio = (double)teleportCD/TELEPORT_CD;
		g.setColor(new Color(250-(int)(200*teleportRatio), 250-(int)(200*teleportRatio), 250-(int)(200*teleportRatio))); //gradient for teleport coolddown
		g.fillOval(20, 5, 10, 10);
		
		double invincibleRatio = (double)invincibleCD/INVINCIBLE_CD;
		if (invincibleRatio<0){
			invincibleRatio = 0;
		}
		g.setColor(new Color(250-(int)(200*invincibleRatio), 250-(int)(200*invincibleRatio), 250-(int)(200*invincibleRatio))); //gradient for invincible cool down
		g.fillOval(35, 5, 10, 10);
		
		g.setColor(new Color(200, 200, 50)); //draw score
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString(""+score, 10, 760);
		
	}
	
	//get methods
	public int getCenterX(){
		return xPos+size/2;
	}
	
	public int getCenterY(){
		return yPos+size/2;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public int getScore(){
		return score;
	}
	
	//keyboard methods
	public void keyPressed(KeyEvent e){

		if (e.getKeyCode()==KeyEvent.VK_W){
			up = true;
		} 
		if (e.getKeyCode()==KeyEvent.VK_S){
			down = true;
		} 
		
		if (e.getKeyCode()==KeyEvent.VK_A){
			left = true;
		}
		else if (e.getKeyCode()==KeyEvent.VK_D){
			right = true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_SPACE){
			if (speedUpCD>=SPEED_UP_CD){
				speedUp = 10;
				speedUpCD = 0;
			}
		}

		
		if (e.getKeyCode()==KeyEvent.VK_E){
			if (invincibleCD>0){
				invincible = true;
			} else {
				invincible = false;
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode()==KeyEvent.VK_W){
			up = false;
	    } 
		if (e.getKeyCode()==KeyEvent.VK_S){
			down = false;
		} 
		
		if (e.getKeyCode()==KeyEvent.VK_A){
			left = false;
		}
		else if (e.getKeyCode()==KeyEvent.VK_D){
			right = false;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_E){
			invincible = false;
			color = new Color(100, 100, 200);
		}
	}
	
	//mouse methods
	public void mousePressed(MouseEvent e){
		if (e.getX()>0&&e.getX()+size<795&&e.getY()>0&&e.getY()+size<795)
		if (teleportCD>=200){
			justTeleported = true;
			teleportCD = 0;
			xPos = e.getX()-size/2;
			yPos = e.getY()-size/2;
		}
	}
	
	//update method
	public void update(){
		if (invincibleCD<=0){ //stall invincible cooldown if fully depleted
			invincible = false;
		}
		if (!invincible){ //can only act if not invincible
			color = new Color(100, 100, 200);
			if (invincibleCD<INVINCIBLE_CD){
				invincibleCD++;
			}
			if (!collision()){ //if it isn't colliding
				
				if (collect()){ //if coins is collected increase score and move coin
					score++;
					coin.reset();
				}
				xVel = 0;
				if (right){
					xVel = moveSpeed+speedUp;
				} else if (left){
					xVel = -1*(moveSpeed+speedUp);
				}
				if (xPos+xVel>0 && xPos+xVel+size<795){
					xPos += xVel;
				}
				
				yVel = 0;
				if (down){
					yVel = moveSpeed+speedUp;
				} else if (up){
					yVel = -1*(moveSpeed+speedUp);
				}
				if (yPos+yVel>0 && yPos+yVel+size<795){
					yPos += yVel;
				}
				
				if (speedUp > 0){
					speedUp--; //dash decays over time
					speedUpCD--;
				}
			} else {
				dead = true;
			}
		} else {
			color = new Color(150, 150, 250); //change colour if invincible
			invincibleCD--;
			if (invincibleCD<=0){
				invincibleCD = -50;
			}
		}
		if (justTeleported){
			teleportCD++;
			if (teleportCD>+TELEPORT_CD){
				justTeleported = false;
			}
		} else {
			if (teleportCD<TELEPORT_CD){
				teleportCD++;
			}
		}
		if (speedUpCD<=SPEED_UP_CD){
			speedUpCD++;
		}
	}
}