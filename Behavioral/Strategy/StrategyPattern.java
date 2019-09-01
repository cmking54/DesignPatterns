/*
Name: Strategy
Use: To dynamically assign behaviors
*/
public class StrategyPattern {
    public static void main(String[] args) {
        Blob blob = new Blob();
        blob.name = "Bob the Blob";
        
        blob.mood = new Sneak();
        System.out.println(blob.name + "'s move: " + blob.mood.action());
        
        blob.mood = new Shout();
        System.out.println(blob.name + "'s move: " + blob.mood.action());
        
        blob.mood = new Attack();
        System.out.println(blob.name + "'s move: " + blob.mood.action());
    }
}
class Blob {
    String name; 
    Behavior mood;
}

// making a custom datatype using polymorphism
interface Behavior {
    String action();
}
class Shout implements Behavior {
    public String action() {
        return "Look at me!";
    }
}
class Sneak implements Behavior {
    public String action() {
        return "**Hides in the shadow**";
    }
}
class Attack implements Behavior {
    public String action() {
        return "I'm hungry";
    }
}
