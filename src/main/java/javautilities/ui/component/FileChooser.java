package javautilities.ui.component;

import java.io.File;

import javax.swing.JFileChooser;

import javautilities.ui.defaults.KFileChooser;

public class FileChooser {

	private static JFileChooser instance;

	public static String getFileName() {
		File file = getFile();
		if (file != null) {
			return file.getPath();
		}
		return null;
	}
	
	public static String getDirectoryName() {
		File file = getDirectory();
		if (file != null) {
			return file.getPath();
		}
		return null;
	}
	
	public static File getFile() {
		JFileChooser fChooser = getInstance();
		fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int res = fChooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			return fChooser.getSelectedFile();
		}
		return null;
	}
	
	public static File getDirectory() {
		JFileChooser fChooser = getInstance();
		fChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int res = fChooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			return fChooser.getSelectedFile();
		}
		return null;
	}
	
	
	public static JFileChooser getInstance() {
		if (instance == null) {
			instance = new KFileChooser();
		}
		return instance;
	}
	
}
