import java.util.*;
import edu.duke.*;
import java.io.File; 

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        String res = "";
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            res = res + message.charAt(i);
        }
        return res;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            String res = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(res);
        }
        
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet<String>();
        for(String word : fr.lines()){
            word = word.toLowerCase();
            set.add(word);
        }
        return set;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        String[] words = message.split("\\W+");
        for (String word : words){
            word = word.toLowerCase();
            if (dictionary.contains(word)){
                count += 1;
            }
        }
        return count;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (String word : dictionary){
            for (char c : word.toCharArray()){
                c = Character.toLowerCase(c);
                if (!map.containsKey(c)){
                    map.put(c, 1);
                }
                else{
                    map.put(c, map.get(c) + 1);
                }
            }
        }
        int maxVal = 0;
        char mostChar = ' ';
        for (char ch : map.keySet()){
            int count = map.get(ch);
            if (count > maxVal){
                maxVal = count;
                mostChar = ch;
            }
        }
        return mostChar;
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxCount = 0;
        String finalLang = "";
        String res = "";
        HashSet<String> finalDic = new HashSet<String>();
        for (String lang : languages.keySet()){
            
            System.out.println("Tring with the language " + lang);
            HashSet<String> dic = languages.get(lang);
            String decrypted = breakForLanguage(encrypted, dic);
            int count = countWords(decrypted, dic);
            if(count > maxCount){
                maxCount = count;
                res = decrypted;
                finalLang = lang;
            }
        }
        System.out.println("The most suitable language is " + finalLang);
        System.out.println(res);
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int maxCount = 0;
        int bestKeyNum = 0;
        String result = "";
        char mostChar = mostCommonCharIn(dictionary);
        for (int key = 1; key <= 100; key++){
            int[] keys = tryKeyLength(encrypted, key, mostChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > maxCount){
                maxCount = count;
                bestKeyNum = key;
                result = decrypted;
            }
            System.out.println("Tried with Key " + key);
        }
        //System.out.println(maxCount);
        //System.out.println(bestKeyNum);
        return result;
    }
    
    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //System.out.println(encrypted);
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        DirectoryResource dr  = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            
            FileResource frLang = new FileResource(f);
            HashSet<String> dictionary = readDictionary(frLang);
            map.put(fileName, dictionary);
            System.out.println("Finished adding " + fileName);
        }
        
        //System.out.println(dictionary);        
        
        //String decrypted = breakForLanguage(encrypted, dictionary);
        //System.out.println(decrypted);
        breakForAllLangs(encrypted, map);
    }
    
    public void testTryKeyLength () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        int[] keys = tryKeyLength(encrypted, 4, 'e');
        for(int i = 0; i < keys.length; i++){
            System.out.println(keys[i]);
        }
    }
    
    public void testReadDictionary () {
        FileResource fr = new FileResource();
        HashSet<String> set = readDictionary(fr);
        System.out.println(set);
    }
    
    public void testCountWords () {
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        FileResource fr1 = new FileResource();
        String message = fr1.asString();
        
        int[] keys = tryKeyLength(message, 38, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
        String decrypted = vc.decrypt(message);
        
        int count = countWords(decrypted, dictionary);
        System.out.println(count);
    }
    
    public void testBreakForLanguage () {
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        FileResource fr1 = new FileResource();
        String message = fr1.asString();
        int count = countWords(message, dictionary);
        System.out.println(count);
    }
    
    public void method(){
        int x = 7;
        int y = x;
        x = 2;
        System.out.println(x + ", " + y);
    }
}
