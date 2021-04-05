
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipherTwo{
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainkey1;
    private int mainkey2;
    
    public CaesarCipherTwo(int key1, int key2){
        mainkey1 = key1;
        mainkey2 = key2;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt (String input){
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if (Character.isUpperCase(currChar)){            
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                    if (i % 2 == 0){
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }//Replace the ith character of encrypted with newChar 
                    else{
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                    
                }
            }
            else if(Character.isLowerCase(currChar)){
                currChar = Character.toUpperCase(currChar);
                int idx = alphabet.indexOf(currChar);
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                    if (i % 2 == 0){
                        char newChar = shiftedAlphabet1.charAt(idx);
                        newChar = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, newChar);
                    }//Replace the ith character of encrypted with newChar 
                    else{
                        char newChar = shiftedAlphabet2.charAt(idx);
                        newChar = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, newChar);
                    }
                    //Replace the ith character of encrypted with newChar    
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String decrypt(String encrypted){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainkey1, 26 - mainkey2);
        return cc.encrypt(encrypted);
    }
}
