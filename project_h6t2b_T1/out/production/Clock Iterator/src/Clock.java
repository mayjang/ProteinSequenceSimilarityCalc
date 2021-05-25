import java.util.Iterator;

public class Clock implements Iterable<Integer> {
    private Integer startTime;
    private Integer numHours;

    public Clock(Integer start, Integer hrs) {
        startTime = start;
        numHours = hrs;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ClockIterator();
    }

    private class ClockIterator implements Iterator<Integer> {

        private int currTime;

        public ClockIterator() {
            currTime = startTime;
        }

        @Override
        public boolean hasNext() {
            return currTime < (startTime + numHours);
        }

        @Override
        public Integer next() {
            Integer nextTime = currTime;
            currTime++;

            return nextTime;
        }
    }
}
