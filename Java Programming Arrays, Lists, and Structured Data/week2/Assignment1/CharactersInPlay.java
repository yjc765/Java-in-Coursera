
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;
public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    public CharactersInPlay() {
        characters = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    public void update(String person){
        int index = characters.indexOf(person);
        if (index == -1){
            characters.add(person);
            counts.add(1);
        }
        else{
            int freq = counts.get(index);
            counts.set(index,freq + 1);
        }
    }
    public void findAllCharacters(){
        FileResource resource = new FileResource();
        counts.clear();
        characters.clear();
        for(String line : resource.lines()){
            int indexPeriod = line.indexOf('.');
            if (indexPeriod != -1){
                String character = line.substring(0, indexPeriod);
                update(character);
            }

        }
    }
    
    public int findMax(){
        int max = counts.get(0);
        int maxIndex = 0;
        for(int k=0; k < counts.size(); k++){
            if (counts.get(k) > max){
                max = counts.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        findAllCharacters();
        //for (int i = 0; i < counts.size(); i++){
        //    System.out.println(characters.get(i) + "\t" + counts.get(i));
        //}
        
        int index = findMax();
        System.out.println("max character/count: "+ characters.get(index) + " "+ counts.get(index));
        charactersWithNumParts(10, 100);
    }
    public void charactersWithNumParts(int num1, int num2){
        for (int i = 0; i < counts.size(); i++){
            if (counts.get(i) >= num1 && counts.get(i) <= num2){
                System.out.println(characters.get(i) + "\t" + counts.get(i));
            }
        }
    } 
}
