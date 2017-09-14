package pl.bgrochal.turtlepen.models;

import java.awt.*;


public class Shapes {

	private Shape shape;
	private Color color;
	
	public Shapes(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public Color getColor() { 
		return this.color;
	}

}
