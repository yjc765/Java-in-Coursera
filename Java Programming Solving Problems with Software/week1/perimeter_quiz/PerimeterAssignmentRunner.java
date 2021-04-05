import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            //System.out.println("current length = " + currDist);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for(Point p: s.getPoints()){
            count = count + 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        int count = getNumPoints(s);
        double length = getPerimeter(s);
        double average = length / count;
        return average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double maxDist = 0.0;
        for(Point currPt: s.getPoints()){
            double currDist = prevPt.distance(currPt);
            //System.out.println("current length = " + currDist);
            if (currDist > maxDist){
                maxDist = currDist;
            }
            prevPt = currPt;
        }
        return maxDist;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double max_x = prevPt.getX();
        for(Point p: s.getPoints()){
            double curr_x = p.getX();
            if(curr_x > max_x){
            max_x = curr_x;}
        }
        return max_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double max_perimeter = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); 
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > max_perimeter){
                max_perimeter = length;
            }
        }
        return max_perimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double max_perimeter = 0;
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f); 
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(length > max_perimeter){
                max_perimeter = length;
                temp = f;
            }
        }
        ;    // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        //int count = getNumPoints(s);
        //System.out.println("number of points is " + count);
        double average = getAverageLength(s);
        System.out.println("average length = " + average);
        double maxLength = getLargestSide(s);
        System.out.println("maximal length = " + maxLength);
        //double max_x = getLargestX(s);
        //System.out.println("maximal x = " + max_x);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double max_length = getLargestPerimeterMultipleFiles();
        System.out.println("maximal perimeter = " + max_length);
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("file of maximal perimeter = " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testFileWithLargestPerimeter();
    }
}
