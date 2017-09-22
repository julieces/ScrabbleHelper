/**
 * This class describes the dictionary object and the methods used on it.
 * It contains an arraylist of the words in the dictionary file as well as two
 * methods which determine if a given string is a word in the dictionary or if 
 * a given string is a prefix of a word in the dictionary
 * @author Julie Cestaro
 * @version 1 March 2017 
 */

package Project2;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Dictionary {
	private ArrayList<String> dictionary;
	
	/**
	 * This is the constructor for the dictionary object. It validates the existence
	 * of the file used to create the dictionary object and creates an arraylist containing
	 * all of the words in the dictionary file.
	 * @param f
	 * @throws IllegalArgumentException
	 */
	public Dictionary (File f) throws IllegalArgumentException{
		
		//validate that the file exists and is readable
		if (!f.exists()){
			throw new IllegalArgumentException("Error: File " + f.getName() + " does not exist.");
		}else if (!f.canRead()){
			throw new IllegalArgumentException("Error: File " + f.getName() + " is not readable.");
		}
		
		//create a new scanner to read the file
		Scanner input = null;
		//be sure that the file exists
		try{
			input = new Scanner(f);
		}catch (FileNotFoundException e){
			System.err.println("File not found exception thrown for file " + f.getName());
		}
		
		//create an empty array to contain the dictionary
		dictionary = new ArrayList<String>();
		
		//add each word to the dictionary arraylist
		while (input.hasNextLine()){
			dictionary.add(input.nextLine());
		}
		
		input.close();
	}

	/**
	 * This is a public wrapper method which takes in a string argument 
	 * that gets pushed into the wordSearch method which uses a binary search 
	 * to determine if the string argument is contained in the dictionary object
	 * @param str
	 * @return this.wordSearch(0, this.dictionary.size() - 1, str)
	 */
	public boolean isWord(String str){
		//send to helper method
		return this.wordSearch(0, this.dictionary.size() - 1, str);
		
	}
	
	/**
	 * This is the private binary search method which determines if the string argument
	 * from the isWord method is contained in the dictionary object. The string argument
	 * from the isWord method is "key" here.
	 * @param begin
	 * @param end
	 * @param key
	 * @return true if key is contained in the dictionary, false if it isn't
	 */
	private boolean wordSearch(int begin, int end, String key){
		//formula for finding midpoint
		int mid = (begin + end) / 2;
		
		//base cases
		if (begin >= end){
			//case where one of the endpoints is the key
			if (key.compareTo(this.dictionary.get(begin)) == 0){
				return true;
			}else{
				return false;
			}
		//case where the midpoint is the key
		}else if (key.compareTo(this.dictionary.get(mid)) == 0){
			return true;
		}
		
		//recursive cases
		//case where key is smaller than midpoint
		else if (key.compareTo(this.dictionary.get(mid)) < 0){
			return wordSearch(begin, mid-1, key);
		
		//case wehere key is larger than the midpoint
		}else if (key.compareTo(this.dictionary.get(mid)) > 0){
			return wordSearch(mid+1, end, key);
		}
		
		
		else{
			return false;
		}
	}
	
	/**
	 * This is a public wrapper method which takes in a string argument 
	 * that gets pushed into the prefixSearch method which uses a binary search 
	 * to determine if the string argument is contained in the dictionary object
	 * @param str
	 * @return this.prefixSearch(0, this.dictionary.size() - 1, str)
	 */
	public boolean isPrefix(String str){
		//send to helper method
		return this.prefixSearch(0, this.dictionary.size() - 1, str);
	}
	
	/**
	 * This is the private binary search method which determines if the string argument
	 * from the isPrefix method is contained in the dictionary object. The string argument
	 * from the isPrefix method is "key" here.
	 * @param begin
	 * @param end
	 * @param key
	 * @return true if key is a prefix, false if it isn't
	 */
	private boolean prefixSearch(int begin, int end, String key){
		//formula for finding midpoint
		int mid = (begin + end) / 2;

		//base cases
		if (begin >= end){
			//case where one of the endpoints is the key
			if (key.length() <= this.dictionary.get(begin).length() && key.compareTo(this.dictionary.get(begin).substring(0, key.length())) == 0){
				return true;
			}else{
				return false;
			}
		}else if (key.length() <= this.dictionary.get(mid).length() && key.compareTo(this.dictionary.get(mid).substring(0, key.length())) == 0){
			return true;
		}
		
		//recursive cases
		//case where key is smaller than midpoint
		else if (key.length() <= this.dictionary.get(mid).length() && key.compareTo(this.dictionary.get(mid).substring(0, key.length())) < 0){
			return prefixSearch(begin, mid-1, key);
		//case wehere key is larger than the midpoint
		}else if (key.length() <= this.dictionary.get(mid).length() && key.compareTo(this.dictionary.get(mid).substring(0, key.length())) > 0){
			return prefixSearch(mid+1, end, key);
		}
		
		
		else{
			return false;
		}
	}
	
	/**
	 * This is a method which returns a string representation of the dictionary object
	 * @return str
	 */
	@Override
	public String toString(){
		String str = "[";
		for (int i = 0; i < this.dictionary.size(); i++){
			str += this.dictionary.get(i);
			str += ", ";
		}
		str += "]";
		return str;
	}
}
