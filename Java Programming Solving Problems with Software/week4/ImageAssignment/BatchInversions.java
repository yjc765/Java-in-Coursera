
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class BatchInversions {
    public ImageResource makeInversion (ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //compute inPixel's red + inPixel's blue + inPixel's green
            //divide that sum by 3 (call it average)
            int InvPixelRed = 255 - inPixel.getRed();
            int InvPixelGreen = 255 - inPixel.getGreen();
            int InvPixelBlue = 255 - inPixel.getBlue();
            //set pixel's red to average
            pixel.setRed(InvPixelRed);
            //set pixel's green to average
            pixel.setGreen(InvPixelGreen);
            //set pixel's blue to average
            pixel.setBlue(InvPixelBlue);
        }
        //outImage is your answer
        return outImage;
    }
    
    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
        }
    }

    public void testInversion() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeInversion(ir);
        gray.draw();
    }
}

