
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
    public boolean isVowel(char ch){
        char lch = Character.toLowerCase(ch);
        if(lch == 'a' || lch == 'e' || lch == 'i' || lch == 'o' || lch == 'u'){
            return true;
        }
        return false;
    }
    
    public void testIsVowel(){
        char ch = 'a';
        boolean res = isVowel(ch);
        System.out.println(res);
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder returnString = new StringBuilder(phrase);
        for(int k=0; k < phrase.length(); k++){
            char character = phrase.charAt(k);
            if (isVowel(character)){
               returnString.setCharAt(k, ch); 
            }
            else{
                returnString.setCharAt(k, character); 
            }
        }
        return returnString.toString();
    }
    
    public void testReplaceVowels(){
        String phrase =  "Hello World";
        char ch = '*';
        String returnString = replaceVowels(phrase, ch);
        System.out.println(returnString);
    }
    
    public String emphasize(String phrase, char ch){
        String lowerPhrase = phrase.toLowerCase();
        char lch = Character.toLowerCase(ch);
        StringBuilder returnString = new StringBuilder(phrase);
        for(int k = 0; k < phrase.length(); k++){
            char character = lowerPhrase.charAt(k);
            char characterInput = phrase.charAt(k);
            if (character == lch && k % 2 == 0){
               returnString.setCharAt(k, '*'); 
            }
            else if (character == lch && k % 2 == 1){
                returnString.setCharAt(k, '+'); 
            }
            else{
                returnString.setCharAt(k, characterInput); 
            }
        }
        return returnString.toString();
    }
    
    public void testEmphasize(){
        String phrase =  "dna ctgaaactga";
        char ch = 'A';
        String returnString = emphasize(phrase, ch);
        System.out.println(returnString);
    }
}


