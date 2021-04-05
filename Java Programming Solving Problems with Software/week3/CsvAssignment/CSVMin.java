
/**
 * Write a description of CSVMin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        //For each row (currentRow) in the CSV File
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }

    public void testColdestInDay () {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEDT"));
    }
///////////////////////////////////////////////
    public double averageTemperatureInFile(CSVParser parser){
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            totalTemp += currentTemp;
            count += 1;
        }
        double avgTemp = totalTemp / count;
        return avgTemp;
    }
    
    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }
///////////////////////////////////////////////
    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }

    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        String fileName = "";
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
            if (currentRow == smallestSoFar){
                fileName = f.getName();
            }
        }
        //The largestSoFar is the answer
        return fileName;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < smallestTemp && currentTemp != -9999) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }

    public void testColdestInManyDays () {
        CSVRecord smallest = coldestInManyDays();
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
///////////////////////////////////////////////
    public void testFileWithColdestTemperature () {
        String fineNameOfColdestTemp = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fineNameOfColdestTemp);
        FileResource fr = new FileResource(fineNameOfColdestTemp);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));     
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(
            currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
    }
}

