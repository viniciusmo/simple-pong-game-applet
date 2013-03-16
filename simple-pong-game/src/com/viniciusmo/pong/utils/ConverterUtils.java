package com.viniciusmo.pong.utils;

import java.awt.Rectangle;

import com.viniciusmo.pong.shapes.Shape;

public class ConverterUtils {

	public static Rectangle convertShapeToRectangleAWT(Shape shape) {
		Rectangle rec = new Rectangle();
		rec.width = shape.getWidth();
		rec.height = shape.getHeight();
		rec.x = shape.getX();
		rec.y = shape.getY();
		return rec;
	}
}
