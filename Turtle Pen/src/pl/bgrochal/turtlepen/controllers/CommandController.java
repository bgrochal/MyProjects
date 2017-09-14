package pl.bgrochal.turtlepen.controllers;

import pl.bgrochal.turtlepen.exceptions.*;
import pl.bgrochal.turtlepen.factories.*;
import pl.bgrochal.turtlepen.models.*;
import pl.bgrochal.turtlepen.views.*;

import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import java.awt.*;
import java.io.*;


public class CommandController {
	
	private TurtleModel turtleModel;
	private MainWindowView mainView;
	private CommandView commandView;
	private PanelView panelView;
	
	private String[] splittedLine;
	private int index;
	
	
	public CommandController(MainWindowView mainView, CommandView commandView, PanelView panelView, TurtleModel turtleModel) {
		this.turtleModel = turtleModel;
		this.commandView = commandView;
		this.panelView = panelView;
		this.mainView = mainView;
		
		control();
	}
	
	private void control() {
		ActionListener commandLineListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				commandView.setTurtleInfo("");
				turtleModel.setInvalidReported(false);
				turtleModel.setExistingVariable(false);
				turtleModel.setVariableNotExist(false);
				turtleModel.setIllegalVariableName(false);
				
				String line = commandView.getCommandLine().getText();
				line = line.toLowerCase();
				while(line.contains("  "))
					line = line.replaceAll("  ", " ");
				
				for(int i=5; i<=500; i+=5) {
					Integer liczba = new Integer(i);
					if((i%10) == 0)
						line = "cc 255 0 0";
					else
						line = "cc 0 0 255";
					
					splittedLine = line.split(" ");
					parse();
					
					line = "dl " + liczba.toString() + "\n";
					splittedLine = line.split(" ");
					parse();
					line = "dc " + liczba.toString() + "\n";
					splittedLine = line.split(" ");
					parse();
					line = "rt 90";
					splittedLine = line.split(" ");
					parse();
					
				}
				
				splittedLine = line.split(" ");
				if(parse()) {
					for(int i=0; i<=index; i++) {
						commandView.setCommandHistory(splittedLine[i]);
						if(i != index)
							commandView.setCommandHistory(" ");
					}
					commandView.setCommandHistory(System.lineSeparator());
				}
				else if(!(turtleModel.isIllegalVariableName() || turtleModel.isVariableNotExist() || turtleModel.isExistingVariable()))
					reportInvalidInput();
				
				commandView.setCommandLine("");
			}
		};
		commandView.getCommandLine().addActionListener(commandLineListener);
		
		ActionListener saveCommandHistoryListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooserTextFactory saveCommandHistoryDialog = new FileChooserTextFactory();
				if(saveCommandHistoryDialog.showSaveDialog(mainView.getMainWindow()) == JFileChooser.APPROVE_OPTION) {
					String path = saveCommandHistoryDialog.getSelectedFile().getAbsolutePath();
					
					for(String descr : saveCommandHistoryDialog.getAvailableExtensions().keySet()) {
						if(saveCommandHistoryDialog.getFileFilter().getDescription().equals(descr) && !path.endsWith(saveCommandHistoryDialog.getAvailableExtensions().get(descr)))
							path += ("." + saveCommandHistoryDialog.getAvailableExtensions().get(descr));
					}
					
					try {
						File savingFile = new File(path);
						if(!savingFile.exists())
							savingFile.createNewFile();
						
						BufferedWriter writer = new BufferedWriter(new FileWriter(savingFile.getAbsoluteFile()));
						String content = commandView.getCommandHistory().getText();
						if(path.substring(path.length()-3, path.length()).equals("csv")) {
							content = content.replaceAll(System.lineSeparator(), ", ");
							content = content.substring(0, content.length()-2);				/* Removing last two ", " chars */
						}
							
						writer.write(content);
						writer.close();
						
					} 
					catch(IOException exc) {
						exc.printStackTrace();
					}
				}
			}
		};
		commandView.getSaveCommandHistoryButton().addActionListener(saveCommandHistoryListener);
	}
	
	/* Command line parser */
	
	private boolean parse() {
		try {
			index = 0;
			
			switch(splittedLine[index]) {
				/* Drawing shapes */
				case "dl":
					drawLine(convertToDouble(splittedLine[++index]));
					break;
				case "dc":
					drawCircle(convertToDouble(splittedLine[++index]));
					break;
				case "dp":
					drawPolygon(convertToInt(splittedLine[++index]), convertToDouble(splittedLine[++index]));
					break;
				case "cs":
					clearScreen();
					break;
				/* Turtle moving */
				case "rt":
					turnRight(convertToDouble(splittedLine[++index]));
					break;
				case "lt":
					turnLeft(convertToDouble(splittedLine[++index]));
					break;
				case "reset":
					resetTurtle();
					break;
				case "wait":
					sleepThread(convertToInt(splittedLine[++index]));
					break;
				/* Turtle attributes */
				case "cc":
					changeColor(convertToInt(splittedLine[++index]), convertToInt(splittedLine[++index]), convertToInt(splittedLine[++index]));
					break;
				case "pu":
					penUp();
					break;
				case "pd":
					penDown();
					break;
				/* Turtle info */
				case "pen":
					showPen();
					break;
				case "color":
					showColor();
					break;
				case "angle":
					showAngle();
					break;
				/* Variables */
				case "var":
					if(splittedLine.length > 2)
						initializeVariable(splittedLine[++index], convertToDouble(splittedLine[++index]));
					else
						initializeVariable(splittedLine[++index], 0);
					break;
				case "val":
					changeVariableValue(splittedLine[++index], convertToDouble(splittedLine[++index]));
					break;
				case "add":
					increaseVariableValue(splittedLine[++index], convertToDouble(splittedLine[++index]));
					break;
				case "sub":
					decreaseVariableValue(splittedLine[++index], convertToDouble(splittedLine[++index]));
					break;
				case "mul":
					multiplyVariableValue(splittedLine[++index], convertToDouble(splittedLine[++index]));
					break;
				case "div":
					divideVariableValue(splittedLine[++index], convertToDouble(splittedLine[++index]));
					break;
				case "mod":
					modulateVariableValue(splittedLine[++index], convertToDouble(splittedLine[++index]));
					break;
				case "cast":
					IntegerVariableValue(splittedLine[++index]);
					break;
				case "print":
					printValue(splittedLine[++index]);
					break;
				/* Loop */	
					
				/* Default */
				default:
					return false;
			}
		}
		catch(ArrayIndexOutOfBoundsException | IllegalArgumentException exc) {
			return false;
		}
		catch(VariableNotExistException exc) {
			commandView.setTurtleInfo(turtleModel.getMessages().getString("variableNotExist"));
			turtleModel.setVariableNotExist(true);
			return false;
		}
		
		/* 
		 * ArrayIndexOutOfBoundsException - not enough arguments
		 * IllegalArgumentException - contains NumberFormatException (invalid number argument), prevents from illegal color arguments
		 */
		
		return true;
	}
	
	private void reportInvalidInput() {
		commandView.setTurtleInfo(turtleModel.getMessages().getString("invalidInput"));
		turtleModel.setInvalidReported(true);
	}
	
	private double convertToDouble(String argument) {
		if(turtleModel.getUsersVariables().containsKey(argument))
			return turtleModel.getUsersVariables().get(argument).getValue();
		
		return Double.parseDouble(argument);
	}
	
	private int convertToInt(String argument) {
		if(turtleModel.getUsersVariables().containsKey(argument))
			return ((int)turtleModel.getUsersVariables().get(argument).getValue());
		
		return Integer.parseInt(argument);
	} 
	
	/* Drawing shapes */
	
	private void drawLine(double length) {
		/* If the argument is negative, method draws line backwards */
		if(turtleModel.isShown()) {
			panelView.getShapeList().add(new Shapes(new Line2D.Double(turtleModel.getPosX(), turtleModel.getPosY(), turtleModel.getPosX() - length * Math.sin(turtleModel.getAngle()), turtleModel.getPosY() - length * Math.cos(turtleModel.getAngle())), turtleModel.getPenColor()));
			panelView.getPanel().repaint();
		}
			
		turtleModel.setPosX(turtleModel.getPosX() - length * Math.sin(turtleModel.getAngle()));
		turtleModel.setPosY(turtleModel.getPosY() - length * Math.cos(turtleModel.getAngle()));
	}
	
	private void drawCircle(double radius) {
		if(Math.signum(radius) == -1)
			throw new IllegalArgumentException();
		
		if(turtleModel.isShown()) {
			panelView.getShapeList().add(new Shapes(new Ellipse2D.Double(turtleModel.getPosX() - radius, turtleModel.getPosY() - radius, 2*radius, 2*radius), turtleModel.getPenColor()));
			panelView.getPanel().repaint();
		}
	}
	
	private void drawPolygon(int sides, double length) {
		if(Math.signum(sides) == -1 || Math.signum(length) == -1)
			throw new IllegalArgumentException();
		
		double n = (double)sides;
		double radius = (Math.sin(((n-2)/n)*((Math.PI)/2)) / Math.sin((2*(Math.PI))/n)) * length;
		
		if(sides%2 == 0)
			turnLeft(180/sides);
		
		penUp();
		drawLine(radius);
		penDown();
		
		turnRight(180 - ((n-2)/n * 90));
		
		for(int i=0; i<sides; i++) {
			drawLine(length);
			turnRight(180 - ((n-2)/n * 180));
		}
		
		turnRight((n-2)/n * 90);
		penUp();
		drawLine(radius);
		penDown();
		turnRight(180);
		
		if(sides%2 == 0)
			turnRight(180/sides);
	}
	
	private void clearScreen() {
		turtleModel.setClear(true);
		panelView.getPanel().repaint();
	}
	
	/* Turtle moving */
	
	private void turnRight(double angle) {
		double temp = (turtleModel.getAngle() - (angle/180)*Math.PI)%(2*Math.PI);
		if(temp < 0)
			temp += (2*Math.PI);
		
		turtleModel.setAngle(temp);
	}
	
	private void turnLeft(double angle) {
		turtleModel.setAngle((turtleModel.getAngle() + (angle/180)*Math.PI)%(2*Math.PI));
	}
	
	private void resetTurtle() {
		turtleModel.setPosX(241);
		turtleModel.setPosY(241);
	}
	
	private void sleepThread(int time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException e) {
			System.exit(1);
		}
	}
	
	/* Turtle attributes */
	
	private void changeColor(int red, int green, int blue) {
		turtleModel.setPenColor(new Color(red, green, blue));
	}
	
	private void penUp() {
		turtleModel.setShown(false);
	}
	
	private void penDown() {
		turtleModel.setShown(true);
	}
	
	/* Turtle info */
	
	private void showPen() {
		if(turtleModel.isShown())
			commandView.setTurtleInfo("Pen is down.");
		else
			commandView.setTurtleInfo("Pen is up.");
	}
	
	private void showColor() {
		commandView.setTurtleInfo("R=" + turtleModel.getPenColor().getRed() + " G=" + turtleModel.getPenColor().getGreen() + " B=" + turtleModel.getPenColor().getBlue() + ".");
	}
	
	private void showAngle() {
		commandView.setTurtleInfo(turtleModel.getAngle()/Math.PI + "\u03C0 rad = " + turtleModel.getAngle()/(2*Math.PI)*360 + " deg");
	}
	
	/* Variables */
	
	private void initializeVariable(String name, double value) {
		if(!turtleModel.getUsersVariables().containsKey(name)) {
			try {
				Double.parseDouble(name);
				commandView.setTurtleInfo(turtleModel.getMessages().getString("illegalVariableName"));
				turtleModel.setIllegalVariableName(true);
				throw new IllegalArgumentException();
			}
			catch(NumberFormatException e) {
				turtleModel.getUsersVariables().put(name, new Variable(name, value));
			}
		}
		else {
			commandView.setTurtleInfo(turtleModel.getMessages().getString("existingVariable"));
			turtleModel.setExistingVariable(true);
			throw new IllegalArgumentException();
		}
	}
	
	private void changeVariableValue(String name, double value) throws VariableNotExistException {
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).setValue(value);
		else 
			throw new VariableNotExistException();
	}
	
	private void increaseVariableValue(String name, double value) throws VariableNotExistException {
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).add(value);
		else
			throw new VariableNotExistException();
	}
	
	private void decreaseVariableValue(String name, double value) throws VariableNotExistException {
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).sub(value);
		else
			throw new VariableNotExistException();
	}
	
	private void multiplyVariableValue(String name, double value) throws VariableNotExistException {
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).mul(value);
		else
			throw new VariableNotExistException();
	}
	
	private void divideVariableValue(String name, double value) throws VariableNotExistException {
		if(value == 0)
			throw new IllegalArgumentException();
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).div(value);
		else
			throw new VariableNotExistException();
	}
	
	private void modulateVariableValue(String name, double value) throws VariableNotExistException {
		if(value == 0)
			throw new IllegalArgumentException();
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).mod(value);
		else
			throw new VariableNotExistException();
	}
	
	private void IntegerVariableValue(String name) throws VariableNotExistException {
		if(turtleModel.getUsersVariables().containsKey(name))
			turtleModel.getUsersVariables().get(name).cast();
		else
			throw new VariableNotExistException();
	}
	
	private void printValue(String name) throws VariableNotExistException {
		if(turtleModel.getUsersVariables().containsKey(name))
			commandView.setTurtleInfo(name + " = " + turtleModel.getUsersVariables().get(name).getValue());
		else 
			throw new VariableNotExistException();
	}
	
	/* Loop */
	
	
}
