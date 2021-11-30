package java_final_lab.webCrawling;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * this class designed for Crawling 
 * it includes all Crawling methods
 * all Crawling thread extend it
 * import external java library - jsoup to parse HTML 
 *
 */
public class Crawling {
	private URL url;
	/***
	 * 
	 * @param website 
	 * this method is used to get html content
	 */
	public String crawlingWeb(String website) {
		StringBuilder content = new StringBuilder();//String container
		try {
			URL url = new URL(website);
			this.url = url;
			URLConnection connection = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));// use BufferReader to read url's content to get html content
			JOptionPane.showMessageDialog(null, "Crwaling Now!" + website);
			while (true) {
				String str = br.readLine();
				if (str == null) {
					break;
				}
				content.append(str + "\n");//append str to container
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Crwaling error in" + website);
		}
		return content.toString();
	}
	/***
	 * this method is used to filter text according to html content and get true text
	 * @return
	 */
	public String filterText() {
		String temp = "";
		try {
			Document doc = Jsoup.parse(this.url, 1000 * 10);// use jsoup to parse url if time exceeded 1000 * 10, IOException is thrown.

			temp = doc.body().text();//get website's body text.
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Sorry parse time out!");
			e.printStackTrace();
		}
		return splitString(temp, 40);
		
	}
/***
 * this method is designed for split long string to small string and append "\n" at the end of small string 
 * to make textarea scroll smoothly
 * @param str long string which will be executed
 * @param stringLenth
 * @return
 */
	 private String splitString(String str, int stringLenth) {
		int start = 0;
		int len = str.length();
		StringBuilder sb = new StringBuilder();//container
		while (true) {
			if (stringLenth + start < str.length()) {
				sb.append(str.substring(start, stringLenth + start) + "\n");
			}
			else {
				sb.append(str.substring(start, str.length() - 1));
				break;
			}
			start += stringLenth;
		}
		return sb.toString();
	}

}
