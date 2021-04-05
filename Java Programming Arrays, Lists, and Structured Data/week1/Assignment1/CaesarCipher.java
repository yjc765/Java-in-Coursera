
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
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
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar 
                encrypted.setCharAt(i, newChar);
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
                encrypted.setCharAt(i, newChar);
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
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
                        char newChar = shiftedAlphabetKey1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }//Replace the ith character of encrypted with newChar 
                    else{
                        char newChar = shiftedAlphabetKey2.charAt(idx);
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
                        char newChar = shiftedAlphabetKey1.charAt(idx);
                        newChar = Character.toLowerCase(newChar);
                        encrypted.setCharAt(i, newChar);
                    }//Replace the ith character of encrypted with newChar 
                    else{
                        char newChar = shiftedAlphabetKey2.charAt(idx);
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
    
    
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 12;
        int key2 = 2;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(encrypted, 26-key1, 26-key2);
        System.out.println(decrypted);
    }
}
