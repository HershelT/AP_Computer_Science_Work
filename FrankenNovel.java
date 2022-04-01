import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.*;
/**
 * Module 9 Project: The Gutenberg FrankenNovel
 *
 * Name: Hershel Thomas
 * 
 * AP Computer Science, Virtual Virginia
 */

public class FrankenNovel
{
    //////// MAIN ////////
    
    //For FILENAME, TITLE, and AUTHOR below, include appropriate text.
    
    public static void main(String[] args)
    {
        //Book 1
        File bookFile1 = new File("GreatGatsby.txt");Book book1 = new Book(bookFile1);
        System.out.println("Book 1: The Great Gatsby by F. Scott Fitzgerald");
        System.out.println("Word count: " + book1.getWordCount());
        System.out.println("Longest word: " + book1.getLongestWord() + "\n");
        
        //Book 2
        File bookFile2 = new File("ScarletLetter.txt");
        Book book2 = new Book(bookFile2);
        System.out.println("Book 2: The Scarlet Letter by Nathaniel Hawthorne");
        System.out.println("Word count: " + book2.getWordCount());
        System.out.println("Longest word: " + book2.getLongestWord() + "\n");
        //FrankenNovel
        writeFrankenNovel(book1, book2);        
        System.out.println("My FrankenNovel, The Crimson Past, has been created in frankenOutput.txt.");
    }
    
    //////// METHODS ////////
    
    /* Writes to an output file shorterBook's even-indexed words and longerBook's odd-indexed words.
     * After end of shorterBook's list, all remaining words from longerBook's list will print.
     * Uses a try-catch block.   
       @param b1 -- the first Book object
              b2 -- the second Book object
    */
    public static void writeFrankenNovel(Book b1, Book b2)
    {
        Book shorterBook;
        Book longerBook;
        ArrayList<String> sL;  
        ArrayList<String> lL;
        PrintWriter outputFile;
        /* Determine shorterBook and longerBook. */
        
        try
        {
            outputFile = new PrintWriter("frankenOutput.txt");
            /* Print appropriate words to the output file. */
            shorterBook = b1;
            longerBook = b2;
            if (b1.getWordCount() > b2.getWordCount()) {
                longerBook = b1;
                shorterBook = b2;
            }
            sL = shorterBook.getWordList();
            lL = longerBook.getWordList();
            for (int i = 0; i < lL.size() ; i++) {
                if (i % 2 == 0 && i < sL.size()) {
                    outputFile.print(sL.get(i) + " ");
                }
                else {
                    outputFile.print(lL.get(i) + " ");
                }
                if (i % 9 == 0 && i != 0) {
                    outputFile.println(" ");
                }
            }
            outputFile.close();
        }
        catch(Exception e)
        {
            System.out.println("Check output filename.");
        }
    }
}

class Book
{
    //// FIELDS ////
    public ArrayList<String> wordList;
    public int wordCount;
    public String longestWord;
    public Scanner scanB;
    public ArrayList<String> words;
    /* Declare fields for the word list, the word count, and the longest word.*/
    
    ////// CONSTRUCTOR //////
    //@param f -- the file object
    public Book(File f)
    {
        words = readBook(f);
        wordCount = countWords(words);
        longestWord = findLongestWord(words);
    }
    
    //////// METHODS ////////

    /* Reads in every word from an input file to an ArrayList.
     * Punctuation may be included.
     * Uses a try–catch block.
     * Returns an ArrayList filled with "words."
     * @param f -- the input file object
     */
    public ArrayList<String> readBook(File f)
    {
        try {
            scanB = new Scanner(f);
            wordList =  new ArrayList<String>();
            while (scanB.hasNext()) {
                String scanWord = scanB.next();
                wordList.add(scanWord);
            }
        }catch (Exception e) {
            System.out.println("Wrong File");
        }
        return wordList;
    }
    
    /* Counts the number of words in a list.
     * Returns number of words.
     * @param b -- A Book object's word list
     */
    public int countWords(ArrayList<String> b)
    {
        wordCount = 0;
        for (int i = 0; i < b.size(); i++) {
            wordCount++;
        }
        return wordCount;
    }
    
    /* Finds the longest word in a list.
     * In the case of a tie, return only the first word of longest length.
     * Returns the longest word.
     * @param b -- A Book object's word list
     */
    public String findLongestWord(ArrayList<String> b)
    {
        longestWord = b.get(0);
        for (int i = 1; i < b.size(); i++) {
            
            if (b.get(i).length() > longestWord.length()) {
                longestWord = b.get(i);
            }
        }
        return longestWord;
    }
    
    ////////// ACCESSOR METHODS //////////
    public int getWordCount() {
        return wordCount;
    }
    public String getLongestWord() {
        return longestWord;
    }
    public ArrayList<String> getWordList() {
        return wordList;
    }
    /* Write 3 accessor methods for the fields of the Book class. */
    
}