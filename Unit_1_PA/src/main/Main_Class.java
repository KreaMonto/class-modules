package main;

import java.util.*;

public class Main_Class {
	
	public static void main(String args[]) {
		
		
		try (
				// this is to be able to use the scanner
				Scanner scan = new Scanner(System.in)
		) {
			// first task - to be able to capture the user input... in this case by console...
			System.out.println("Enter your text:");
			String inputText = scan.nextLine();
			
			// second task - to be able to count the length of the text in chars
			int charCount = inputText.length();
			System.out.println("Character count: " + charCount);
			
			// third task - to be able to count the words
			int wordCount = inputText.split("\\s+").length;
			System.out.println("Word Count: " + wordCount);
			
			// the most common char
			char mostCommonChar = findMonstCommonChar(inputText);
			System.out.println("Most Common Character: ->" + mostCommonChar + "<-");
			
			// choosing a char to validate the frequency
			System.out.println("Enter a character to check its frequency:");
			char inputChar = scan.next().charAt(0);
			int charFrequency = countCharFreq(inputText, inputChar);
			System.out.println("Frequency of '" + inputChar + "': " + charFrequency);
			
			// words frequency
			System.out.println("Enter a word to check its frequency:");
			String inputWord = scan.next().toLowerCase();
			int wordFrequency = countWordFreq(inputText.toLowerCase(), inputWord);
			System.out.println("Frequency of '" + inputWord + "': " + wordFrequency);
			
			// unique words
			int uniqueWordsCount = countUniqueWords(inputText.toLowerCase());
			System.out.println("Unique Words Count: " + uniqueWordsCount);
			
			
		} catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); 
        }
        
	}
	
	
	
	
	// finding the most common char ------------------------------------------------------
	private static char findMonstCommonChar(String text) {
		
		Map<Character, Integer> charFreqMap = new HashMap<>();
		
		
		for (char c : text.toCharArray()) {
			charFreqMap.put(c, charFreqMap.getOrDefault(c, 0) + 1);
		}
		
		char mostCommonChar = ' ';
        int maxFrequency = 0;
        
        for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostCommonChar = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }
		
		return mostCommonChar;
	}
	
	
	
	
	// counting frequency of a char ------------------------------------------------------
    private static int countCharFreq(String text, char character) {
    	int frequency = 0;
    	
    	for (char c : text.toCharArray()) {
    		if (Character.toLowerCase(c) == Character.toLowerCase(character)) {
    			frequency++;
    		}
    	}
        return frequency;  
    }
    
    
    
    
    // counting frequency of a word -------------------------------------------------------
    private static int countWordFreq(String text, String word) {
    	int frequency = 0;
    	String[] words = text.split("\\s+");
    	
    	
    	for (String w : words) {
    		if (w.toLowerCase().equals(word.toLowerCase())) {
    			frequency++;
    		}
    	}
    	return frequency;  
    }
    
    
    
    // unique words
    private static int countUniqueWords(String text) {
		
    	Set<String> uniqueWordSet = new HashSet<>();
    	
    	String[] words = text.split("\\s+");
    	
    	for (String word : words) {
    		uniqueWordSet.add(word);
    	}
    	
    	
    	return uniqueWordSet.size();
    }
}
