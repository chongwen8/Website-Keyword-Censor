package java_final_lab.webCrawling;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * this class allow user to input websites and sensitive words 
 * which might be used in the future and store them to unique file
 *
 */
public class InputFrame extends JFrame implements ActionListener{

	private JPanel mainPanel = new JPanel();
	private JPanel htmlPanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JTextArea sensWordArea = new JTextArea(15, 25);
	private JScrollPane htmlSPane = new JScrollPane(sensWordArea);
	private JTextArea webArea = new JTextArea(15, 25);
	private JScrollPane textSPane = new JScrollPane(webArea);
	private JTabbedPane tabPane = new JTabbedPane();
	private JButton[] buttons;
	private JSplitPane buttonSplitPane_2 = new JSplitPane();
	private JSplitPane buttonSplitPane_1 = new JSplitPane();

	public InputFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.buttons = new JButton[4];
		buttons[2] = new JButton("Sure");
		buttons[1] = new JButton("Clear");
		buttons[3] = new JButton("Clear");
		buttons[0] = new JButton("Sure");
		buttonSplitPane_1.setResizeWeight(0.5);
		buttonSplitPane_2.setResizeWeight(0.5);
		mainPanel.setLayout(new BorderLayout());
		sensWordArea.setEditable(true);
		sensWordArea.setLineWrap(true);
		sensWordArea.setFont(new Font("Monaco", Font.PLAIN, 14));
		htmlPanel.setLayout(new BorderLayout());
		htmlPanel.add(htmlSPane, BorderLayout.CENTER);
		for (JButton jButton : buttons) {
			jButton.addActionListener(this);
		}
		htmlSPane.setColumnHeaderView(buttonSplitPane_1);
		buttonSplitPane_1.setLeftComponent(buttons[0]);
		buttonSplitPane_1.setRightComponent(buttons[1]);
		webArea.setFont(new Font("Monaco", Font.PLAIN, 14));
		webArea.setEditable(true);
		webArea.setLineWrap(true);
		textPanel.setLayout(new BorderLayout());
		textPanel.add(textSPane, BorderLayout.CENTER);

		textSPane.setColumnHeaderView(buttonSplitPane_2);
		buttonSplitPane_2.setLeftComponent(buttons[2]);
		buttonSplitPane_2.setRightComponent(buttons[3]);
		tabPane.add("Sensitive Word", htmlPanel);
		tabPane.add("Website", textPanel);
		mainPanel.add(tabPane, BorderLayout.CENTER);
		this.add(mainPanel);
		this.setTitle("Input Sensword & Website");
		this.setLocation(400, 250);
		this.setSize(600, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton nowJButton = (JButton) e.getSource();
		if (nowJButton == buttons[0]) {
			fileWriter(sensWordArea.getText(), "sens words");
			this.sensWordArea.setText("");

		} else if (nowJButton == buttons[1]) {
			this.sensWordArea.setText("");
		} else if (nowJButton == buttons[2]) {
			fileWriter(webArea.getText(), "web");
			this.webArea.setText("");
		} else if (nowJButton == buttons[3]) {
			this.webArea.setText("");
		}

	}
/**
 * write to file
 * @param text
 * @param type
 */
	public void fileWriter(String text, String type) {
		try {
			FileWriter fw = new FileWriter(new File("/Users/chong/code/Java_test/" + CrawlingAllThread.uniqueCurrentTimeMS() + type + ".txt"));
			String str = text;
			fw.write(str);
			fw.close();
			JOptionPane.showMessageDialog(this, "Write Successfully !");

		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

}
