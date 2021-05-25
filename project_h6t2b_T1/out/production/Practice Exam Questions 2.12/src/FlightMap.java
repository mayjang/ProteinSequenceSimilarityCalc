import java.util.HashSet;
import java.util.Set;

public class FlightMap {

    private Set<Route> routes;


    public FlightMap() {

        routes = new HashSet<>();
    }

    public void addRoute(Route r) {
        if (!routes.contains(r)) {
            routes.add(r);
        }
    }

    public void removeRoute(Route r) {
        if (routes.contains(r)) {
            routes.remove(r);
        }
    }
}
