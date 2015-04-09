
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementare RadixTree
 * 
 * @author Claudia Rogoz
 * 
 */
public class RadixTree
{
	static boolean rez;
    
	/**
	 * Se creeaza un RadixTree doar cu nodul root
	 */
	RadixTree(){
		new RadixNode("\0");
	}
	/**
	 * Se insereaza recursiv cuvantul in RadixTree
	 * 
	 * @param root Nodul curent
	 * @param word Cheia(Cuvantul) de inserat
	 * @param index Lista care contine indicii la care se afla cuvantul curent
	 * @param offset Variabila asociata cheii (in ASCII)
	 */
    void insertWord(RadixNode root, String word, List<Integer> index, int offset)
    {
    	
    	RadixNode rNode = root;
        String prefix = null;
        String oldstr = null;
        String newstr = null;
       
        //adauga nodul corespunzator cheii(word) daca nu a fost creat deja
        if (rNode.kids[word.charAt(0) - offset ] == null)
        	{
        	
        	rNode.kids[word.charAt(0) - offset] = new RadixNode(word);
        	rNode.kids[word.charAt(0) - offset].fullWord = true;
        	rNode.kids[word.charAt(0) - offset].list.addAll(index);
        	
        	}
        
        else 
        	{        	
        	
        	boolean ok = false;
        
        	//se gaseste prefixul comun nodului curent, respectiv cheii (word)
        	int minLength = Math.min(rNode.kids[word.charAt(0) - offset].word.length(), word.length());
    	   
        	for (int j = 0; j < minLength; j++) {
        		//daca s-a gasit vreo diferenta intre cele 2 cuvinte
        		//nodul curent se imparte in doua noduri, urmand ca apoi sa se adauge recursiv 
        		// si noul cuvant de introdus
    	        if (rNode.kids[word.charAt(0) - offset].word.charAt(j) != word.charAt(j)) {
    	           prefix = rNode.kids[word.charAt(0) - offset].word.substring(0, j);
    	           oldstr = rNode.kids[word.charAt(0) - offset].word.substring(j);
    	           newstr = word.substring(j); 
    	           ok = true;
    	           
    	           //se construieste un nod intermediar
    	           RadixNode r1 = new RadixNode(oldstr);
    	           r1.fullWord = rNode.kids[word.charAt(0) - offset].fullWord;
    	           r1.list.addAll(rNode.kids[word.charAt(0) - offset].list);
    	        		
    	        
    	           for(int k = 0; k < 27; k++){
    	        	   if (rNode.kids[word.charAt(0) - offset].kids[k] != null)
    	        	   	{
    	        		   r1.kids[k] = rNode.kids[word.charAt(0) - offset].kids[k];
    	        	   	}	
    	           }
    	            
    	           //se updateaza nodul curent cu noile date
    	           rNode.kids[word.charAt(0) - offset]. word = prefix;
    	           rNode.kids[word.charAt(0) - offset].fullWord = false;
    	           rNode.kids[word.charAt(0) - offset].list.removeAll(rNode.kids[word.charAt(0) - offset].list);
    	          
    	           
    	           for (int i = 0; i < 27; i++)
    	        	   rNode.kids[word.charAt(0) - offset].kids[i] = null;
    	           if (oldstr.charAt(0) == 39)
    	        	   rNode.kids[word.charAt(0) - offset].kids[26] = r1;
    	           else rNode.kids[word.charAt(0) - offset].kids[oldstr.charAt(0) - offset] = r1;
    	           
    	           //recursiv se adauga noul nod in functie de offset  
    	           if (newstr.charAt(0) == 39)
    	        	   insertWord(rNode.kids[word.charAt(0) - offset],newstr,index,13);
    	           else insertWord(rNode.kids[word.charAt(0) - offset],newstr,index,97);
    	           
    	           for (int i = 0; i< 27 ; i++){
    	        	   RadixNode pNode = new RadixNode(rNode.kids[word.charAt(0) - offset].word);
    	        	   pNode.fullWord = rNode.kids[word.charAt(0) - offset].fullWord;
    	        	   for (int k = 0; k< 27; k++)
    	        		   pNode.kids[k] = rNode.kids[word.charAt(0) - offset];
    	           }
    	           return;
    	        }
    	    }
        	
        	//daca prefxul comun celor doua cuvinte este format din primele minLKength caractere
    	    if (!ok){
    	
    	    	prefix = rNode.kids[word.charAt(0) - offset].word.substring(0, minLength);
    	    	
    	    	//daca lungimea cuvantului de introdus este mai mare decat lungimea prefixului gasit
    	    	//atunci se insereaza recursiv cuvantul de la pozitia minLength in RadixTree 
    	    	if (minLength < word.length()){
    	    		newstr = word.substring(minLength);
    	    		
    	    		if (newstr.charAt(0) == 39)
    	    		   insertWord(rNode.kids[word.charAt(0) - offset],newstr,index,13);
    	    		else insertWord(rNode.kids[word.charAt(0) - offset],newstr,index,97);
    	    	}
    	    	else {
    	    		
    	    		//in cazul in care cuvantul este egal cu prefixul gasit
    	    		//se updateaza lista indecsilor
    	    		
    	    		if (rNode.kids[word.charAt(0) - offset].word.length() == word.length()){
    	    			
    	    			rNode.kids[word.charAt(0) - offset].fullWord = true;
    	    			rNode.kids[word.charAt(0) - offset].list.addAll(index);
    	    		}
    	    		
    	    		else{
    	    		//daca lungimea cuvantului vechiului nod este mai mare decat lungiema prefixul gasit
    	    		// se insereaza recursiv cuvantul de la pozitia minLength in RadixTree
    	    		//inserarea se face in functie de offset-ul corespunzator
    	    		oldstr = rNode.kids[word.charAt(0) - offset].word.substring(minLength);
    	    		
    	    		
    	    		if (oldstr.charAt(0) == 39)
    	    		   insertWord(rNode.kids[word.charAt(0) - offset],oldstr,index,13);
    	    		else insertWord(rNode.kids[word.charAt(0) - offset],oldstr,index,97);}
    	    	}
    	    }
    	   }

    }
    
