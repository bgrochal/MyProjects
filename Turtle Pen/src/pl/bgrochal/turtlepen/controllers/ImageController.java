package pl.bgrochal.turtlepen.controllers;

import pl.bgrochal.turtlepen.factories.*;
import pl.bgrochal.turtlepen.views.*;

import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;


public class ImageController {

	private MainWindowView mainView;
	private PanelView panelView;
	private ImageView imageView;
	
	public ImageController(ImageView imageView, PanelView panelView, MainWindowView mainView) {
		this.imageView = imageView;
		this.panelView = panelView;
		this.mainView = mainView;
		control();
	}
	
	private void control() {
		ActionListener saveImageListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileChooserGraphicsFactory saveImageDialog = new FileChooserGraphicsFactory();
				if(saveImageDialog.showSaveDialog(mainView.getMainWindow()) == JFileChooser.APPROVE_OPTION) {
					String path = saveImageDialog.getSelectedFile().getAbsolutePath();
					
					for(String descr : saveImageDialog.getAvailableExtensions().keySet()) {
						if(saveImageDialog.getFileFilter().getDescription().equals(descr) && !path.endsWith(saveImageDialog.getAvailableExtensions().get(descr)))
							path += ("." + saveImageDialog.getAvailableExtensions().get(descr));
					}
					
					try {
						BufferedImage image = new BufferedImage(panelView.getPanel().getWidth(), panelView.getPanel().getHeight(), BufferedImage.TYPE_INT_RGB);
						Graphics graphics = image.getGraphics();
						panelView.getPanel().paint(graphics);
						ImageIO.write(image, path.substring(path.length()-3, path.length()), new File(path));
					} 
					catch(IOException exc) {
						exc.printStackTrace();
					}
				}
			}
		};
		imageView.getSaveImageButton().addActionListener(saveImageListener);
	}

}
