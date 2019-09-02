/* Name: Template Method 
Use: Used to create a group of subclasses that have to execute a similar group of methods
Theme Seed: Rush
*/
public class TemplateMethodPattern {
    public static void main(String[] args) {
        new SugarRush().effect();
        new SkyDivingRush().effect();
    }
}
// sugar, skydiving, ziplining, base-jumping, running 
abstract class Rush {
    public boolean causedByExercise() { return true; }
    //private boolean causedByOther() { return !causedByExercise(); }
    public boolean isDeadly() { return false; }
    public abstract void healthResult();
    public abstract void lifeResult();
    public void effect() {
        if (causedByExercise()) { healthResult(); } 
        if (isDeadly()) { lifeResult(); }
        System.out.println();
    }
}
// Base above
class SugarRush extends Rush {
    public boolean causedByExercise() { return false; }
    public boolean isDeadly() { return true; }
    public void healthResult() {
        System.out.println("A sugar rush is probably unhealthy. Maybe slow down!");
    }
    public void lifeResult() {
        System.out.println("One sugar rush may not kill you if you're diabetic. Many are not good for anyone.");
    }
}
class SkyDivingRush extends Rush {
    public boolean isDeadly() { return true; }
    public void healthResult() {
        System.out.println("A skydiving rush is probably healthy... in moderation");
    }
    public void lifeResult() {
        System.out.println("A skydiving rush is great, but stay viligant and safe...or else");
    }
}
