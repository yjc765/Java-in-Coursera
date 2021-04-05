
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainkey;
    
    public CaesarCipher(int key){
        mainkey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(mainkey) + alphabet.substring(0, mainkey);
    }
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < sb.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = sb.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if (Character.isUpperCase(currChar)){            
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar 
                sb.setCharAt(i, newChar);
                }
            }
            else if(Character.isLowerCase(currChar)){
                currChar = Character.toUpperCase(currChar);
                int idx = alphabet.indexOf(currChar);
                if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                newChar = Character.toLowerCase(newChar);
                sb.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted;
        return sb.toString();
    }
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher(26 - mainkey);
        return cc.encrypt(encrypted);
    }
}
