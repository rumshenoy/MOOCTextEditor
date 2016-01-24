package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;

    public DictionaryBST() {
        this.dict = new TreeSet<>();
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
        String lowerCaseWord = word.toLowerCase();
        if(dict.contains(lowerCaseWord)){
            return false;
        }else{
            dict.add(lowerCaseWord);
        }
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        String lowerCase = s.toLowerCase();
        if(dict.contains(lowerCase)){
            return true;
        }
        return false;
    }

}
