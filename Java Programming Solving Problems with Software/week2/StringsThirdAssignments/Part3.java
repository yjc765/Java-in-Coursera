
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Part3 {
    public int findStopCodon(String dnaStr,
                             int startIndex,
                             String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1){
            int diff = currIndex - startIndex;       
            if (diff % 3 == 0){
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }  
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        //if (minIndex == dna.length()){
        //    return "";
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;   
        }
        else{
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            //startIndex = startIndex + currentGene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            //startIndex = startIndex + currentGene.length();
        }
        return geneList;
    } 
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
    
    public void processGenes(StorageResource sr){
        System.out.println("How many genes are in this file？");
        int count_total = 0;
        for (String g: sr.data()){
            count_total = count_total + 1; 
        }
        System.out.println(count_total);
        
        System.out.println("print all the Strings in sr that are longer than 60 characters");
        for (String g: sr.data()){
            if (g.length() > 60){
                System.out.println(g);
            }    
        }
        
        System.out.println("print the number of Strings in sr that are longer than 60 characters");
        int count = 0;
        for (String g: sr.data()){
            if (g.length() > 60){
                count = count + 1;
            }    
        }
        System.out.println(count);
        
        System.out.println("print the Strings in sr whose C-G-ratio is higher than 0.35");
        for (String g: sr.data()){
            if (cgRatio(g) > 0.35){
                System.out.println(g);
            }    
        }
        
        System.out.println("print the number of strings in sr whose C-G-ratio is higher than 0.35");
        int count_cgRatio = 0;
        for (String g: sr.data()){
            if (cgRatio(g) > 0.35){
                count_cgRatio = count_cgRatio + 1;
            }    
        }
        System.out.println(count_cgRatio);
        
        System.out.println("print the length of the longest gene in sr");
        int maxLength = 0;
        for (String g: sr.data()){
            if (g.length() > maxLength){
                maxLength = g.length();
            }    
        }
        System.out.println(maxLength);
    }
    public void testProcessGenes(){
        // FileResource fr = new FileResource("brca1line.fa");
        URLResource file = new  URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        String dna = file.asString();  
        dna = dna.toUpperCase();
        
        System.out.println("How many times does the codon CTG appear in this strand of DNA? " + countCTG(dna));
        
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
    }
}
