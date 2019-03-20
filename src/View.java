import javax.swing.*;

public class View extends JFrame

{

	JMenuItem Save = new JMenuItem("Save");
	JMenuItem Open = new JMenuItem("Open");
	JMenuItem Check_Spelling = new JMenuItem("Check Spelling");
	JMenuItem Add_word = new JMenuItem("Add word to dictionary");
	JTextPane TextArea = new JTextPane();

	private JMenuBar Menu_bar = new JMenuBar();
	private JMenu Menu = new JMenu("File");

	View(Controller controller)

	{

		this.add(TextArea);
		this.setJMenuBar(Menu_bar);
		this.setTitle("Text Editor");
		this.setVisible(true);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(controller);

		Menu_bar.add(Menu);
		Menu.add(Save);
		Menu.add(Open);
		Menu.add(Check_Spelling);
		Menu.add(Add_word);
		Save.addActionListener(controller);
		Open.addActionListener(controller);
		Check_Spelling.addActionListener(controller);
		Add_word.addActionListener(controller);

	}
}
