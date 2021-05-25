package TwoSixteenQuestionFive;

public class Airport {

    private final String code;

    public Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport other = (Airport) o;
        return (code.equals(other.code));
    }
    
    @Override
    public int hashCode() {
        int result = 31 * code.hashCode();
        return result;
    }



}
