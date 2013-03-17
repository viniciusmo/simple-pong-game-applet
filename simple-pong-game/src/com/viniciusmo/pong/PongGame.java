package com.viniciusmo.pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.viniciusmo.pong.audio.PlayerMP3;
import com.viniciusmo.pong.game.GameArea;
import com.viniciusmo.pong.game.Moveable;
import com.viniciusmo.pong.shapes.Circle;
import com.viniciusmo.pong.shapes.Rectangle;
import com.viniciusmo.pong.shapes.Shape;

public class PongGame extends Applet implements MouseMotionListener, Runnable {

	private static final long serialVersionUID = 1L;
	private Shape bar;
	private Shape ball;
	private GameArea area;

	public void init() {
		area = new GameArea(800, 500);
		bar = new Rectangle(200, 0, 10, 150);
		ball = new Circle(area, 100, 100, 15, 15);
		setSize(area.getWidth(), area.getHeight());
		setBackground(Color.black);
		addMouseMotionListener(this);
		new Thread(this).start();
	}

	public void paint(Graphics g) {
		bar.draw(g);
		ball.draw(g);
		g.drawString("Score: " + 10, 20, 20);
		g.setColor(Color.WHITE);
		g.drawString("by viniciusmo", getWidth() - 100, 20);

	}

	public void mouseMoved(MouseEvent e) {
		bar.setY(e.getY());
	}

	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void run() {
		while (true) {
			ball.updatePosition();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

			}
			if (bar.collides(ball)) {
				Moveable mv = (Moveable) ball;
				mv.changeDirectionX();
				PlayerMP3 player = new PlayerMP3("../res/pong.mp3");
				player.play();
			}
			repaint();
		}
	}
}
