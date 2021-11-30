package java_final_lab.webCrawling;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
/***
 * The following method is used to demonstrate text area crawling from website
 * use tabPane to show  text area which might be more elegant
 *
 */
public class TextAreaFrame extends JFrame{
	// plan UI arrangement
	private JPanel mainPanel=new JPanel();
	private JPanel htmlPanel=new JPanel();
	private JPanel textPanel=new JPanel();
	JTextArea htmlArea=new JTextArea(15, 25);
	private JScrollPane htmlSPane=new JScrollPane(htmlArea);
	JTextArea textArea=new JTextArea(15,25);
	private JScrollPane textSPane=new JScrollPane(textArea);
	private JTabbedPane tabPane=new JTabbedPane();

	public TextAreaFrame() {
		// initially not show this frame visible
	try {	
		// set UI style looks like Mac os
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
		e.printStackTrace();
	}
	this.setTitle("Html & Text Area");
	this.setLocation(400, 200);
	this.setSize(600, 500);
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// dispose resource if closing window
	mainPanel.setLayout(new BorderLayout());
	htmlArea.setEditable(false);
	htmlArea.setLineWrap(true);
	htmlArea.setFont(new Font("Monaco", Font.PLAIN, 14));
	htmlPanel.setLayout(new BorderLayout());
	htmlPanel.add(htmlSPane,BorderLayout.CENTER);
	textArea.setFont(new Font("Monaco", Font.PLAIN, 14));
	textArea.setEditable(false);
	textArea.setLineWrap(true);
	textPanel.setLayout(new BorderLayout());
	textPanel.add(textSPane,BorderLayout.CENTER);
	tabPane.add("Html", htmlPanel);
	tabPane.add("Text",textPanel);
	mainPanel.add(tabPane,BorderLayout.CENTER);
	this.add(mainPanel);
	}

}
