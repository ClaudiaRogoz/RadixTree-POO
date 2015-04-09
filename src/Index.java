
import java.util.ArrayList;
import java.util.List;

/**
 * This execution entry point class handles the parsing, indexing and lookup of words in a text.
 */
public class Index {

	/**
	 * Execution entry point.
	 *
	 * @param args two strings representing the name of the file that contains the text to index,
	 * and the name of the file containing words to lookup in the text.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java -cp <classpath> Index <text file> <word file>");
			System.exit(1);
		}

		String word;
		int i = 0;
		
		RadixTree rTree = new RadixTree();
        RadixNode tree = new RadixNode("\0");
		
        List<Integer> list = new ArrayList<Integer>();
        
		FileParser textFile = new FileParser(args[0]);
		textFile.open();
		
		while ((word = textFile.getNextWord()) != null) {			
	        if (i != 0)
	        	list.remove(0);
	        list.add(i);
	        rTree.insertWord(tree,word,list,97);
			i++;
		}
		

		textFile.close();
		
		FileParser prefixFile = new FileParser(args[1]);
		prefixFile.open();
		
		while ((word = prefixFile.getNextWord()) != null) {
			if (rTree.findPrefix(tree, word,97) == false)
				System.out.println("0");
			
		}
		
		prefixFile.close();
	}
}
