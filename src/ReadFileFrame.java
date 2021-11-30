package java_final_lab.webCrawling;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/***
 * this class means to show .txt content without any special function 
 * due to tabPane, it can show two file's content at the same time
 *
 */
public class ReadFileFrame extends JFrame implements ActionListener {

	private JPanel mainPanel = new JPanel();
	private JPanel sensWordJPanel = new JPanel();
	private JPanel webJPanel = new JPanel();
	private JTextArea sensWordArea = new JTextArea(15, 25);
	private JScrollPane htmlSPane = new JScrollPane(sensWordArea);
	private JTextArea webArea = new JTextArea(15, 25);
	private JScrollPane textSPane = new JScrollPane(webArea);
	private JTabbedPane tabPane = new JTabbedPane();
	private JButton[] buttons;
	private final JSplitPane buttonSplitPane_2 = new JSplitPane();
	private final JSplitPane buttonSplitPane_1 = new JSplitPane();

	public ReadFileFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.buttons = new JButton[4];
		buttons[2] = new JButton("Read");
		buttons[1] = new JButton("Clear");
		buttons[3] = new JButton("Clear");
		buttons[0] = new JButton("Read");
		buttonSplitPane_1.setResizeWeight(0.5);
		buttonSplitPane_2.setResizeWeight(0.5);
		mainPanel.setLayout(new BorderLayout());
		sensWordArea.setEditable(false);
		sensWordArea.setLineWrap(true);
		sensWordArea.setFont(new Font("Monaco", Font.PLAIN, 14));
		sensWordJPanel.setLayout(new BorderLayout());
		sensWordJPanel.add(htmlSPane, BorderLayout.CENTER);
		for (JButton jButton : buttons) {
			jButton.addActionListener(this);
		}
		htmlSPane.setColumnHeaderView(buttonSplitPane_1);
		buttonSplitPane_1.setLeftComponent(buttons[0]);
		buttonSplitPane_1.setRightComponent(buttons[1]);
		webArea.setFont(new Font("Monaco", Font.PLAIN, 14));
		webArea.setEditable(false);
		webArea.setLineWrap(true);
		webJPanel.setLayout(new BorderLayout());
		webJPanel.add(textSPane, BorderLayout.CENTER);

		textSPane.setColumnHeaderView(buttonSplitPane_2);
		buttonSplitPane_2.setLeftComponent(buttons[2]);
		buttonSplitPane_2.setRightComponent(buttons[3]);
		tabPane.add("Sensitive Word", sensWordJPanel);
		tabPane.add("Website", webJPanel);
		mainPanel.add(tabPane, BorderLayout.CENTER);
		this.add(mainPanel);
		this.setTitle("Sensword & Website");
		this.setLocation(450, 200);
		this.setSize(600, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton nowJButton = (JButton) e.getSource();
		if (nowJButton == buttons[0]) {
			String temp = fileReader();
			if (temp != null) {
				this.sensWordArea.append(temp);
			}
		} else if (nowJButton == buttons[1]) {
			this.sensWordArea.setText("");
		} else if (nowJButton == buttons[2]) {
			String temp = fileReader();
			if (temp != null) {
				this.webArea.append(temp);
			}
		} else if (nowJButton == buttons[3]) {
			this.webArea.setText("");
		}

	}

	public String fileReader() {
		JFileChooser chooseFrame = new JFileChooser();
		if (chooseFrame.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		File file = chooseFrame.getSelectedFile();
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (true) {
				String str = br.readLine();
				if (str == null) {
					break;
				}
				sb.append(str + "\n");
			}
			br.close();
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		return sb.toString();
	}

}
