
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> map;
    public CodonCount(){
        map = new HashMap<String,Integer>(); 
    }
    public void buildCodonMap(int start, String dna){
        map.clear();
        for (int i = start; i < dna.length() - 3; i += 3){
            String codon = dna.substring(i, i + 3);
            codon = codon.toUpperCase();
            if (!map.containsKey(codon)){
                map.put(codon, 1);
            }
            else {
                map.put(codon, map.get(codon) + 1);
            }
        } 
    }
    
    public String getMostCommonCodon(){
        int maxCount = 0;
        String res = "";
        for (String codon : map.keySet()){
            int count = map.get(codon);
            if (count > maxCount){
                maxCount = count;
                res = codon;
            }
        }
        return res;
    }
    
    public void printCodonCounts(int start, int end){
        for (String codon : map.keySet()){
            int count = map.get(codon);
            if (count >= start && count <= end){
                System.out.println(codon + "\t" + count);
            }
        }
    }
    
    public void teser(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap(2, dna);
        printCodonCounts(7,7);
        String maxCodon = getMostCommonCodon();
        int maxCount = map.get(maxCodon);  
        System.out.println("Max Codon is " + maxCodon + " " + maxCount);
        System.out.println("Total unique codon " + map.size());
    }
}
