public class Part {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int value;

    public Part(int x, int y, int width, int height, int value) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        if (x != part.x) return false;
        if (y != part.y) return false;
        if (width != part.width) return false;
        if (height != part.height) return false;
        int valueDiff = value - part.value;
        return valueDiff >= -10 && valueDiff <= 10;
    }

}
