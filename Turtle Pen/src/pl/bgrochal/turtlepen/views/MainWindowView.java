package pl.bgrochal.turtlepen.views;

import pl.bgrochal.turtlepen.factories.*;
import pl.bgrochal.turtlepen.models.*;

import javax.swing.*;


public class MainWindowView implements ILanguageListener {
	
	protected JFrame mainWindow;
	protected TurtleModel turtleModel;
	
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuBar menuBar;
	private JMenuItem fileExit;
	private JMenuItem fileLang;
	private JMenuItem helpCommands; 
	
	
	public MainWindowView(TurtleModel turtleModel) {
		mainWindow = new JFrame();
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
		
		this.turtleModel = turtleModel;
		
		createView();
	}
	
	private void createView() {
		mainWindow.setSize(800, 600);
		mainWindow.setTitle(turtleModel.getMessages().getString("programName"));
		mainWindow.setLocationRelativeTo(null);
		
		mainWindow.setLayout(null);
		
		menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);
		
		fileMenu = new MenuFactory(menuBar, turtleModel.getMessages().getString("menuFile"));
		helpMenu = new MenuFactory(menuBar, turtleModel.getMessages().getString("menuHelp"));
		
		fileLang = new MenuItemFactory(fileMenu, turtleModel.getMessages().getString("fileLang"));
		fileExit = new MenuItemFactory(fileMenu, turtleModel.getMessages().getString("fileExit"));
		helpCommands = new MenuItemFactory(helpMenu, turtleModel.getMessages().getString("helpCommands"));
	}
	
	@Override
	public void changeLanguage() {
		this.mainWindow.setTitle(turtleModel.getMessages().getString("programName"));
		this.fileMenu.setText(turtleModel.getMessages().getString("menuFile"));
		this.helpMenu.setText(turtleModel.getMessages().getString("menuHelp"));
		this.fileLang.setText(turtleModel.getMessages().getString("fileLang"));
		this.fileExit.setText(turtleModel.getMessages().getString("fileExit"));
		this.helpCommands.setText(turtleModel.getMessages().getString("helpCommands"));
	}
	
	public JMenuItem getFileExit() {
		return this.fileExit;
	}
	
	public JMenuItem getHelpCommands() {
		return this.helpCommands;
	}
	
	public JMenuItem getFileLang() {
		return this.fileLang;
	}
	
	public JFrame getMainWindow() {
		return this.mainWindow;
	}
	
}
