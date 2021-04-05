
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testUniqIP(){
         LogAnalyzer la = new LogAnalyzer();
         la.readFile("weblog2_log");
         int uniqueIPs = la.countUniqueIPs();
         System.out.println("There are " + uniqueIPs + " IPs");
     }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPsOnDay = la.uniqueIPVisitsOnDay("Sep 24");
        for (String ip : uniqueIPsOnDay) {
             System.out.println(ip);
        }
        System.out.println(uniqueIPsOnDay.size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int count = la.countUniqueIPsInRange(400,499);
        System.out.println(count);

    }
    
    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxCount = la.mostNumberVisitsByIP(counts);
        System.out.println(maxCount);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> ipArray = la.iPsMostVisits(counts);
        System.out.println(ipArray);
    }
    
    public void testiPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String, ArrayList<String>> iPsDayMap = la.iPsForDays();
        System.out.println(iPsDayMap);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPsDayMap = la.iPsForDays();
        String mostDay = la.dayWithMostIPVisits(iPsDayMap);
        System.out.println(mostDay);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> iPsDayMap = la.iPsForDays();
        ArrayList<String> ipArray = la.iPsWithMostVisitsOnDay(iPsDayMap, "Sep 30");
        System.out.println(ipArray);
    }
}
