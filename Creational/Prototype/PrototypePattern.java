/* Name: Prototype
Use: Adding a subclass instance of a known super class at runtime
To create new objects by cloning other objects
Theme Seed: Labored
*/
import java.util.*;
public class PrototypePattern {
    public static void main(String[] args) {
        CloneFactory taskMaster = new CloneFactory();
        Lifting roy = new Lifting(7, 7, 225);
        Lifting chris = (Lifting) taskMaster.getClone(roy);
        //
        System.out.println("" + System.identityHashCode(roy) + 
                            " Roy's Workout: " + roy);       
        System.out.println("" + System.identityHashCode(chris) + 
                            " Chris' Workout: " + chris);       
        //
        chris.sets /= 2;
        chris.reps /= 2;
        chris.weight /= 2;
        //
        System.out.println("\nCorrection:");
        System.out.println("" + System.identityHashCode(roy) + 
                            " Roy's Workout: " + roy);       
        System.out.println("" + System.identityHashCode(chris) + 
                            " Chris' Workout: " + chris);       
    }
}
interface Labor extends Cloneable {
    public Labor makeCopy();
}
class Lifting implements Labor {
    public int sets, reps, weight;
    public Lifting(int sets, int reps, int weight) {
        // init
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }
    public Labor makeCopy() {
        Labor obj = null;
        try {
            obj = (Lifting) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public String toString() {
        return "Sets: " + sets + ", Reps: " + reps + ", Weight: " + weight;
    }
}
class CloneFactory {
    public Labor getClone(Labor origin) {
        return origin.makeCopy();
    }
}
