
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
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
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("encrypted msg is: " + encrypted);
        System.out.println("decrypted msg is: " + decrypted);
        String decryptedBreak = breakCaesarCipher(encrypted);
        System.out.println("decrypted msg by breaking the key is: " + decryptedBreak);
    }
    
    public String breakCaesarCipher(String input){
        int[] counts = countLetters(input);
        
        int maxInd = maxIndex(counts);
        int dkey = maxInd - 4;
        if (maxInd < 4){
            dkey = 26 - (4 - maxInd);
        }
        System.out.println("The key is " + dkey);
        CaesarCipher cc = new CaesarCipher(dkey);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
}
