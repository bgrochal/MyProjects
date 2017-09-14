package pl.bgrochal.turtlepen;

import pl.bgrochal.turtlepen.controllers.*;
import pl.bgrochal.turtlepen.models.*;
import pl.bgrochal.turtlepen.views.*;

import javax.swing.*;


public class TurtlePen {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			@SuppressWarnings("unused")
			public void run() { 
				TurtleModel mainModel = new TurtleModel();
				
				MainWindowView mainView = new MainWindowView(mainModel);
				CommandView commandView = new CommandView(mainView);
				ImageView imageView = new ImageView(mainView);
				PanelView panelView = new PanelView(mainView);
				
				MainWindowController mainController = new MainWindowController(mainView, imageView, commandView, mainModel);
				CommandController commandController = new CommandController(mainView, commandView, panelView, mainModel);
				ImageController imageController = new ImageController(imageView, panelView, mainView);
			}	
		});
	}

}
