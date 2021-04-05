
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or av date)
 */
import edu.duke.*;
import java.util.*;
import java.io.File;
public class GladLib {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> seen;
    private ArrayList<String> seenLabel;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLib(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        seen = new ArrayList<String>();
        seenLabel = new ArrayList<String>();
    }
    
    public GladLib(String source){        
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        
        String[] labels = {"country", "noun", "animal", "adjective", "name", 
                           "color", "timeframe", "verb", "fruit"};
        for (String s : labels){
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            //System.out.println(list);
            myMap.put(s, list);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!seenLabel.contains(label)){
            seenLabel.add(label);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50) + 5;           
        }
        
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (seen.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
        seen.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int countTotal = 0;
        for(String label : myMap.keySet()){
            ArrayList<String> list = myMap.get(label);
            for (String word : list){
                countTotal += 1;
            }
        }
        return countTotal;
    }
    
    public int totalWordsConsidered(){
        int countConsidered = 0;
        for(String label : myMap.keySet()){
            if (seenLabel.contains(label)){
                ArrayList<String> list = myMap.get(label);
                for (String word : list){
                    countConsidered += 1;
                }
            }
        }
        return countConsidered;
    }
    
    public void makeStory(){
        seen.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate.txt");
        printOut(story, 60);
        System.out.println("\n");
        int count = 0;
        for (String s: seen){
            System.out.println(s);
            count++;
        }
        System.out.println(count);
        
        int countTotal = totalWordsInMap();
        System.out.println(countTotal);
        
        int countConsidered = totalWordsConsidered();
        System.out.println(countConsidered);
    }
}
