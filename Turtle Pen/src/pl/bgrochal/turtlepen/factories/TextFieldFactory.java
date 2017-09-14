package pl.bgrochal.turtlepen.factories;

import javax.swing.*;


@SuppressWarnings("serial")
public class TextFieldFactory extends JTextField {
	
	public TextFieldFactory(int x, int y, int width, int height) {
		super();
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}
	
}
