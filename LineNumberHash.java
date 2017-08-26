/**
 * Utility class that uses a HashMap to keep track of words and the line-numbers
 * at which they occur. Can give back the list of all words, and for each word,
 * a list of line-numbers for that particular word.
 *
 * @author M. Allen
 */
import java.util.ArrayList;
import java.util.HashMap;

public class LineNumberHash
{
    // Maps each word to the list of its line-numbers.
    // Use of hashing makes the map look-ups efficient.
    private HashMap<String, ArrayList<Integer>> lineNumbers = new HashMap<>();
    
    /**
     * Stores a line-number for the given word. If the word is not already in
     * the data-set, then a new list of line numbers is created for that word.
     * If the word is already in the data-set, the line-number is added to an
     * existing list.
     *
     * @param word A word from a file.
     * @param lineNumber A line number (starting at 1) from the file in which
     *            the word occurs.
     */
    public void add( String word, int lineNumber )
    {
        if ( !lineNumbers.containsKey( word ) )
            lineNumbers.put( word, new ArrayList<Integer>() );
        
        lineNumbers.get( word ).add( lineNumber );
    }
    
    /**
     * Returns a list of all words stored; any word that is added using add()
     * will appear in the resulting list.
     *
     * @return An ArrayList containing all words added to the structure.
     */
    public ArrayList<String> getAllWords()
    {
        return new ArrayList<String>( lineNumbers.keySet() );
    }
    
    /**
     * Returns a list containing all line-numbers for a given word.
     *
     * @param word A word from a file.
     * @return An ArrayList containing all the line-numbers at which the word
     *         occurs in the file. (If the word is not included in the list
     *         returned by getAllWords(), the return of this method is null.)
     */
    public ArrayList<Integer> getLineNumbers( String word )
    {
        return lineNumbers.get( word );
    }
}
