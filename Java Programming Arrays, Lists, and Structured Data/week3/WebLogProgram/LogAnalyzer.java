
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }

     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry le: records){
             int statusCode = le.getStatusCode();
             if (statusCode > num){
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         for (LogEntry le: records){
             Date time = le.getAccessTime();
             String timeString = time.toString();
             //System.out.println(timeString);
             String day = timeString.substring(4, 10);
             //System.out.println(day);
             if (someday.equals(day) ){
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIPsOnDay.contains(ipAddr))
                    uniqueIPsOnDay.add(ipAddr);
             }
         }   
         return uniqueIPsOnDay;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIP = new ArrayList<String>();
         for (LogEntry le: records){
             int statusCode = le.getStatusCode();
             if (statusCode >= low && statusCode <= high){
                 String ipAddr = le.getIpAddress();
                 if(!uniqueIP.contains(ipAddr))
                    uniqueIP.add(ipAddr);
             }
         }
         return uniqueIP.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         for(LogEntry le: records){
             String ip = le.getIpAddress();
             if(!ipCounts.containsKey(ip)){
                 ipCounts.put(ip, 1);
             }
             else{
                 ipCounts.put(ip, ipCounts.get(ip) + 1);
             }
         }
         return ipCounts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts){
         int maxCount = 0;
         for(String ip : ipCounts.keySet()){
             int currCount = ipCounts.get(ip);
             if (currCount > maxCount){
                 maxCount = currCount;
             }
         }
         return maxCount;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts){
         ArrayList<String> ipArray = new ArrayList<String>();
         int maxCount = 0;
         
         for (Integer v : ipCounts.values()) {
             if (v > maxCount){
                 maxCount = v;
             }   
         }
         
         for(String ip : ipCounts.keySet()){
             int currCount = ipCounts.get(ip);
             if (currCount == maxCount){
                 ipArray.add(ip);
             }
         }
         return ipArray;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> iPsDayMap = new HashMap<String, ArrayList<String>>();
         for (LogEntry le: records){
             Date time = le.getAccessTime();
             String timeString = time.toString();
             //System.out.println(timeString);
             String day = timeString.substring(4, 10);
             //System.out.println(day);
             String ipAddr = le.getIpAddress();
             if (!iPsDayMap.containsKey(day)){
                 ArrayList<String> ipList = new ArrayList<String>();
                 ipList.add(ipAddr);
                 iPsDayMap.put(day, ipList);
             }
             else{
                 ArrayList<String> list = iPsDayMap.get(day);
                 list.add(ipAddr);
                 iPsDayMap.put(day, list);
             }
         }
         return iPsDayMap;
     }   
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsDayMap){
         String suchDay = null;
         int mostIp = 0;
         for (String day : iPsDayMap.keySet()){
             ArrayList <String> iPs = iPsDayMap.get(day);
             int numIp = iPs.size();
             if (numIp > mostIp){
                 suchDay = day;
                 mostIp = numIp;
             }
         }
         return suchDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsDayMap, String day){
         ArrayList<String> iPs = iPsDayMap.get(day);
         HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
         for(String ip: iPs){
             if(!ipCounts.containsKey(ip)){
                 ipCounts.put(ip, 1);
             }
             else{
                 ipCounts.put(ip, ipCounts.get(ip) + 1);
             }
         }
         
         ArrayList<String> ipArray = iPsMostVisits(ipCounts);
         return ipArray;
     }
}
