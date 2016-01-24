package spelling;

import java.util.*;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{

		String lowerCaseWord = word.toLowerCase();
		TrieNode curr = root;
		TrieNode child = curr;
		int i = 0;

		while(i < lowerCaseWord.length()){
			char currentChar = lowerCaseWord.charAt(i);
			Set<Character> validNextCharacters = curr.getValidNextCharacters();
			if(validNextCharacters != null &&
					validNextCharacters.size() > 0 && validNextCharacters.contains(currentChar)){
				child = curr.getChild(currentChar);
				if(child.getText().equals(lowerCaseWord)) {
					if (child.endsWord()) {
						return false;
					} else {
						child.setEndsWord(true);
						size++;
						return true;
					}
				}
			}else{
				child = curr.insert(currentChar);
			}
			curr = child;

			i++;
		}

		curr.setEndsWord(true);
		size++;
		return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		String lowerCaseWord = s.toLowerCase();
		TrieNode curr = root;
		TrieNode child = curr;
		int i = 0;

		while(i < lowerCaseWord.length()){
			char currentChar = lowerCaseWord.charAt(i);
			Set<Character> validNextCharacters = curr.getValidNextCharacters();
			if(validNextCharacters != null &&
					validNextCharacters.size() > 0 && validNextCharacters.contains(currentChar)){
					child = curr.getChild(currentChar);
			}else{
				child = curr.insert(currentChar);
			}
			curr = child;

			i++;
		}

		if(child.getText().equals(lowerCaseWord) && child.endsWord()){
			return true;
		}
		return false;
	}

	/**
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions)
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions

		 List<String> predictions = new ArrayList<>();

		 TrieNode curr = root;
		 int i = 0;
		 while(i < prefix.length()){
			 Set<Character> validNextCharacters = curr.getValidNextCharacters();
			 Character currCharacter = prefix.charAt(i);

			 if(validNextCharacters.contains(currCharacter)){
				 curr = curr.getChild(currCharacter);
			 }else{
				return predictions;
			 }
			 i++;
		 }

		 //we have traversed the whole stem
		 Queue<TrieNode> queue = new LinkedList<>();
		 queue.add(curr);
		 while (!queue.isEmpty()){
			 TrieNode currNode = queue.poll();
			 if(predictions.size() < numCompletions){
				 if(currNode.endsWord()){
					 predictions.add(currNode.getText());
				 }
			 }else{
				 return predictions;
			 }

			 for(Character c: currNode.getValidNextCharacters()){
				 queue.add(currNode.getChild(c));
			 }
		 }
    	 
         return predictions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
}