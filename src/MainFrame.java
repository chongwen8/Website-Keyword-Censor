package java_final_lab.webCrawling;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
/***
 * this is main window. When you run program, this will display firstly.
 *
 */
public class MainFrame extends JFrame implements ActionListener{
	private JPanel textJPanel=new JPanel();
	private JLabel webJLabel=new JLabel("Website:");
	private JTextField siteField=new JTextField(25);
	private JScrollPane siteSPane=new JScrollPane(siteField);//makes text could scroll
	private JButton goCrawl=new JButton("Crawling");
	private JPanel mainJPanel;
	private JPanel buttonJPanel = new JPanel();
	private JPanel westJPanel = new JPanel();
	private JPanel eastJPanel = new JPanel();
	private JButton[] buttons;
	private TextAreaFrame textAreaFrame;
	public MainFrame(TextAreaFrame textAreaFrame) {
		this.buttons = new JButton[4];
		buttons[2] = new JButton("Write");
		buttons[1] = new JButton("Library");
		buttons[3] = new JButton("Read");
		buttons[0] = new JButton("Show");
		try {	
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());// set UI style looks like Mac os
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * This is the layout of the interface below 
		 */
		textJPanel.setLayout(new BorderLayout());
		webJLabel.setPreferredSize(new Dimension(70,30));
		siteSPane.setPreferredSize(new Dimension(300, 30));
		goCrawl.setPreferredSize(new Dimension(90, 30));
		textJPanel.add(webJLabel,BorderLayout.WEST);
		textJPanel.add(siteSPane,BorderLayout.CENTER);
		textJPanel.add(goCrawl, BorderLayout.EAST);
		mainJPanel = new JPanel();
		mainJPanel.setLayout(new BorderLayout());
		mainJPanel.add(textJPanel, BorderLayout.NORTH);
		mainJPanel.add(westJPanel, BorderLayout.WEST);
		mainJPanel.add(eastJPanel, BorderLayout.EAST);
		//binding button 
		for (JButton jButton : buttons) {
			jButton.addActionListener(this);
		}
		goCrawl.addActionListener(this);
		mainJPanel.add(buttonJPanel, BorderLayout.CENTER);
		// set each button same size and always lay it in the center with GridBagLayout
		GridBagLayout centraBagLayout = new GridBagLayout();
		centraBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		centraBagLayout.rowHeights = new int[]{0, 95, 0, 95, 0, 0};
		centraBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		centraBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		buttonJPanel.setLayout(centraBagLayout);
		
		GridBagConstraints gbcButton_0 = new GridBagConstraints();
		gbcButton_0.fill = GridBagConstraints.VERTICAL;
		gbcButton_0.insets = new Insets(0, 0, 5, 5);
		gbcButton_0.gridx = 1;
		gbcButton_0.gridy = 1;
		buttonJPanel.add(buttons[0], gbcButton_0);
		
		GridBagConstraints gbcButton_1 = new GridBagConstraints();
		gbcButton_1.fill = GridBagConstraints.VERTICAL;
		gbcButton_1.insets = new Insets(0, 0, 5, 5);
		gbcButton_1.gridx = 3;
		gbcButton_1.gridy = 1;
		buttonJPanel.add(buttons[1], gbcButton_1);
		
		GridBagConstraints gbcButton_2 = new GridBagConstraints();
		gbcButton_2.fill = GridBagConstraints.VERTICAL;
		gbcButton_2.insets = new Insets(0, 0, 5, 5);
		gbcButton_2.gridx = 1;
		gbcButton_2.gridy = 3;
		buttonJPanel.add(buttons[2], gbcButton_2);
		
		GridBagConstraints gbcButton__3 = new GridBagConstraints();
		gbcButton__3.insets = new Insets(0, 0, 5, 5);
		gbcButton__3.fill = GridBagConstraints.VERTICAL;
		gbcButton__3.gridx = 3;
		gbcButton__3.gridy = 3;
		buttonJPanel.add(buttons[3], gbcButton__3);
		this.setLocation(500, 200);
		this.setSize(700, 500);
		this.add(mainJPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.textAreaFrame = textAreaFrame; // transmit textFrame 
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton nowJButton = (JButton) e.getSource();
		if (nowJButton == this.buttons[0]) {
			textAreaFrame.setVisible(true);//show textFrame 
		}
		else if (nowJButton == this.buttons[1]) {
			LibraFrame libra = new LibraFrame(textAreaFrame);//show Library Frame
		}
		else if (nowJButton == this.buttons[2]) {
			InputFrame i = new InputFrame(); 
		}
		else if (nowJButton == this.buttons [3]) {
			ReadFileFrame r = new ReadFileFrame();
		}
		else if (nowJButton == this.goCrawl) {
			// clear textArea content and begin crawl 

			new Thread(new CrawlingOneThread(this, siteField.getText(), textAreaFrame.textArea, textAreaFrame.htmlArea)).start();// start this  thread 
			textAreaFrame.setVisible(true);//show textFrame 
		}
		
	}

}
