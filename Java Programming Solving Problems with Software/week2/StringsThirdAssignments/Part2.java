
/**
 * Write a description of part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part2 {
    public double cgRatio(String dna){
        double ratio = 0;
        
        int currC = dna.indexOf("C");
        int countC = 0;
        while (currC != -1){
            countC += 1;
            currC = dna.indexOf("C", currC + 1);
        }
        int currG = dna.indexOf("G");
        int countG = 0;
        while (currG != -1){
            countG += 1;
            currG = dna.indexOf("G", currG + 1);
        }
        ratio = (float)(countC + countG) / dna.length();
        return ratio;
    }
    
    public int countCTG(String dna){
        int currCTG = dna.indexOf("CTG");
        int count = 0;
        while (currCTG != -1){
            count += 1;
            currCTG = dna.indexOf("CTG", currCTG + 3);
        }
        return count;
    }
    
    public void testCgRatio(){
        String dna = "ATGCCATAG";
        System.out.println("Testing cgRatio on " + dna);
        double ratio = cgRatio(dna);        
        System.out.println(ratio);       
    }
    
    public void testCountCTG(){
        String dna = "CTGABTCTGCATGGCTGCC";
        System.out.println("Testing countCTG on " + dna);
        int count = countCTG(dna);        
        System.out.println(count);       
    }
}
