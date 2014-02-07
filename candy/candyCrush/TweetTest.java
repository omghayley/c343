/** Yuzhen Ye@Indiana University, Bloomington, C343, Spring 2014*/
package candyCrush;

public class TweetTest {
	public static void main(String[] args) {
		String inputFile = null;
		String outputFile = null;
		for(int i = 0; i < args.length; i ++) {
			//use equals() (instead of ==) to check if two strings are the same
			if(args[i].equals("-i") && (args.length >= (i + 1))) {
				inputFile = args[i + 1];
			}
			else if(args[i].equals("-o") && (args.length >= (i + 1))) {
				outputFile = args[i + 1];
			}
		}
		if((inputFile == null) || (outputFile == null)) {
			System.out.println("Usage: TweetTest -i inputfile -o outputfile");
			System.exit(0);
		}
		System.out.println("InputFile: " + inputFile);
		Tweet twt = new Tweet(inputFile);
		twt.extractWords();
		twt.save2File(outputFile);
		System.out.println("results saved to " + outputFile);
	}
}
