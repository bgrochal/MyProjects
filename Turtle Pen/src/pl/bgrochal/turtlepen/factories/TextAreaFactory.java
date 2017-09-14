package pl.bgrochal.turtlepen.factories;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class TextAreaFactory extends JTextArea {

	public TextAreaFactory(boolean wrapable, boolean editable, Color fontColor) {
		super();
		this.setLineWrap(true);
		this.setEditable(false);
		this.setForeground(fontColor);
		this.setVisible(true);
	}
	
}
