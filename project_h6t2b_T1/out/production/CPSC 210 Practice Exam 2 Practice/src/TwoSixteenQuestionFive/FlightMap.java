package TwoSixteenQuestionFive;

import java.util.HashSet;
import java.util.Set;

public class FlightMap {

    private Set<Route> routes;

    public FlightMap() {
        routes = new HashSet<Route>();
    }

    public void addRoute(Route route){
        routes.add(route);
    }

    public void removeRoute(Route route){
        routes.remove(route);
    }
    
}
