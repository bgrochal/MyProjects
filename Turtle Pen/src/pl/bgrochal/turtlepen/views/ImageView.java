package pl.bgrochal.turtlepen.views;

import pl.bgrochal.turtlepen.factories.*;

import javax.swing.*;


public class ImageView implements ILanguageListener {

	private JButton saveImageButton;
	private MainWindowView mainView;
	
	public ImageView(MainWindowView mainView) {
		this.mainView = mainView;
		createView();
	}
	
	private void createView() {
		saveImageButton = new ButtonFactory((mainView.turtleModel.getMessages().getString("saveImage")), 20, 470, 200, 20);
		mainView.mainWindow.add(saveImageButton);
	}
	
	@Override
	public void changeLanguage() {
		this.saveImageButton.setText(mainView.turtleModel.getMessages().getString("saveImage"));
	}
	
	public JButton getSaveImageButton() {
		return this.saveImageButton;
	}

}
