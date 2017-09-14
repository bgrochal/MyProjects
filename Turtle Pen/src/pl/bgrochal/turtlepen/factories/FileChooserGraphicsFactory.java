package pl.bgrochal.turtlepen.factories;

import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import java.util.*;
import java.io.*;

@SuppressWarnings("serial")
public class FileChooserGraphicsFactory extends JFileChooser {

	private Map<String, String> availableExtensions;
	
	public FileChooserGraphicsFactory() {
		super();
		
		availableExtensions = new HashMap<String, String>();
		
		FileFilter jpgFile = new ExtensionFilter("*.jpg", "jpg");
		FileFilter bmpFile = new ExtensionFilter("*.bmp", "bmp");
		FileFilter pngFile = new ExtensionFilter("*.png", "png");
		this.addChoosableFileFilter(jpgFile);
		this.addChoosableFileFilter(bmpFile);
		this.addChoosableFileFilter(pngFile);
		
		this.setAcceptAllFileFilterUsed(false);
		this.setFileFilter(jpgFile);
	}
	
	public Map<String, String> getAvailableExtensions() {
		return this.availableExtensions;
	}

	private class ExtensionFilter extends FileFilter {
		
		private String description;
		private String extension;
		
		private ExtensionFilter(String description, String extension) {
			this.description = description;
			this.extension = extension;
			
			availableExtensions.put(description, extension);
		}
		
		@Override
		public boolean accept(File f) {
			if(f.isDirectory())
				return true;
			return (f.getAbsolutePath().endsWith(extension) || f.getAbsolutePath().endsWith(extension.toUpperCase()));
		}

		@Override
		public String getDescription() {
			return this.description;
		}	
		
	}
	
}
