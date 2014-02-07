/** Yuzhen Ye@Indiana University, Bloomington, C343, Spring 2014*/
package candyCrush;
import java.util.Vector;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Tweet {
	//if size of the tweets/words/counts is known, can use array instead
	private Vector<String> tweets;
	private Vector<String> words;
	private Vector<Integer> counts;
	Tweet() {
		tweets = null;
		words = null;
		counts = null;
	}
	Tweet(String inputFile) {
		// declares a Vector of size 100, increase the space by 20
		this();
		tweets = new Vector<String>(100, 20); 
		try {
			readFile(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	private void readFile(String inputFile) throws FileNotFoundException {
		//create an input stream object to read the inputFile
		InputStream in = new FileInputStream(inputFile);
		//use scanner
		Scanner scan = new Scanner(in);
		//read the file line by line
		while(scan.hasNext()) {
			//convert to lower case
			tweets.add(scan.nextLine().toLowerCase());
		}
		//close the file
		scan.close();
	}
	public void extractWords() {
		words = new Vector<String> (100, 20);
		counts = new Vector<Integer> (100, 20);
		for(int i = 0; i < tweets.size(); i ++) {
			String atweet = tweets.elementAt(i);
			String reg = "\\w+";
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(atweet);
			while(m.find()) {
				String aword = m.group(0);
				if(words.contains(aword)) {
					int idx = words.indexOf(aword);
					counts.setElementAt(counts.elementAt(idx) + 1, idx);
				}
				else {
					words.add(aword);
					counts.add(1);
				}
			}
		}
	}
	public void display() {
		for(int i = 0; i < words.size(); i ++)
			System.out.println(words.elementAt(i) + " " + counts.elementAt(i));
	}
	public void save2File(String outputFile) {
		try {
			//create a File
			File file = new File(outputFile);
			//create BufferedWriter
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			for(int i = 0; i < words.size(); i ++)
				output.write(words.elementAt(i) + " " + counts.elementAt(i) + "\n");
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
