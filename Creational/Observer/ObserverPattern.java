import java.util.*;
import java.text.DecimalFormat;
/*
Name: Observer
Use: To update many objects on a change
*/
public class ObserverPattern {
    public static void main(String[] args) {
        StockGrabber stockGrabber = new StockGrabber();
        IBMPrice ibmObserver = new IBMPrice(stockGrabber);
        stockGrabber.setStockPrice("IBM", 131.36);
        new Thread(new StockSim(stockGrabber, 1, "IBM", 131.36)).start();
    }
}
interface Subject {    
    public void register(Observer o); // add
    public void unregister(Observer o); // remove
    public void notifyObserver(); // push message
}
interface Observer {
    public void update(double price);
   
     public String getName();
}

class StockGrabber implements Subject{
    private ArrayList<Observer> observers;
    private HashMap<String,Double> stocks;
    public StockGrabber() {
        observers = new ArrayList<Observer>();
        stocks = new HashMap<>();
    }
    public void register(Observer o) { // add
        observers.add(o);
    }
    public void unregister(Observer o) { // remove
        observers.remove(o);
    }
    public void notifyObserver() { // push message
        for (Observer observer: observers) {
            if (stocks.containsKey(observer.getName())) {
                observer.update(stocks.get(observer.getName()));
                return; // add bool
            }
        }
    }
    public void setStockPrice(String name, double newPrice) {
        stocks.put(name, newPrice);
        notifyObserver(); 
    }
}

class IBMPrice implements Observer { // should be a singleton
    //StockGrabber sg;
    final String name = "IBM";
    double price;
    public IBMPrice(StockGrabber sg) {
        //this.sg = sg;
        sg.register(this);
    }
    public void update(double price) {
        this.price = price;
        System.out.println(name + ": " + price);
    }
    public String getName() {
        return name;
    }
}

class StockSim implements Runnable {
    private int startDelay;
    private String stock; // make Stock object later
    private double price;
    private StockGrabber sg;
    public StockSim(StockGrabber sg, int delay, String newStock, double newPrice) {
        this.sg = sg;
        startDelay = delay;
        stock = newStock;
        price = newPrice;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try { Thread.sleep(startDelay * 1000); } 
            catch (Exception e) {}
            double flux = (Math.random() * (0.06)) - 0.03;
            DecimalFormat df = new DecimalFormat("#.##");
            price = Double.valueOf(df.format(price + flux));
            sg.setStockPrice(stock, price);
        }
    }
}
// class AAPLPrice implements Observer {}
