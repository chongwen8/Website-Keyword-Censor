package java_final_lab.webCrawling;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
/***
 * 
 * this class is a little complicated
 * it 's main purpose is to provide a graphical interface 
 * for users to count sensitive words for each file which might includes many websites
 */
public class LibraFrame extends JFrame implements ActionListener{

	private JPanel mainPanel = new JPanel();
	private JPanel fileJPanel = new JPanel();
	private JPanel tabJPanel = new JPanel();
	private JPanel contentJPanel = new JPanel();
	private JTabbedPane tabPane = new JTabbedPane();
	private JPanel westJPanel = new JPanel();
	private JButton[] buttons;
	private JPanel comboxJPanel = new JPanel();
	private JComboBox<String> selectComboBox = new JComboBox<String>();// purpose  is to clear all websites or sensitive words
	private List sensWordList = new List();
	private List websiteList = new List();
	private JScrollPane websiteFileJScrollPane = new JScrollPane(websiteList);
	private JScrollPane sensWordFileJScrollPane = new JScrollPane(sensWordList);
	private SensWordExe swe;
	private TextAreaFrame textAreaFrame;
	public LibraFrame(TextAreaFrame textAreaFrame) {
		// TODO Auto-generated constructor stub
		buttons = new JButton[4];
		buttons[0] = new JButton("Load Sensitive Word");
		buttons[1] = new JButton("Highlight");
		buttons[2] = new JButton("Load Website");
		buttons[3] = new JButton("Count");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		websiteList.addActionListener(this);
		mainPanel.setLayout(new BorderLayout());
		fileJPanel.setLayout(new BorderLayout());
		contentJPanel.setLayout(new BorderLayout());
		tabJPanel.setLayout(new BorderLayout());
		westJPanel.setLayout(new GridLayout(0, 1, 0, 20));
		for (JButton jButton : buttons) {
			jButton.addActionListener(this);
			westJPanel.add(jButton);
		}
		tabPane.add("Senstive Word", fileJPanel);
		mainPanel.add(westJPanel, BorderLayout.WEST);
		tabPane.add("Website", contentJPanel);
		tabJPanel.add(tabPane, BorderLayout.CENTER);
		mainPanel.add(tabJPanel, BorderLayout.CENTER);
		mainPanel.add(comboxJPanel, BorderLayout.NORTH);
		comboxJPanel.add(selectComboBox);
		contentJPanel.add(websiteFileJScrollPane, BorderLayout.CENTER);
		fileJPanel.add(sensWordFileJScrollPane, BorderLayout.CENTER);
		selectComboBox.addItem("Delete all Sensitive Words");
		selectComboBox.addItem("Delete all Webs");
		selectComboBox.setEditable(false);
		selectComboBox.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (selectComboBox.getSelectedItem().equals("Delete all Sensitive Words")) {
					SensWordExe.sensWordHashMap.clear();
					sensWordList.removeAll();
				}
				else if (selectComboBox.getSelectedItem().equals("Delete all Webs")) {
					SensWordExe.webArrayList.clear();
					websiteList.removeAll();
				}
			}
		});
		
		this.textAreaFrame = textAreaFrame;
		this.add(mainPanel);
		this.setTitle("Library");
		this.setLocation(400, 300);
		this.setSize(600, 500);
		this.addWindowListener(new WindowAdapter() { 
			@Override 
			// default dispose all resource and clear all websites and sensitive words while closing this window
			public void windowClosed(WindowEvent e) { 
				SensWordExe.sensWordHashMap.clear();
				sensWordList.removeAll();
				SensWordExe.webArrayList.clear();
				websiteList.removeAll();
			}
		});
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		swe = new SensWordExe();
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {// exe actions according to button
		// TODO Auto-generated method stub
		if (e.getSource() == websiteList) {
			String web = websiteList.getSelectedItem();
			if (web != null) {
				this.textAreaFrame.htmlArea.setText("");
				this.textAreaFrame.textArea.setText("");
				new Thread(new CrawlingOneThread(this, web, this.textAreaFrame.textArea, this.textAreaFrame.htmlArea)).start();	
				this.textAreaFrame.setVisible(true);
				return;
			}
		}
		JButton nowJButton = (JButton) e.getSource();
		if (nowJButton == this.buttons[0]) {
			swe.loadSensWords(sensWordList);
		}
		else if (nowJButton == this.buttons[1]) {
			if (this.textAreaFrame.textArea.getText().length() <= 0) {
				JOptionPane.showMessageDialog(this, "Sorry you haven't load website content");
				return;
			}
			swe.highlightSens(this.textAreaFrame.textArea);
		}
		else if (nowJButton == this.buttons[2]) {
			swe.loadWebsite(websiteList);
			
		}
		else if (nowJButton == this.buttons [3]) {
			analyzeWeb();
		}
		
	}
	public void analyzeWeb() {
		if (SensWordExe.sensWordHashMap.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Sorry you haven't load sens word files");
			return;
		}
		new Thread(new CrawlingAllThread()).start();//start a thread to crawl all websites
		
	}
}
