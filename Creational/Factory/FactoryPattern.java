/*
Name: Factory
Use: a method returns one of several possible classes that share a common super class
*/
public class FactoryPattern {
    public static void main(String[] args) {
        double experiment_pH = 0;
        Acidity solution = AcidityFactory.makeAcidity(experiment_pH);
        do {
            solution.behavior();
            solution = AcidityFactory.change_pH(solution, 1);
        } while (solution != null);
    }
}
class AcidityFactory {
    public static Acidity makeAcidity(double pH) {
        if (pH > 7) 
            return new Acidic(pH);
        else if (pH < 7)
            return new Basic(pH);
        else 
            return new Neutral(pH);
    }
    public static Acidity change_pH(Acidity solution, double pH) {
        double new_pH = solution.pH + pH;
        if (new_pH < 0 || new_pH > 14) 
            return null;
        return AcidityFactory.makeAcidity(new_pH);
    }
}
abstract class Acidity {
    double pH;
    abstract void behavior();
}
class Basic extends Acidity {
    public Basic(double pH) { this.pH = pH; }
    public void behavior() { System.out.println("" + pH + " is below 7.0; Hurts"); }
}
class Acidic extends Acidity {
    public Acidic(double pH) { this.pH = pH; }
    public void behavior() { System.out.println("" + pH + " is above 7.0; Hurts too"); }
}
class Neutral extends Acidity {
    public Neutral(double pH) { this.pH = pH; }
    public void behavior() { System.out.println("" + pH + " is 7.0; Safe, hopefully"); }
}
