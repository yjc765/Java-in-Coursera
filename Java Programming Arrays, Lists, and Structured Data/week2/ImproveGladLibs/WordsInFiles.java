
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;
public class WordsInFiles {
    private HashMap<String, ArrayList<String>> map; 
    public WordsInFiles(){
        map = new HashMap<String, ArrayList<String>>();
    }
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fName = f.getName();
        for (String word : fr.words()){
            if (!map.containsKey(word)){
                ArrayList<String> wordList = new ArrayList<String>();
                wordList.add(fName);
                map.put(word, wordList);
            }
            else{
                ArrayList<String> wordList = map.get(word);
                if (wordList.indexOf(fName) == -1){
                    wordList.add(fName);
                    map.put(word, wordList);
                }

            }
        } 
    }
    
    public void buildWordFileMap(){
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maxNum = 0;
        for (String word : map.keySet()){
            ArrayList<String> values = map.get(word);
            if (values.size() > maxNum){
                maxNum = values.size();
            }
        }
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String word : map.keySet()){
            if (map.get(word).size() == number){
                words.add(word);
            }
        }
        return words;
    }
    
    public void printFiles(String word){
        System.out.println(word + " appears in: ");
        for (String wordInMap : map.keySet()){
            if (wordInMap.equals(word)){
                for (String fileName : map.get(word)){
                    System.out.println(fileName);
                }
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxNum = maxNumber();
        System.out.println("Max number is " + maxNum);
        System.out.println(map.size() + " in total");
        //for (String word : map.keySet()){
        //    System.out.println(word + " appears in " + map.get(word));
        //}
        ArrayList<String> words = wordsInNumFiles(4);
        System.out.println(words.size());
        //System.out.println("These words appear in 7 files: " + words);
        printFiles("tree");
    }
}


