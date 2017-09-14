package pl.bgrochal.turtlepen.models;

import java.util.*;
import java.awt.*;


public class TurtleModel {

	private double posX;
	private double posY;
	private double angle;
	private boolean shown;
	private boolean clear;
	private Color penColor;
	private boolean invalidReported;
	private boolean existingVariable;
	private boolean variableNotExist;
	private boolean illegalVariableName;
	
	private Locale locale;
	private ResourceBundle messages;
	
	private Map<String, Variable> usersVariables = new HashMap<String, Variable>();
	
	
	public TurtleModel() {
		this.posX = 241;
		this.posY = 241;
		this.angle = 0;
		this.shown = true;
		this.clear = false;
		this.penColor = Color.BLACK;
		this.invalidReported = false;
		this.illegalVariableName = false;
		
		this.locale = new Locale("en");
		this.messages = ResourceBundle.getBundle("Messages", this.locale);
	}
	
	public double getPosX() {
		return this.posX;
	}
	
	public double getPosY() {
		return this.posY;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public Color getPenColor() {
		return this.penColor;
	}
	
	public boolean isShown() {
		return this.shown;
	}
	
	public boolean toClear() {
		return this.clear;
	}
	
	public boolean isInvalidReported() {
		return this.invalidReported;
	}
	
	public boolean isExistingVariable() {
		return this.existingVariable;
	}
	
	public boolean isVariableNotExist() {
		return this.variableNotExist;
	}
	
	public boolean isIllegalVariableName() {
		return this.illegalVariableName;
	}
	
	public ResourceBundle getMessages() {
		return this.messages;
	}
	
	public Map<String, Variable> getUsersVariables() {
		return this.usersVariables;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setShown(boolean shown) {
		this.shown = shown;
	}

	public void setPenColor(Color penColor) {
		this.penColor = penColor;
	}
	
	public void setMessages() {
		this.locale = new Locale(this.messages.getString("changeLang"));
		this.messages = ResourceBundle.getBundle("Messages", this.locale);
	}
	
	public void setClear(boolean value) {
		this.clear = value;
	}
	
	public void setInvalidReported(boolean value) {
		this.invalidReported = value;
	}
	
	public void setExistingVariable(boolean value) {
		this.existingVariable = value;
	}
	
	public void setVariableNotExist(boolean value) {
		this.variableNotExist = value;
	}
	
	public void setIllegalVariableName(boolean value) {
		this.illegalVariableName = value;
	}

}
