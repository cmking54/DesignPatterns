/*Name: Decorator
Use: Allows for dynamic change of object
Theme Seed: Ignore
*/
class DecoratorPattern { 
    public static void main(String[] args) {
        System.out.print("Things to Ignore: ");
        System.out.println(new Quiet(new Nothing()).getName());
        System.out.print("Things to Ignore: ");
        System.out.println(new Quiet(new Bills(new Nothing())).getName());
    }
}
interface Ignorable {
    String getName();
}
class Nothing implements Ignorable {
    public Ignorable last;
    public String getName() { return "Nothing; "; }
}
// Base Above
class Quiet extends Nothing {
    public Quiet(Ignorable last) { this.last = last; }
    public String getName() { return last.getName() + "Too Quiet; "; }
}
class Bills extends Nothing {
    public Bills(Ignorable last) { this.last = last; }
    public String getName() { 
        return last.getName() + "Bills Can Get Paid Next Month; "; 
    }
}
