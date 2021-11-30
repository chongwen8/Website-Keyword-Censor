package java_final_lab.webCrawling;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
/***
 * 
 * @see Crawling 
 * this's class extends Crawling and implements Runnable， so it could use father's method 
 *
 */
public class CrawlingOneThread extends Crawling implements Runnable {
	private String website;
	private JTextArea sensArea;
	private JTextArea textArea;
	private JFrame fatherJFrame;

	public CrawlingOneThread(JFrame fatherFrame, String website, JTextArea textArea, JTextArea sensArea) {
		// TODO Auto-generated constructor stub
		this.website = website;
		this.sensArea = sensArea;
		this.textArea = textArea;
		this.fatherJFrame = fatherFrame;
	}

	@Override
	public void run() {
		// hint 
		if (website == null) {
			JOptionPane.showMessageDialog(this.fatherJFrame, "Website is null");
			return;
		}
		// clear textArea content and begin crawl 
		sensArea.setText("");
		textArea.setText("");
		String html = crawlingWeb(website);// father's method
		if (html.length() != 0) {
			JOptionPane.showMessageDialog(this.fatherJFrame, "done!");
			String text = filterText();
			sensArea.append(html);
			textArea.append(text);
		}
		else {
			JOptionPane.showMessageDialog(this.fatherJFrame, "fail!");
		}
	}

}
