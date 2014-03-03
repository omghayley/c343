/** Yuzhen Ye@Indiana University, Bloomington, C343, Spring 2014*/
package codonTable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Scanner;

public class CodonTable {
	private Hashtable<String, String> codonTable;
	public CodonTable(String inputFile) {
		try {
			LoadTable(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void LoadTable(String inputFile) throws FileNotFoundException {
		codonTable = new Hashtable<String, String>();
		InputStream in = new FileInputStream(inputFile);
		Scanner scan = new Scanner(in);
		while(scan.hasNext()) {
			String aline = scan.nextLine();
			if (aline.startsWith("#")) continue;
			String[] subs = aline.split("\\s+");
			for(int i = 2; i < subs.length; i ++) {
				codonTable.put(subs[i], subs[1]);
			}
		}
		System.out.println("total codon: " + codonTable.size());
	}
	public String GetAA(String codon) {
		assert codon.length() == 3 : "Input codon needs to be of length 3" + codon;
		if(codonTable.containsKey(codon)) {
			return codonTable.get(codon);
		}
		else return "*";
	}
	public String GetProt(String DNA) {
		String prot = "";
		for(int i = 0; i < DNA.length(); i += 3) {
			String codon = DNA.substring(i, i + 3);
			if(codon.length() == 3) {
				prot += GetAA(codon);
			}
		}
		return prot;
	}
}
