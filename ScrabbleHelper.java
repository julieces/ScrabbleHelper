/**
 * This class contains the main method in which the dictionary and 
 * permutations classes are used.
 * @author Julie Cestaro
 * @version 1 March 2017
 */

package Project2;
import java.util.*;
import java.io.File;


public class ScrabbleHelper {
	
	/**
	 * This main method takes in a file name or absolute path to a CSV file 
	 * and a string of letters as command line arguments and returns all of the
	 * words that can be made with those letters that are also contained in the 
	 * dictionary file
	 * @param args
	 */
	public static void main(String[] args){
		
		//validate that there are two command line arguments and throw exeptions if otherwise
		if (args.length == 0){
			System.err.println("Error: Two arguments expected");
			System.exit(1);
		}else if (args.length == 1){
			System.err.println("Error: A string of letters must be entered in addition to the file name");
			System.exit(1);
		}
		
		//create file object with the specified path or file name
		File file = new File(args[0]);
		
		//declare dictionary and permutations objects
		Dictionary dictionaryFile;
		Permutations letters;
		
		try{
			
			//create dictionary object
			dictionaryFile = new Dictionary(file);

			//create permutation object
			letters = new Permutations(args[1]);
			
			//generate all words from the permutations of the letters in the permutations object
			ArrayList<String> allWords = letters.getAllWords(dictionaryFile);
			
			//determine proper output to the user
			if (allWords.size() == 0){
				System.out.println("No words found");
			}else{
				System.out.printf("Found %d Words:", allWords.size());
				for (int i = 0; i < allWords.size(); i++){
					System.out.println("\n\t" + allWords.get(i));
				}
			}
			
		}catch(IllegalArgumentException e){
			System.err.println(e);
		
		}
		
	}
}
