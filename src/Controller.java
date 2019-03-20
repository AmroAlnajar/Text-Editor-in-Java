import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Controller implements ActionListener, WindowListener {
	View view = new View(this);
	Model model = new Model();
	private JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	private boolean saved = false;
	private String currentText = "s";

	Controller() throws IOException {

	}

	private void Check_Before_Closing() {
		int safe = JOptionPane.showConfirmDialog(null, "Your text is not saved, are you sure you want to exit?",
				"Warning", JOptionPane.YES_NO_OPTION);

		if (safe == JOptionPane.YES_OPTION) {
			view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);// yes
		} else {
			view.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);// no
		}
	}

	private void Save_File(JTextPane pane) throws IOException {

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			selectedFile.createNewFile();
			model.Write_To_File(new File(selectedFile.getAbsolutePath()), pane);
			saved = true;
		}

		currentText = pane.getText();

	}

	private void Open_File(JTextPane pane) throws IOException, BadLocationException {
		pane.setText("");
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();

			model.Read_File(selectedFile, pane);
		}
	}

	private void Spell_Check(JTextPane TextArea) {

		String[] s = TextArea.getText().split("[ !.,]");

		view.TextArea.setText("");
		StyledDocument doc = TextArea.getStyledDocument();
		Style style = view.TextArea.addStyle("i'm a style", null);

		for (int i = 0; i < s.length; i++) {

			System.out.println("The words are: " + s[i]);

			if (model.Dictionary.contains(s[i])) {

				StyleConstants.setForeground(style, Color.GREEN);
				try {
					doc.insertString(doc.getLength(), s[i] + " ", style);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

				// TODO: remove color after checking spelling

			} else {

				StyleConstants.setForeground(style, Color.RED);
				try {
					doc.insertString(doc.getLength(), s[i] + " ", style);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

		}
	}

	public void Normal_mode(JTextPane TextArea) throws BadLocationException {
		StyledDocument doc = TextArea.getStyledDocument();
		Style style = view.TextArea.addStyle("i'm a style", null);
		StyleConstants.setForeground(style, Color.BLACK);
		doc.insertString(doc.getLength(), " ", style);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(view.Save)) {
			try {
				Save_File(view.TextArea);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource().equals(view.Open)) {
			try {
				Open_File(view.TextArea);
			} catch (IOException | BadLocationException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource().equals(view.Check_Spelling)) {
			Spell_Check(view.TextArea);

			try {
				Normal_mode(view.TextArea);
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}

		}

		if (e.getSource().equals(view.Add_word)) {
			String s = JOptionPane.showInputDialog("What word do you want to add?");
			try {
				model.Add_word(s);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {

		if (!saved & !view.TextArea.getText().equals("")) {

			Check_Before_Closing();

		}

		if (saved & !view.TextArea.getText().equals(currentText)) {
			Check_Before_Closing();
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
