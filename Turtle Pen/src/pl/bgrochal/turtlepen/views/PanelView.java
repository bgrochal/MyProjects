package pl.bgrochal.turtlepen.views;

import pl.bgrochal.turtlepen.models.*;

import javax.swing.*;
import java.awt.*;


public class PanelView {

	private TurtlePanel panel;
	private MainWindowView mainView;
	
	
	public PanelView(MainWindowView mainView) {
		this.mainView = mainView;
		createView();
	}
	
	private void createView() {
		panel = new TurtlePanel(mainView.turtleModel);		/* Without factory, internal use only */
		panel.setBounds(280, 30, 481, 481);
		panel.setBackground(Color.WHITE);
		panel.setVisible(true);
		mainView.mainWindow.add(panel);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public java.util.List<Shapes> getShapeList() {
		return panel.getShapeList();
	}

}
