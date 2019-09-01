/* Name: Command
Use: Representing and encapsualting all the info needed to call a method at a later time
Theme Seed: Unusual
*/
public class CommandPattern {
    public static void main(String[] args) {
        Performer tomJones = new Singer();
        /*
        Lyric[] verse = new Lyric[]{new ItsNotUnusual(), new LovedByAnyone(),
                                    new ItsNotUnusual(), new HaveFunWithAnyone(),
                                    new HangingWithAnyone(),
                                    new ItsNotUnusual(), new SeeMeCry()
                                    };
        for (Lyric bar: verse) {
            ((Singer)tomJones).setBar(bar);
            tomJones.perform();
        }
        */
        // Simplified Loop Of Above
        Lyric[] verse_simp = new Lyric[]{new LovedByAnyone(), 
                                        new HaveFunWithAnyone(), 
                                        new HangingWithAnyone(), 
                                        new SeeMeCry()
                                        };
        Lyric repeat = new ItsNotUnusual();
        for (int i = 0; i < verse_simp.length; i++) {
            if (i != 2) {
                ((Singer)tomJones).setBar(repeat);
                tomJones.perform();
            }
            ((Singer)tomJones).setBar(verse_simp[i]);
            tomJones.perform();
        }   
    }
}
interface Performer {
    void perform();
}
interface Lyric {
    void sing();
}
// Base above
class LovedByAnyone implements Lyric {
    public void sing() {
        System.out.println("to be loved by anyone");
    }
}
class HaveFunWithAnyone implements Lyric {
    public void sing() {
        System.out.println("to have fun with anyone");
    }
}
class SeeMeCry implements Lyric {
    public void sing() {
        System.out.println("to see me cry, I wanna die");
    }
}
class HangingWithAnyone implements Lyric {
    public void sing() {
        System.out.println("But when I see you hanging about with anyone");
    }
}
class ItsNotUnusual implements Lyric {
    public void sing() {
        System.out.print("It's not unusual ");
    }
}
class Singer implements Performer {
    Lyric bar;
    public void setBar(Lyric bar) {
        this.bar = bar;
    }
    public void perform() {
        bar.sing();
    }
}
