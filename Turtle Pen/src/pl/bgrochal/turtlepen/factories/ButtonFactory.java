package pl.bgrochal.turtlepen.factories;

import javax.swing.*;


@SuppressWarnings("serial")
public class ButtonFactory extends JButton {

	public ButtonFactory(String text, int x, int y, int width, int height) {
		super(text);
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}

}
