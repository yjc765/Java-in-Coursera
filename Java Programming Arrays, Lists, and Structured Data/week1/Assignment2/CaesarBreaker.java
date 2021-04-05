
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarBreaker {
    public String decrypt(String encrypted, int key){
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
    }
    
    public void testDecrypt(){
    int key = 15;
    String encrypted = "Pi cddc qt xc iwt rdcutgtcrt gddb lxiw ndjg wpi dc udg p hjgegxht epgin. NTAA ADJS!";
    String decrypted = decrypt(encrypted, key);
    System.out.println(decrypted);
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
    
    public void testHalfOfString(){
        String message = "Qbkm Zgis";
        String split0 = halfOfString(message, 0);
        System.out.println(split0);
        String split1 = halfOfString(message, 1);
        System.out.println(split1);
    }
    
    public int getKey(String s){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k = 0; k < s.length(); k++){
            char ch = Character.toLowerCase(s.charAt(k));
            int dex = alpha.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        ///
        //for(int k = 0; k < counts.length; k++){
        //    System.out.println("k: " + k + " freq: " + counts[k]);
        //}
        ///
        int maxVal = 0;
        int maxIndex = 0;
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > maxVal){
                maxIndex = i;
                maxVal = counts[i];
            }
        }
        System.out.println("Final maxIndex: " + maxIndex);
        int dkey = maxIndex - 4;
        if (maxIndex < 4){
            dkey = 26 - (4 - maxIndex);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        String split0 = halfOfString(encrypted, 0);
        System.out.println(split0);
        String split1 = halfOfString(encrypted, 1);
        System.out.println(split1);
        int key1 = getKey(split0);
        int key2 = getKey(split1);
        System.out.println("key1 is " + key1 + " and key2 is " + key2);
        CaesarCipher cc = new CaesarCipher();
        String decrypted = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        return decrypted;
    }
    
    public void testDecryptTwoKeys() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);

    }
}
