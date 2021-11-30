package java_final_lab.webCrawling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.atomic.AtomicLong;

import javax.swing.JOptionPane;
/***
 * this's class was designed to Crawling all websites in the file
 * and storage each file's statistic result in  corresponding file 
 * 
 *
 */
public class CrawlingAllThread extends Crawling implements Runnable{
	  static AtomicLong LAST_TIME_MS = new AtomicLong(); //get last time
	@Override
	// this thread is designed for Crawling all websites
	public void run() {
		// hint
		if (SensWordExe.webArrayList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Sorry, you haven't load website file");
			return;
		}
		for (File file : SensWordExe.webArrayList) { //read all files from hashmap
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String fileName = file.getName();
				//mac os path below, u make need change to your path
				FileWriter fw = new FileWriter("/Users/chong/code/Java_test/" + fileName.substring(0, fileName.length() - 4) +" count.txt");//corresponding file name which is crawlec
				fw.write("Sensitive word is below:" +"\n");
				int size = SensWordExe.sensWordHashMap.keySet().size();
				while (true) {
					String website = br.readLine();
					if (website == null) {
						break;
					}
					fw.write(website+"data see below: \n");
					//get website's text with father's method
					String html = crawlingWeb(website);
					String text = filterText();
					for (String word : SensWordExe.sensWordHashMap.keySet()) {
						int index = 0, count = 0, len = word.length();
						while ((index = text.indexOf(word, index)) != -1) {
							count += 1;//count the times of each website's sensitive word
							SensWordExe.sensWordHashMap.merge(word, 1, Integer::sum);
							index += len;
						}
						fw.write(word + " happen " + count + " times" + "\n");
					}
					fw.write("\n");
				}
				br.close();
				fw.write("This is all date. See below\n");
				for (String key : SensWordExe.sensWordHashMap.keySet()) {
					fw.write(key + " happens " + SensWordExe.sensWordHashMap.get(key) + " times\n");//total result
				}
				fw.close();
				JOptionPane.showMessageDialog(null, "Crawling is over");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Sorry, Crawling is fail");
				e.printStackTrace();
			}
		}
	}
	/***
	 * this method use time to create an unique id for each file 
	 * @return unique id according to time
	 */
	public static long uniqueCurrentTimeMS() {
	    long now = System.currentTimeMillis();
	    while(true) {
	        long lastTime = LAST_TIME_MS.get();
	        if (lastTime >= now)
	            now = lastTime+1;
	        if (LAST_TIME_MS.compareAndSet(lastTime, now))
	            return now;
	    }
	}
}
