import java.io.*;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class Model {

	ArrayList<String> Dictionary = new ArrayList<>();

	Model() throws IOException {

		Load_Dictionary();

	}

	private void Load_Dictionary() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Dictionary.txt"));

		String st;
		while ((st = br.readLine()) != null) {
			Dictionary.add(st);
		}
		System.out.println(Dictionary.size());

		br.close();

	}

	void Write_To_File(File file, JTextPane pane) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(pane.getText());
		writer.close();
	}

	void Add_word(String word) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Dictionary.txt", true));
		writer.write(word + "\n");
		writer.close();

		System.out.println("the word you added is: " + word);

		Dictionary.clear();
		Load_Dictionary();
	}

	void Read_File(File file, JTextPane pane) throws BadLocationException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		StyledDocument document = (StyledDocument) pane.getDocument();

		String st;
		while ((st = br.readLine()) != null) {
			document.insertString(document.getLength(), st + "\n", null);

		}

		br.close();
	}

}
