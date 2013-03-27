package com.viniciusmo.pong.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.viniciusmo.pong.shapes.Shape;

public class BarControllerMovement implements KeyListener {

	private Shape shape;
	private static final int VELOCITY_OF_MOVEMENT = 70;
	private static final int UP_ARROW_KEY_CODE = 40;
	private static final int DOWN_ARROW_KEY_CODE = 38;

	public BarControllerMovement(Shape shape) {
		this.shape = shape;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case UP_ARROW_KEY_CODE:
			shape.setY(shape.getY() + VELOCITY_OF_MOVEMENT);
			break;
		case DOWN_ARROW_KEY_CODE:
			shape.setY(shape.getY() - VELOCITY_OF_MOVEMENT);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}
