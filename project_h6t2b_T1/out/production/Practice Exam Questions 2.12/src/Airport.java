

public class Airport {

    private String airportCode;


    public Airport(String code){
        this.airportCode = code;
    }

    public String getAirportCode() {
        return this.airportCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (airportCode.equals(airport.getAirportCode())) return true;
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = airportCode.hashCode();
        return result;
    }
}
