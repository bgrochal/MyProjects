package pl.bgrochal.turtlepen.controllers;

import pl.bgrochal.turtlepen.models.*;
import pl.bgrochal.turtlepen.views.*;

import java.awt.event.*;


public class MainWindowController {

	private MainWindowView mainView;
	private CommandView commandView;
	private TurtleModel turtleModel;
	private ImageView imageView;
	
	public MainWindowController(MainWindowView mainView, ImageView imageView, CommandView commandView, TurtleModel turtleModel) {
		this.mainView = mainView;
		this.imageView = imageView;
		this.commandView = commandView;
		this.turtleModel = turtleModel;
		control();
	}
	
	private void control() {
		ActionListener fileExitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				System.exit(0);
			}
		};
		mainView.getFileExit().addActionListener(fileExitListener);
		
		ActionListener helpCommandsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				/* To implement */
			}
		};
		mainView.getHelpCommands().addActionListener(helpCommandsListener);
		
		ActionListener fileLangListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				turtleModel.setMessages();
				mainView.changeLanguage();
				imageView.changeLanguage();
				commandView.changeLanguage();
			}
		};
		mainView.getFileLang().addActionListener(fileLangListener);
	}
}