/* Name: Facade
Use: To make a simplified interface that performs many other actions behind the scenes
Theme Seed: Stride
*/
import java.util.*;
import java.io.*;
public class FacadePattern {
    public static Journey getLongestStride() {
        Journey journey, longest_journey = null;
        int dist, longest_dist = -1;
        for (State first: State.values()) {
            for (State second: State.values()) {
                journey = new Journey(first, second);
                dist = journey.getTripDist();
                if (longest_dist < dist) {
                    longest_dist = dist;
                    longest_journey = journey;
                }
            }
        }
        return longest_journey;
    }
    public static void main(String[] args) {
        Strides.generateUS();

        Journey road_trip = new Journey(State.NY, State.NJ);
        road_trip.addPitStop(State.CT);
        System.out.println(road_trip);

        Journey round_trip = new Journey(State.NY, State.NY);
        round_trip.addPitStop(State.CA);
        System.out.println(round_trip);   

        System.out.println(new Journey(State.NY, State.AK));   
        System.out.println(new Journey(State.WA, State.FL));   
        System.out.println("And the longest straight path across the country is\n" + getLongestStride());   
    }
}
class Journey { // the Facade
    State start, end;
    List<State> midpts;
    public Journey(State start, State end) {
        this.start = start;
        midpts = new ArrayList<>();
        midpts.add(start);
        this.end = end;
        //midpts.add(mid);
    }
    public void addPitStop(State mid) {
        midpts.add(mid);
    }
    public int getTripDist() {
        int total = 0;
        int currStride;
        for (int i = 1; i < midpts.size(); i++) {
            currStride = Strides.getShortestStride(midpts.get(i-1),midpts.get(i));
            if (currStride == -1) 
                return -1;
            total += currStride;
        }
        currStride = Strides.getShortestStride(midpts.get(midpts.size()-1), end);
        if (currStride == -1) {
            return -1;
        }
        return total + currStride;
    }
    public String toString() {
        int trip_dist = getTripDist();
        return "Current trip from " + start + " to " + end +
                ((trip_dist == -1) ? " is unwalkable." :
                " travels across " + trip_dist + " states.");
    }
}
class Strides { // sub-journey11
    static Map<State, Set<State>> graph = new HashMap<>();
    public static void put (State curr, State neighbor) {
        if (!graph.containsKey(curr)) {
            Set<State> value = new HashSet<>();
            value.add(neighbor);
            graph.put(curr, value);
        } else {
            graph.get(curr).add(neighbor);
        }
    }
    public static void put(State curr, Set<State> neighbors) {
        graph.put(curr, neighbors);
    }
    public static void generateUS() {
        try {
            Scanner sc = new Scanner(new File("usa_graph.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] line_split = line.strip().split(" ");
                Set<State> neighbors = new HashSet<State>();
                for (int i = 1; i < line_split.length; i++) {
                    neighbors.add(getState(line_split[i]));
                }
                put(getState(line_split[0]), neighbors);
            }
            sc.close();
        } catch (Exception e) {
        }
        //System.out.println(graph);
    }
    public static State getState(String text) {
        return Enum.valueOf(State.class, text);
    }
    public static Set<State> getNeighbors(State key) {
        return graph.get(key);
    }
    public static int getShortestStride(State start, State end) {
        int total_dist = 0;
        Set<State> currNeigh, nextNeigh, visited;
        currNeigh = new HashSet<>();
        nextNeigh = new HashSet<>();
        visited = new HashSet<>();
        
        currNeigh.add(start);

        while (currNeigh.size() > 0) {
            // System.out.println(currNeigh); // shows some path taken
            for (State currState: currNeigh) {
                if (!visited.contains(currState)) {
                    if (currState == end) {
                        return total_dist;
                    }
                    visited.add(currState);
                    if (getNeighbors(currState) != null) {
                        for (State next: getNeighbors(currState)) {
                            if (!visited.contains(next)) {
                                nextNeigh.add(next);
                            }
                        } 
                    }
                }
            }
            currNeigh = nextNeigh; // shallow copy?
            nextNeigh = new HashSet<State>();
            total_dist++;
        }
        return -1; //could not find -> non mainland
    } 
}
enum State {
    AL, AK, AZ, AR, CA, CO, CT, DE, DC, FL, GA, HI, 
    ID, IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN, 
    MS, MO, MT, NE, NV, NH, NJ, NM, NY, NC, ND, 
    OH, OK, OR, PA, PR, RI, SC, SD, TN, TX, US, 
    UT, VT, VA, WA, WV, WI, WY
}
