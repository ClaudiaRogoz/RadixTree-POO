import java.util.ArrayList;
import java.util.List;

/**
 *Se reprezinta un nod din RadixTree
 * 
 * @author Claudia Rogoz
 *
 */

class RadixNode
{
    String word;
    RadixNode[] kids;
    boolean fullWord;
    List<Integer> list;
    
    RadixNode(String word)
    {
        
        this.word = word.substring(0);
        kids = new RadixNode[27];
        this.fullWord = false;
        list = new ArrayList<Integer>();
    }
    
}