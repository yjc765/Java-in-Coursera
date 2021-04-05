/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
            System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }
    
    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource("yob" + Integer.toString(year) + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                rank += 1;
                if (rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testGetRank () {
        //FileResource fr = new FileResource();
        int rank = getRank(1971, "Frank", "M");
        System.out.println(rank);
    }
    
    public String getName(int year, int rank, String gender){
        String name = "NO NAME";
        int count = 0;
        FileResource fr = new FileResource("yob" + Integer.toString(year) + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                count += 1;
                if (count == rank) {
                    name = rec.get(0);
                    return name;
                }
            }
        }
        return name;
    }
    
    public void testGetName() {
        //FileResource fr = new FileResource();
        String name = getName(1982, 450, "M");
        System.out.println(name);
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        String newName = "NO NAME";
        int rank = getRank(year, name, gender);
        if(rank != -1){
            newName = getName(newYear, rank, gender);
        }
        return newName;
    }
    
    public void testwhatIsNameInYear() {
        //FileResource fr = new FileResource();
        String oldName = "Owen";
        int year = 1974;
        int newYear = 2014;
        String newName = whatIsNameInYear(oldName, year, newYear, "M");
        System.out.println(oldName + " born in " + Integer.toString(year) + " would be " + 
                           newName + " if she(he) was born in " + Integer.toString(newYear));
    }
    
    public int yearOfHighestRank(String name, String gender){
        int highestYear = -1;
        int rank = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rankOfThisYear = getRank(year, name, gender);
            if (rank == -1 && rankOfThisYear != -1) {
                rank = rankOfThisYear;
                highestYear = year;
            }
            else if (rank != -1 && rankOfThisYear != -1){
                if(rankOfThisYear < rank){
                    rank = rankOfThisYear;
                    highestYear = year;
                }
            }
            
        }
        return highestYear;
    }
    
    public void testYearOfHighestRank() {
        //FileResource fr = new FileResource();
        String name = "Mich";
        int highestYear = yearOfHighestRank(name, "M");
        System.out.println(highestYear);
    }
    
    public double getAverageRank(String name, String gender){
        double averageRank = -1.0;
        int numFile = 0;
        double totalRank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rankOfThisYear = getRank(year, name, gender);
            if (rankOfThisYear != -1) {                
                totalRank += rankOfThisYear;
            }
            numFile += 1;
        }
        
        if(totalRank != 0){
            averageRank = totalRank / numFile;
        }
        
        return averageRank;
    }
    
    public void testGetAverageRank(){
        String name = "Robert";
        String gender = "M";
        double averageRank = getAverageRank(name, gender);
        System.out.println(averageRank);
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalHigherBirth = 0;
        FileResource fr = new FileResource("yob" + Integer.toString(year) + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender) && !rec.get(0).equals(name)){
                totalHigherBirth += Integer.parseInt(rec.get(2));
            }
            else if(rec.get(1).equals(gender) && rec.get(0).equals(name)){
                break;
            }
        }
        return totalHigherBirth;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int total = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println(total);
        
    }
}