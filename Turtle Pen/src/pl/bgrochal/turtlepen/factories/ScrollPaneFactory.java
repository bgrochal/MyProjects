package pl.bgrochal.turtlepen.factories;

import javax.swing.*;


@SuppressWarnings("serial")
public class ScrollPaneFactory extends JScrollPane {
	
	public ScrollPaneFactory(JTextArea area, int x, int y, int width, int height) {
		super(area);
		this.setBounds(x, y, width, height);
		this.setVisible(true);
	}
	
}
