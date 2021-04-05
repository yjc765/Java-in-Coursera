
/**
 * Write a description of CSVHumidity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class CSVHumidity {
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
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

    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles () {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        
        if (smallestSoFar == null ) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            if (!currentRow.get("Humidity").equals("N/A")){
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
                if (currentHumidity < smallestHumidity) {
                //If so update largestSoFar to currentRow
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
        double totalTemp = 0;
        int count = 0;
        for (CSVRecord currentRow : parser) {
            // use method to compare two records
            if (currentRow.get("Humidity") != "N/A"){
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                if (currentHumidity >= value){
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    totalTemp += currentTemp;
                    count += 1;
                } 
            }
        }
        if (count == 0){
            return 0;
        }
        double avgTemp = totalTemp / count;
        return avgTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile () {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if(avgTemp == 0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }
    }    
    public void testLowestHumidityInManyFiles () {
        CSVRecord smallest = lowestHumidityInManyFiles ();
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
                   " at " + smallest.get("DateUTC"));
    }
}
