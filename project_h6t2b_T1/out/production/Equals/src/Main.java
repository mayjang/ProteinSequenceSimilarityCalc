import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Part part1 = new Part(4, 5, 100, 200, 50);
        Part part2 = part1;
        Part part3 = new Part(4, 5, 100, 200, 50);
        Part part4 = new Part(4, 5, 150, 300, 40);
        Part part5 = new Part(4, 5, 100, 200, 70);
        Part part6 = new Part(4, 5, 100, 200, 60);
        Part part7 = new Part(4, 5, 100, 200, 40);
        ColouredPart colouredPart1 = new ColouredPart(Color.BLUE, 4, 5, 100, 200, 50);
        ColouredPart colouredPart2 = new ColouredPart(Color.RED, 4, 5, 100, 200, 40);
        ColouredPart colouredPart3 = new ColouredPart(Color.BLACK, 4, 5, 100, 200, 60);
        ColouredPart colouredPart4 = new ColouredPart(Color.GREEN, 4, 5, 100, 200, 70);
        ColouredPart colouredPart5 = new ColouredPart(Color.YELLOW, 4, 5, 100, 200, 40);

        System.out.println(part1.equals(part2));
        System.out.println(part7.equals(colouredPart1));
    }



}
