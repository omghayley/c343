/** Yuzhen Ye@Indiana University, Bloomington, C343, Spring 2014*/
package codonTable;

public class CodonTest {
	public static void main(String[] args) {
		String codonfile = null;
		for(int i = 0; i < args.length; i ++) {
			if(args[i].equals("-i") && (args.length > i + 1)) {
				codonfile = args[i + 1];
			}
		}
		if(codonfile == null) {
			System.out.println("Usage: CodonTest -i codonfile");
			System.exit(0);
		}
		CodonTable ctable = new CodonTable(codonfile);
		//test 1, expected output: I
		String aa = ctable.GetAA("ATT");
		System.out.println("ATT -> " + aa);
		//test 2, expected output: ILV
		String prot = ctable.GetProt("ATTCTTGTT");
		System.out.println("ATTCTTGTT -> " + prot);
	}

}
