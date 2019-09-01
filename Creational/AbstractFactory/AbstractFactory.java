import java.util.*;
/* Name: AbstractFactory
Use: Factory but more extensible and controllable
Random seed for theme: meeting
*/
class AbstractFactoryPattern {
    public static void main(String[] args) {
        Meeting conf = MeetingMaker.make(Type.Conference);  
        System.out.println(conf);
        conf.composeMeeting("80th Annual Convertion Convention", 1000, 10.0);
        conf.makeSpeaker("Chris King");
        conf.makeService("Food I guess");
        conf.makePanel("8:00 am Friday thru Sun");
        System.out.println(conf);
    }
}
enum Type { Conference, Symposium }
class MeetingMaker {
    public static Meeting make(Type type) {
        Meeting meet = null;
        switch (type) {
            case Conference: meet = new ConferenceBuilder().buildMeeting(); break;
        }
        return meet;
    }
}
abstract class MeetingBuilder {
    protected abstract Meeting makeMeeting();
    public Meeting buildMeeting() {
        Meeting meet = makeMeeting();
        //modify meeting below

        //above
        return meet;
    }
} 
class ConferenceBuilder extends MeetingBuilder {
    protected Meeting makeMeeting() {
        return new Conference(new ConferenceComponentFactory());
    }
}
interface MeetingComponentFactory {
    // speakers, cost, num of guests, services (sercurity, food, 
    public Speaker addSpeaker(String name);
    public Service addService(String name);
    public Panel addDetails(String name);
}
class ConferenceComponentFactory implements MeetingComponentFactory {
    public Speaker addSpeaker(String name) { return new Speaker(name); }
    public Service addService(String name) { return new Service(name); }
    public Panel addDetails(String name) { return new Panel(name); }
}
abstract class Meeting {
    String name;
    List<Speaker> speakers = new ArrayList<>();
    List<Service> services = new ArrayList<>();
    List<Panel> tabs = new ArrayList<>();
    int guests;
    double cost;

    abstract void composeMeeting(String name, int guests, double cost); 
    abstract Speaker makeSpeaker(String name);
    abstract Service makeService(String name);
    abstract Panel makePanel(String name);

    public String toString() {
        return "\n" + name + "\nWith speakers " + speakers + "\nTalking to " + guests + " guests\nWhich can be you for only $" + cost + "\nAdditional info:\n" + services + "\n" + tabs + "\n";
    }
}    
class Conference extends Meeting {
    MeetingComponentFactory partsFactory;
    public Conference(MeetingComponentFactory partsFactory) {
        this.partsFactory = partsFactory;
        this.name = "";
        this.guests = 0;
        this.cost = 0.0;
    }
    public void composeMeeting(String name, int guests, double cost) {
        this.name = name; // can add more functionality here
        this.guests = guests;
        this.cost = cost;
    }
    public Speaker makeSpeaker(String name) {
        Speaker speaker = partsFactory.addSpeaker(name);
        speakers.add(speaker);
        return speaker; // does it pass a mempry reference in java
    }
    public Service makeService(String name) {
        Service service = partsFactory.addService(name);
        services.add(service);
        return service; // does it pass a mempry reference in java
    }
    public Panel makePanel(String name) {
        Panel panel = partsFactory.addDetails(name);
        tabs.add(panel);
        return panel; // does it pass a mempry reference in java
    }
}
class Printable {
    String name;
    public String toString() { return name; } 
}
class Speaker extends Printable { public Speaker(String name) { this.name = name; } }
class Service extends Printable { public Service(String name) { this.name = name; } }
class Panel extends Printable { public Panel(String name) { this.name = name; } }
            /*case Type.Symposium:
                MeetingComponentFactory mcf = new SymposiumComponentFactory();
                meet = new Symposium(mcf);
                break;*/
