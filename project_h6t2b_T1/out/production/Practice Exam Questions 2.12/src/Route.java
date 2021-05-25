public class Route {

    private Airport departureAirport;
    private Airport arrivalAirport;

    public Route(Airport departureAirport, Airport arrivalAirport) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (departureAirport.equals(route.departureAirport) &&
                (arrivalAirport.equals(route.arrivalAirport))) return true;
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = departureAirport.hashCode() + arrivalAirport.hashCode();
        return result;
    }

}
