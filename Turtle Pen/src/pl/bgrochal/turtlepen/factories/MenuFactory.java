package pl.bgrochal.turtlepen.factories;

import javax.swing.*;


@SuppressWarnings("serial")
public class MenuFactory extends JMenu {

	public MenuFactory(JMenuBar menuBar, String name) {
		super(name);
		this.setVisible(true);
		menuBar.add(this);
	}

}
