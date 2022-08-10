import java.util.Scanner;
import java.io.File;

public class WordCount {
    
    public static void main(String[] args) throws Exception{
        
        // Scanner object which takes in file
        Scanner inFile = new Scanner(new File("StarTrek.txt"));
        int wordCount = wordCounter(inFile);

        System.out.println("The number of words in file is: " + wordCount);
        
    }

    public static int wordCounter(Scanner inFile){
        
        String word;
        int wordCount = 0;
        int castCount = 0;

        // While file lines have not reached the end
        while(inFile.hasNextLine()){

            // Temporarily storing each line in word
            word = inFile.nextLine();
            // Splitting each line, stored in word, via regex
            String words[] = word.split("\\s+|\\.|\\:|\\,|\\?|\\)|\\]|\\-|\\!");
            String cast[] = word.split("\\:");
            // Counting words in file by getting length of array of stored words
            castCount += cast.length;
            wordCount += words.length;
        }
        System.out.println("Number of times a cast member spoke: " + castCount);
        System.out.println("Number of words minus cast speaker indicator: " + (wordCount - castCount));
        inFile.close();
        return wordCount;
    }
}
