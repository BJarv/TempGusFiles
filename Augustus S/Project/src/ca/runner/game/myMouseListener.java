package ca.runner.game;


import java.awt.Event;
import java.awt.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.event.MouseInputAdapter;

public class myMouseListener extends MouseInputAdapter implements MouseListener{
	private boolean mouseDown = false;
	private static int mouseX;
	private static int mouseY;
	private Point mousePoint;

	
	public myMouseListener(Game game) {
		game.addMouseListener(this);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
		mouseX = e.getXOnScreen();
		mouseY = e.getYOnScreen();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}

	public boolean getMouseState() {
		return mouseDown;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getPoint());
		mousePoint = e.getPoint();
		mouseY = e.getY();
		mouseX = e.getX();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void getMouseX(MouseEvent e) {
		 mouseX = e.getX();
		 
	}
	
	public void getMouseY(MouseEvent e) {
		 mouseY = e.getY();
		 //System.out.println(mouseY);
	}
	
	public void mouseMoved(MouseEvent e) {
		mouseY = e.getY();
		mouseX = e.getX();
		//System.out.println(mouseX);
	}
	
	public static int getMouseX() {
		return mouseX;
	}
	
	public static int getMouseY() {
		return mouseY;
	}
	
	public Point getPoint() {
		return mousePoint;
	}
}