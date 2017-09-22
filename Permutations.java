/**
 * This class describes the permutations object and the methods used on it.
 * It houses the two recursive methods that generate all permutations of a string
 * and all of the words contained in a given dictionary
 * @author Julie Cestaro
 * @version 1 March 2017 
 */

package Project2;

import java.util.*;
import java.lang.OutOfMemoryError;

public class Permutations {
	private String permutation = "";
	
	/**
	 * This is the constructor for the permutations object. It validates that the
	 * string argument only contains letters and will throw an instance of 
	 * IllegalArgumentException if it does not. If it does only contain letters,
	 * the instance variable "permutation" will be initialized to letters.toLowerCase()
	 * @param letters
	 * @throws IllegalArgumentException
	 */
	public Permutations (String letters) throws IllegalArgumentException{
		
 	    if(letters != null && !(letters.equals(""))){
 	    	
 	    	//validate that the string argument only contains letters a-z or A-Z
 	 	    for (int i = 0; i < letters.length(); i++) {
 	 	        if(!Character.isLetter(letters.charAt(i))) {
 	 	        	//throw exception if the string argument does not contain letters
 	 	        	throw new IllegalArgumentException("Error: you entered an invalid character; only letters can be accepted.");
 	 	        }
 	 	    }
 	 	    
 	 	    this.permutation = letters.toLowerCase();
 	    	
 	    }else{
 	    	throw new IllegalArgumentException("Error: no letters provided, cannot compute any words.");
 	    }
	
	}
	
	/**
	 * This is the public wrapper method which pushes to the givePermutations method. It
	 * returns arraylist which contains all permutations of the permutations object
	 * @return permutationsArray
	 * @throws OutOfMemoryError
	 */
	public ArrayList<String> getAllPermutations() throws OutOfMemoryError{
		
		ArrayList<String> permutationsArray = new ArrayList<String>();
		//send to helper method
		givePermutations(this.permutation, "", permutationsArray);
		return permutationsArray;
		
	}
	
	/**
	 * This is the public wrapper method which pushes to the giveWords method. It
	 * returns an alphabetically sorted arraylist which contains all permutations 
	 * of the permutations object that exist in the specified dictionary object
	 * @param dictionary
	 * @return permutationsArray
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary){

		ArrayList<String> wordArray = new ArrayList<String>();
		//send to helper method
		giveWords(this.permutation, "", wordArray, dictionary);
		//sort alphabetically
		Collections.sort(wordArray);
		return wordArray;
		
	}
	
	/**
	 * This is the private method that recursively generates all permutations of the
	 * string entry which are contained in the specified dictionary object and adds
	 * those permutations to the specified arraylist.
	 * @param entry
	 * @param export
	 * @param array
	 * @param dictionary
	 */
	private void giveWords(String entry, String export, ArrayList<String> array, Dictionary dictionary){
		//base case
		if(entry.length() <= 1){
			//validate that export is contained in the dictionary and not already in the arraylist
			if(dictionary.isWord(export + entry) && !(array.contains(export + entry))){
				array.add(export+entry);
			}
		//recursive case
		}else{
			//iterate through each letter in entry
			for (int i = 0; i < entry.length(); i++){
				//remove the letter at i from entry
				String en = entry.substring(0, i) + entry.substring(i+1);
				//add the letter at i to export
				String ex = export + entry.charAt(i);
				//if the current permutation will result in a word, continue generating the permutation
				if(dictionary.isPrefix(ex)){
					giveWords(en, ex, array, dictionary);
				}
			}
		}
	
	}
	
	/**
	 * This is the private method that recursively generates all permutations of the string
	 * entry and adds them to the specified arraylist.
	 * @param entry
	 * @param export
	 * @param array
	 */
	private void givePermutations(String entry, String export, ArrayList<String> array){

		//base case
		if(entry.length() == 0){
			array.add(export);
		//recursive case
		}else{
			//iterate through each letter in entry
			for (int i = 0; i < entry.length(); i++){
				//remove the letter at i from entry
				String en = entry.substring(0, i) + entry.substring(i+1);
				//add the letter at i to export
				String ex = export + entry.charAt(i);
				//add the next letter to the permutation
				givePermutations(en, ex, array);
			}
		}
		
	}
	
	/**
	 * This method returns a string representation of the current permutations object
	 * @return this.permutation, which is a string
	 */
	@Override
	public String toString(){
		return this.permutation;
	}
	
}










