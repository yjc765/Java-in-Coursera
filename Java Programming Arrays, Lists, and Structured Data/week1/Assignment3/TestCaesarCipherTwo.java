
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    public int[] countLetters(String s){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0; k < s.length(); k++){
            char ch = Character.toLowerCase(s.charAt(k));
            int dex = alpha.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] counts){
        int maxVal = 0;
        int maxInd = 0;
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > maxVal){
                maxInd = i;
                maxVal = counts[i];
            }
        }
        return maxInd;
    }
    
    public int getKey(String input){
        int[] counts = countLetters(input);       
        int maxInd = maxIndex(counts);
        int dkey = maxInd - 4;
        if (maxInd < 4){
            dkey = 26 - (4 - maxInd);
        }
        return dkey;
    }
    
    public String halfOfString(String message, int start){
        StringBuilder split = new StringBuilder();
        for (int i = start; i < message.length(); i = i+2){
            char newChar = message.charAt(i);
            split.append(newChar);
        }              
        return split.toString();
        
        //String answer = "";       
        //for (int k = start; k < message.length() ; k+= 2) {
        //    answer = answer + message.charAt(k);      
        //}     
        //return answer;
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("encrypted msg is: " + encrypted);
        System.out.println("decrypted msg is: " + decrypted);
        String decryptedBreak = breakCaesarCipher(encrypted);
        System.out.println("decrypted msg by breaking the key is: " + decryptedBreak);
    }
    
    public String breakCaesarCipher(String input){
        String split0 = halfOfString(input, 0);
        String split1 = halfOfString(input, 1);
        int key1 = getKey(split0);
        int key2 = getKey(split1);
        System.out.println("key1 is " + key1 + " and key2 is " + key2);
        CaesarCipherTwo cct = new CaesarCipherTwo(key1, key2);
        String decrypted = cct.decrypt(input);
        return decrypted;
    }
}