    /**
     * Metoda recursiva de cautare a unui prefix dat
     * 
     * In cazul in care se gaseste prefixul cautat, se apeleaza 
     * functia getNodes pentru a afisa indecsii corespunzatori
     * cuvintelor cu prefixul dat 
     * 
     * @param root Nodul curent
     * @param word Prefixul de cautat
     * @param offset Variabila asociata cheii
     * @return True daca s-a gasit prefixul, fals altfel
     */
    boolean findPrefix(RadixNode root, String word,int offset)
    {
    	
       RadixNode rNode = root;
      
       
       if (rNode.kids[word.charAt(0) - offset] == null){
    	  	   rez = false;
       }
       else {
    	String prefix = null;
    	String newstr = null;
    	
    	boolean ok = false;
       	int j;
       	
       	//se gaseste prefixul comun celor 2 cuvinte
    	int minLength = Math.min(rNode.kids[word.charAt(0) - offset].word.length(), word.length());
    	
    	for ( j = 0; j < minLength && ok == false; j++) {
   	        if (rNode.kids[word.charAt(0) - offset].word.charAt(j) != word.charAt(j)) {
   	          
   	           prefix = rNode.kids[word.charAt(0) - offset].word.substring(0, j);
   	           if (minLength > j){
   	        	   rez = false;
   	        	   ok = true;
   	           }
   	        }   
    	}
    	
    	
    	//daca prefxul comun celor doua cuvinte este format din primele minLKength caractere
    	if (!ok){
    		prefix = rNode.kids[word.charAt(0) - offset].word.substring(0, minLength);
	    	}
    
    	//daca s-a gasit prefixull in RadixTree se apeleaza functia "getNodes" pentru
    	//listarea indecsilor

    	if (prefix.equals(word)){
    		rez = true;
    		getNodes(rNode.kids[word.charAt(0) - offset], new MyArrayList());
    		return rez;
    		}
    	else{
    		//daca nu s-a gasit inca prefixul atunci se continua cautarea recursiva
    		if (!ok && word.length() > minLength){
    			newstr = word.substring(minLength);
    			findPrefix(rNode.kids[word.charAt(0) - offset],newstr,offset);
    		}
    		else {
    			rez=false;
    		}}
    	}
      
      return rez;
       }

    
    /**
     * Se afiseaza o lista de indecsi corespunzatori parcurgerii 
     * RadixTree-ului de la nodul root in jos
     * 
     * @param root Nodul curent
     * @param list Lista de indecsi
     * 
     */
    void getNodes(RadixNode root, MyArrayList list) {
    	//se foloseste o coada pentru retinerea tuturor nodurilor 
    	//de la nivelul nodului curent(root) in jos
    	Queue<RadixNode> queue = new LinkedList<RadixNode>();
    
    	//daca in nodul curent exista litere care termina cuvantul
    	if (root.fullWord == true)
    		list.addAll(root.list);
    	
    	
    	for (int i = 0; i< 27; i++)
    		queue.add(root.kids[i]);

    	//atat timp cat nu mai este niciun nod de parcurs
    	//se adauga in lista "list" lista de indecsi corespunzatori nodului
    	while (!queue.isEmpty()) {
    		RadixNode node = queue.remove();
    		if (node != null){
        		if (node.fullWord == true) {
        			list.addAll(node.list);
        	}
        
        	for (int i = 0; i< 27 ; i++)
        		queue.add(node.kids[i]);
        	}
    	}
   
    System.out.println(list.size()+ list.toString());
    
    }
    


}
