import java.awt.*;
import java.awt.event.*;							// NEW #1 !!!!!!!!!!
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.swing.*;

/*********************************************************************************
 *
 * Peppered Moth Simulation
 * Casey McCullough
 * 4/18/16
 * 
 ***********************************************************************************/
public class Game extends JFrame 
						implements ActionListener, MouseListener, MouseMotionListener
						
{
	// DATA:
	
	private static final int MAX_WIDTH = 1000;		// Window size
	private static final int MAX_HEIGHT = 560;		// Window size
	private static final int TOP_OF_WINDOW = 22;	// Top of the visible window
	
	private static final int DELAY_IN_MILLISEC = 100;  // Time delay between updates
	private static final int GAME_TIME = 30000; // length of each round in milliseconds.
	
	private static final int NUM_WHITE_MOTHS = 10;
	private static final int NUM_BLACK_MOTHS = 10;
	
	
	private static final int TRUNK_WIDTH = MAX_WIDTH - 200;
	private static final int TRUNK_X = (MAX_WIDTH - TRUNK_WIDTH)  / 2;
	
	private static Random gen = new Random();
	private static int round = 1; // which round of game.
	
	private static AudioPlayer player = new AudioPlayer();
	
	private static Trunk trunk;
	private static Target theTarget;
	private static int totalTime = 0;
	
	private static int playerScore = 0;
	
	private static ArrayList <Moth> theMoths; 
	
	
	// METHODS:
	
	/**
	 * main -- Start up the window.
	 * 
	 * @param	args
	 */
	public static void main(String args[])
	{
		// Create the window.
		Game game = new Game();
	
		game.addMouseListener(game);
		game.addMouseMotionListener(game);	
	}
	
	/**
		A single feeding for one bird.  Eat as many moths as you can, either white or dark!
	 */
	public Game()
	{
		
		trunk = new Trunk (TRUNK_X, TOP_OF_WINDOW, TRUNK_WIDTH, MAX_HEIGHT, Color.black);
		theTarget = new Target (0, 0, 10, Color.yellow);
		theMoths = new ArrayList <Moth>();
		 
		 for (int i = 0; i < NUM_WHITE_MOTHS; i++)
		 {
			 int x = gen.nextInt (TRUNK_WIDTH - 2 * Moth.getSize()) + TRUNK_X + Moth.getSize(); // ensures that moths appear fully on screen
			 int y = gen.nextInt(MAX_HEIGHT);			 
			 Color transparentWhite = new Color (255, 255, 255, 200);
			 Moth m = new Moth (x, y, transparentWhite, theMoths, trunk);
			 theMoths.add(m);
		 }
		 
		 for (int i = 0; i < NUM_BLACK_MOTHS; i++)
		 {
			 int x = gen.nextInt (TRUNK_WIDTH - 2 * Moth.getSize()) + TRUNK_X + Moth.getSize(); // ensures that moths appear fully on screen
			 int y = gen.nextInt(MAX_HEIGHT);
			 
			 Color transparentDarkGray = new Color (64, 64, 64, 200);
			 Moth m = new Moth (x, y, transparentDarkGray, theMoths, trunk);
			 theMoths.add(m);
		 }
		 
		// Show the window 
		setSize(MAX_WIDTH, MAX_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		Timer clock= new Timer(DELAY_IN_MILLISEC, this);			
		clock.start();		
	}
	
	/**
	 * actionPerformed() is called automatically by the timer every time the requested 
	 * delay has elapsed.  
	 * 
	 * @param e		Contains info about the event that caused this method to be called
	 */
	public void actionPerformed(ActionEvent e)		// NEW #5 !!!!!!!!!!
	{
		// Move the moths.
		for (int j = 0; j < theMoths.size(); j++)
		{
			theMoths.get(j).act();
		}
		totalTime += DELAY_IN_MILLISEC;
			
		// Update the window.
		repaint();
	}
	
	/**
	 * paint 		draw the window
	 * 
	 * @param g		Graphics object to draw on
	 */

	public void paint(Graphics g)
	{
		// Clear the window.
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, MAX_WIDTH, MAX_HEIGHT);
		
		trunk.draw(g);
		
		for (int i = 0; i < theMoths.size(); i++)
		{
			theMoths.get(i).draw(g);
		}
		
		theTarget.draw(g);
		
		g.setColor(Color.YELLOW);
		g.drawString("Round: " + round, 20, 42);
		g.drawString("Time: " + ((double) totalTime /1000), 20, 62);
		g.drawString("Score: " + playerScore, 20, 82);

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		theTarget.moveLeft();
		player.play("ping.wav");
		
		for (int i = 0; i < theMoths.size(); i++)
		{
			if (theMoths.get(i).processClick(new Location (e.getX(), e.getY())))
				playerScore++;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		theTarget.moveRight();	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		theTarget.setX(e.getX());
		theTarget.setY(e.getY());
	}	
	

}
