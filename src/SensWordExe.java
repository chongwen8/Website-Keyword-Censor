package java_final_lab.webCrawling;

import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
/***
 * 
 * this class's purpose is to execute sensitive words and highlight them.
 *
 */
public class SensWordExe {
	private JFrame fatherFrame;
	static HashMap<String, Integer> sensWordHashMap = new HashMap<String, Integer>();// this hash was used to store sensitive words and is's times
	static ArrayList<File> webArrayList = new ArrayList<File>();//store websites for each file
	private Highlighter hl;
	DefaultHighlightPainter defauColor = new DefaultHighlightPainter(Color.RED);// default highlight color is red

	public void loadSensWords(List sensWordList) {
		JFileChooser chooseFrame = new JFileChooser();// chose file from PC' disk
		if (chooseFrame.showOpenDialog(fatherFrame) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = chooseFrame.getSelectedFile();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while (true) {
				String str = br.readLine();
				if (str == null) {
					break;
				}
				sensWordHashMap.put(str, 0);// default times is 0
				sensWordList.add(str);
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File is not exist");

			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Read file failure");
			e1.printStackTrace();
		}

	}

	public void loadWebsite(List websiteList) {
		JFileChooser chooseFrame = new JFileChooser();
		if (chooseFrame.showOpenDialog(fatherFrame) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = chooseFrame.getSelectedFile();
		webArrayList.add(file);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while (true) {
				String str = br.readLine();
				if (str == null) {
					break;
				}
				websiteList.add(str);
			}
			br.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File is not exist");

			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Read file failure");
			e1.printStackTrace();
		}
	}
/***
 * highlight sensitive words according to sensWordHashMap
 * @param textArea
 */
	public void highlightSens(JTextArea textArea) {
		if (sensWordHashMap.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Sorry you haven't load sens word files");
			return;
		}
		this.hl = textArea.getHighlighter();
		hl.removeAllHighlights();
		String text = textArea.getText();
		for (String str : sensWordHashMap.keySet()) {
			int index = 0;
			int len = str.length();
			while ((index = text.indexOf(str, index)) != -1) {
				try {
					hl.addHighlight(index, index + len, defauColor);
					index += len;
				} catch (BadLocationException e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}

		}
	}
}
