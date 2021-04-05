
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Tester {
    public void testEncrypt(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCipher cc = new CaesarCipher(5);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        CaesarCracker cc = new CaesarCracker('a');
        String decrypted = cc.decrypt(input);
        int dkey = cc.getKey(input);
        System.out.println("The key is " + dkey);
        System.out.println(decrypted);
        
    }
    
    public void testEncryptVC(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        int[] keys = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keys);
        String encrypted = vc.encrypt(input);
        System.out.println(encrypted); 
    }
    
    public void testDecryptVC(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        int[] keys = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(keys);
        String encrypted = vc.encrypt(input);
        System.out.println(encrypted); 
        
        String decrypted = vc.decrypt(encrypted);
        System.out.println(decrypted); 
    }
}
