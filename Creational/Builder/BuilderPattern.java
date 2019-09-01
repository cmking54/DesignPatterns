/* Name: Builder
Use: when the construction of a object composed of other objects
needs to be hidden
Theme Seed: Father
*/
import java.util.*;
public class BuilderPattern {
    public static void main(String[] args) {
        FatherModel dadTemplate = new DeadBeatModel();
        FatherDirector director = new FatherDirector(dadTemplate);
        director.makeFather();
        Father db = director.getFather();
        System.out.println(db);
    }
}
interface Parent {
    void setAvailability(boolean present);
    void setSupportStatus(boolean support); 
    void setKids(List<Child> kids);
    String toString();
}
class Father implements Parent {
    private boolean isPresent, supporting;
    private List<Child> kids;
    public void setAvailability(boolean present) { isPresent = present;}
    public void setSupportStatus(boolean support) { supporting = support;}
    public void setKids(List<Child> kids) { this.kids = kids; }
    public String toString() { return "He is " + 
                                    ((isPresent) ? "" : "not ") + 
                                    "present; He does " + 
                                    ((supporting) ? "" : "not ") +
                                    "support; He has " + kids.size() + " kids...";
    }
}
interface FatherModel {
    public void recordAvailability();
    public void recordSupportStatus();
    public void recordKids();
    public Father getFather();
}    
class DeadBeatModel implements FatherModel { // defines the data
    private Father dad;
    public DeadBeatModel() {
        dad = new Father();
    }
    public void recordAvailability() {
        dad.setAvailability(false);
    }
    public void recordSupportStatus() {
          dad.setSupportStatus(false);
    }
    public void recordKids() {
        List<Child> roster = new ArrayList<>();
        int i = 1,  end = new Random().nextInt(10);
        do { roster.add(new Child()); i++; } 
        while (i < end);
        dad.setKids(roster);
    }
    public Father getFather() { return dad; }
}
class FatherDirector {
    private FatherModel current_dad;
    public FatherDirector(FatherModel fm) {
        current_dad = fm;
    }
    public void makeFather() { // the birds and bees
        current_dad.recordAvailability();
        current_dad.recordSupportStatus();
        current_dad.recordKids();
    }
    public Father getFather() {
        return current_dad.getFather();
    }
}
class Child {}
