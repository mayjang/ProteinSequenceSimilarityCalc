import java.awt.*;

public class ColouredPart extends Part {
    private Color backgroundColor;
    public ColouredPart(Color color, int x, int y, int width, int height, int value) {
        super(x, y, width, height, value);
        this.backgroundColor = color;
    }
}
