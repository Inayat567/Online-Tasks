// package textcollage;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SimpleFileChooser {

	private JFileChooser dialog;

	public void setDefaultDirectory() {
		if (dialog != null)
			dialog.setCurrentDirectory(null);
	}

	public void setDefaultDirectory(String directoryName) {
		if (dialog == null)
			dialog = new JFileChooser();
		dialog.setCurrentDirectory(new File(directoryName));
	}

	public void setDefaultDirectory(File directory) {
		if (dialog == null)
			dialog = new JFileChooser();
		dialog.setCurrentDirectory(directory);
	}

	public File getInputFile() {
		return getInputFile(null, null);
	}

	public File getInputFile(Component parent) {
		return getInputFile(parent, null);
	}

	public File getInputFile(Component parent, String dialogTitle) {
		if (dialog == null)
			dialog = new JFileChooser();
		if (dialogTitle != null)
			dialog.setDialogTitle(dialogTitle);
		else
			dialog.setDialogTitle("Select Input File");
		int option = dialog.showOpenDialog(parent);
		if (option != JFileChooser.APPROVE_OPTION)
			return null;
		File selectedFile = dialog.getSelectedFile();
		return selectedFile;
	}

	public File getOutputFile() {
		return getOutputFile(null, null, null);
	}

	public File getOutputFile(Component parent) {
		return getOutputFile(parent, null, null);
	}

	public File getOutputFile(Component parent, String dialogTitle) {
		return getOutputFile(parent, dialogTitle, null);
	}

	public File getOutputFile(Component parent, String dialogTitle, String defaultFile) {
		if (dialog == null)
			dialog = new JFileChooser();
		if (dialogTitle != null)
			dialog.setDialogTitle(dialogTitle);
		else
			dialog.setDialogTitle("Select Output File");
		if (defaultFile == null)
			dialog.setSelectedFile(null);
		else
			dialog.setSelectedFile(new File(defaultFile));
		while (true) {
			int option = dialog.showSaveDialog(parent);
			if (option != JFileChooser.APPROVE_OPTION)
				return null;
			File selectedFile = dialog.getSelectedFile();
			if (!selectedFile.exists())
				return selectedFile;
			else {
				int response = JOptionPane.showConfirmDialog(parent,
						"The file \"" + selectedFile.getName()
								+ "\" already exists.\nDo you want to replace it?",
						"Confirm Save",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.CANCEL_OPTION)
					return null;
				if (response == JOptionPane.YES_OPTION)
					return selectedFile;
			}
		}
	}

}
