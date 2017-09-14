package pl.bgrochal.turtlepen.factories;

import javax.swing.*;


@SuppressWarnings("serial")
public class MenuItemFactory extends JMenuItem {
	
	public MenuItemFactory(JMenu menu, String name) {
		super(name);
		this.setVisible(true);
		menu.add(this);
	}
	
}
