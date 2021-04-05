/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record : parser) {
            //Look at the "Exports" column
            String export = record.get("Exports");
            //Check if it contains exportOfInterest
            if (export.contains(exportOfInterest)) {
                //If so, write down the "Country" from that row
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public String countryInfo(CSVParser parser, String countryOfInterest){
       for (CSVRecord record : parser){
           String country = record.get("Country");
           if (country.contains(countryOfInterest)){
               String export = record.get("Exports");
               String value = record.get("Value (dollars)");
               String res = country + ": " + export + ": " + value;
               return res;
           }
       }
       return "NOT FOUND";
       
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
    
    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee"); 
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String res = countryInfo(parser, "China");
        System.out.println(res);
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton",  "flowers");
        parser = fr.getCSVParser();
        int count = numberOfExporters(parser, "cocoa");
        System.out.println(count);
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
