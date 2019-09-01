/* Name: Bridge
Use: Decouple an abstraction from its implementation so that the two can vary independently 
Better Use: Progressively adding functionality while separating out major differences using abstract classes
Theme Seed: Furry
*/
public class BridgePattern {
    public static void main(String[] args) {
        Mammal berr = new Bear(new BearFur());
        berr.swim();
        berr.outsideCondition(true);
        System.out.println(berr);
    }
}
abstract class Fur {
    enum Type { NO, SHORT, LONG }
    boolean temp_mod, heavy_when_wet;
    Type curr_type;
    abstract void setType(Type type); // call init at end or self implem
    void init() {
        switch (curr_type) {
            case NO: temp_mod = false; heavy_when_wet = false; break;
            case SHORT: temp_mod = true; heavy_when_wet = false; break;
            case LONG: temp_mod = true; heavy_when_wet = true; break;
        }
    }
    public String toString() {
        return "has " + curr_type.name().toLowerCase() +
                " which does " + ((temp_mod) ? "" : "not ") + 
                "have temperature moderation from their fur " + 
                ((heavy_when_wet) ?  "and can not swim because of it" : 
                                    "and can swim despite having it");  
    }
}
abstract class Mammal {
    String name;
    Fur fur;
    // constructor should take a ref to a fur
    void swim() {
        String ret = "The " + name + " can " + 
                    ((fur.heavy_when_wet) ? "not " : "") + 
                    "swim well";
        System.out.println(ret);
    }
    void outsideCondition(boolean cold_outside) {
        String ret = "The " + name + " should " +      
                    ((!fur.temp_mod && cold_outside) ? "not " : "") + 
                    "be okay in the " +
                    ((cold_outside) ? "cold" : "heat");
        System.out.println(ret);
    }
    public String toString() {
        return "The " + name + " " + fur;
    }
}
// Design above
class BearFur extends Fur {
    public BearFur() {
        setType(Type.SHORT);
    }
    public void setType(Type type) {
        this.curr_type = type;
        init();
    }
}
class Bear extends Mammal {
    public Bear(Fur fur) { // could have other qualities provided
        this.fur = fur;
        this.name = "average bear";
    }
}
