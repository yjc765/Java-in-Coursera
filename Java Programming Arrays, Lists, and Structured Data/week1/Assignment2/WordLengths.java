
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int maxLength = 0;
        for(String word : resource.words()){
            int wordLength = word.length();
            if (!Character.isLetter(word.charAt(0))){
                wordLength -= 1;
            }
            if (!Character.isLetter(word.charAt(word.length()-1))){
                wordLength -= 1;
            }
            if (wordLength >= counts.length) {                 
                wordLength = counts.length - 1;       
            }   
            if (wordLength > 0 ) { 
                counts[wordLength] += 1;
            }
            if (wordLength > maxLength){
                maxLength = wordLength;
            }
        }
    }
    
    public int indexOfMax(int[] values){
        int maxVal = 0;
        int maxIndex = 0;
        for (int i = 0; i < values.length; i++){
            if (values[i] > maxVal){
                maxIndex = i;
                maxVal = values[i];
            }
        }
        return maxIndex;
    }
    
    public void testCountWordLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for (int k=0; k < 31; k++) {
            System.out.println("The number of length " + k + " is " + counts[k]);
    }  
    int maxIndex = indexOfMax(counts);
    System.out.println("Maximum word length is " + maxIndex);
    }
}