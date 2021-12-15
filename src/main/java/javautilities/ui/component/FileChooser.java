package javautilities.ui.component;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import javautilities.ui.defaults.KFileChooser;

public class FileChooser {

	private static JFileChooser instance;
	
	public static String getFileName() {
		return getFileName(null);
	}
	
	public static String getFileName(String folder) {
		return getFileName(folder, null);
	}

	public static String getFileName(String folder, FileFilter filter) {
		File file = getFile(folder, filter);
		if (file != null) {
			return file.getPath();
		}
		return null;
	}
	
	public static File getFile() {
		return getFile(null);
	}
	
	public static File getFile(String folder) {
		return getFile(folder, null);
	}
	
	public static File getFile(String folder, FileFilter filter) {
		JFileChooser fChooser = getInstance();
		if (folder == null) {
			folder = System.getProperty("user.dir");
		}
		fChooser.setCurrentDirectory(new File(folder));
		fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fChooser.setFileFilter(filter);
		int res = fChooser.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			return fChooser.getSelectedFile();
		}
		return null;
	}

	public static String getDirectoryName() {
		return getDirectoryName(null);
	}
	
	public static String getDirectoryName(String folder) {
		File file = getDirectory(folder);
		if (file != null) {
			return file.getPath();
		}
		return null;
	}
	
	public static File getDirectory() {
		return getDirectory(null);
	}
	
	public static File getDirectory(String folder) {
		JFileChooser fChooser = getInstance();
		if (folder == null) {
			folder = System.getProperty("user.dir");
		}
		fChooser.setCurrentDirectory(new File(folder));
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
