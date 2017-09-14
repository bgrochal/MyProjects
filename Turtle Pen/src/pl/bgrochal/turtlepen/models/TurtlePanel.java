package pl.bgrochal.turtlepen.models;

import javax.swing.*;
import java.util.*;
import java.awt.*;


@SuppressWarnings("serial")
public class TurtlePanel extends JPanel {
	
	
	private java.util.List<Shapes> shapeList;
	private TurtleModel turtleModel;
	
	public TurtlePanel(TurtleModel turtleModel) {
		super();
		this.turtleModel = turtleModel;
		shapeList = new java.util.ArrayList<Shapes>();
		this.setPreferredSize(new Dimension(481, 481));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for (Iterator<Shapes> iterator = shapeList.iterator(); iterator.hasNext();) {
			Shapes drawing = (Shapes) iterator.next();
			g2d.setColor(drawing.getColor()); 
			g2d.draw(drawing.getShape());
        }
		
		if(turtleModel.toClear()) {
			g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
			turtleModel.setClear(false);
			shapeList.clear();
		}

	}

	public java.util.List<Shapes> getShapeList() {
		return this.shapeList;
	}
	
}
