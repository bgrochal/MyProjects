package pl.bgrochal.turtlepen.views;

import pl.bgrochal.turtlepen.factories.*;

import javax.swing.*;
import java.awt.*;


public class CommandView implements ILanguageListener {

	private JTextField turtleInfo;
	private JTextField commandLine;
	private JScrollPane commandArea;
	private JTextArea commandHistory;
	private JButton saveCommandHistoryButton;
	
	private MainWindowView mainView;
	
	
	public CommandView(MainWindowView mainView) {
		this.mainView = mainView;
		createView();
	}
	
	private void createView() {
		commandHistory = new TextAreaFactory(true, false, Color.BLACK);
		commandArea = new ScrollPaneFactory(commandHistory, 20, 20, 200, 350);
		commandArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		commandArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainView.mainWindow.add(commandArea);
		
		commandLine = new TextFieldFactory(20, 400, 200, 20);
		mainView.mainWindow.add(commandLine);
		
		turtleInfo = new TextFieldFactory(20, 430, 200, 20);
		turtleInfo.setEditable(false);
		mainView.mainWindow.add(turtleInfo);
		
		saveCommandHistoryButton = new ButtonFactory(mainView.turtleModel.getMessages().getString("saveCommandHistory"), 20, 500, 200, 20);
		mainView.mainWindow.add(saveCommandHistoryButton);
	}
	
	@Override
	public void changeLanguage() {
		if(mainView.turtleModel.isInvalidReported())
			this.turtleInfo.setText(mainView.turtleModel.getMessages().getString("invalidInput"));
		if(mainView.turtleModel.isExistingVariable())
			this.turtleInfo.setText(mainView.turtleModel.getMessages().getString("existingVariable"));
		if(mainView.turtleModel.isVariableNotExist())
			this.turtleInfo.setText(mainView.turtleModel.getMessages().getString("variableNotExist"));
		if(mainView.turtleModel.isIllegalVariableName())
			this.turtleInfo.setText(mainView.turtleModel.getMessages().getString("illegalVariableName"));
		
		this.saveCommandHistoryButton.setText(mainView.turtleModel.getMessages().getString("saveCommandHistory"));
	}
	
	public JTextField getCommandLine() {
		return this.commandLine;
	}
	
	public JButton getSaveCommandHistoryButton() {
		return this.saveCommandHistoryButton;
	}
	
	public JTextArea getCommandHistory() {
		return this.commandHistory;
	}
	
	public void setCommandHistory(String command) {
		this.commandHistory.setText(this.commandHistory.getText() + command);
	}
	
	public void setCommandLine(String command) {
		this.commandLine.setText(command);
	}
	
	public void setTurtleInfo(String message) {
		this.turtleInfo.setText(message);
	}

}
