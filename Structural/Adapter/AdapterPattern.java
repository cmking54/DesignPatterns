/* Name: Adapter
Use: Allows 2 Interfaces To Work Together
Theme Seed: Renounce
*/
public class AdapterPattern {
    public static void main(String[] args) {
        RoundEarther[] survey = new RoundEarther[]{new AveragePerson(),
                                                    new Renouncement(new FlatEarther())};
        for (RoundEarther re: survey) {
            re.gravity();
            re.dayAndNight();
            re.sphereEarth();
            System.out.println();
        }
    }
}
interface RoundEarther {
    void gravity();
    void dayAndNight();
    void sphereEarth();
}
class AveragePerson implements RoundEarther {
    public void gravity() {
        System.out.println("Gravity keeps us in place; -32 ft^2/s towards the Earth's core");
    }
    public void dayAndNight() {
        System.out.println("Earth rotates, with the Sun and Moon showing on the presented sides");
    }
    public void sphereEarth() {
        System.out.println("Earth is a sphere, just like how a globe presents it");
    }
}

class FlatEarther {
    public void discAcceleration() {
        System.out.println("Gravity is fake; Our Disc accelerates up at 32 ft^2/s");
    }
    public void dayAndNight() {
        System.out.println("The sun and moon are more of constantly circling spotlights");
    }
    public void discEarth() {
        System.out.println("Earth is a disc with the Arctic Circle in the middle");
    }
}

class Renouncement implements RoundEarther { // the Adapter
    FlatEarther person;
    public Renouncement(FlatEarther fe) {
        person = fe;
    }   
    public void gravity() {
        person.discAcceleration();
    }
    public void dayAndNight() {
        person.dayAndNight();
    }
    public void sphereEarth() {
        person.discEarth();
    }
}
