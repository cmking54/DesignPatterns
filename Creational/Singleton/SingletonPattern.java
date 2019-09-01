/* Name: Singleton
Use: To eliminate the option of instaniating more than one object of a given class
Theme Seed: Float
*/
import java.util.*;
public class SingletonPattern {
    private static void isOneInstanceOfSky(long id1, long id2) {
        if (id1 == id2) 
            System.out.println("One Sky");
        else 
            System.out.println("How are there two skies?");
    }
    public static void main(String[] args) {
        Sky firstInstance = Sky.getInstance(); // comment to test thread?
        System.out.print(Sky.getInstance());
        System.out.print(" But only: ");
        SingletonPattern.isOneInstanceOfSky(
            System.identityHashCode(firstInstance), 
            System.identityHashCode(Sky.getInstance())
        );
    }
}
class Sky { // holder of clouds
    private static Sky instance = null;
    public List<Cloud> atmosphere;
    private Sky() {
        atmosphere = Cloud.generateClouds();
    }
    public static Sky getInstance() {
        synchronized (Sky.class) { // thread safe
            if (instance == null) { 
                instance = new Sky();
                // init can go here
            }
        }
        return instance;
    }
    public String toString() {
        return "The Sky has " + atmosphere.size() + 
                " clouds today: " + atmosphere;
    }
}
class Cloud {
    String name;
    private Cloud() {
        name = new String[] {"Cirrus", "Cumulus", "Stratus"}
                                [new Random().nextInt(3)];
    }
    public static List<Cloud> generateClouds() {
        List<Cloud> list = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(5) + 5; i++)
            list.add(new Cloud());
        return list;
    }
    public String toString() {
        return name;
    }
}
