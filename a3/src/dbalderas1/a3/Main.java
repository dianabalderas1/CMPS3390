package dbalderas1.a3;

import java.util.Random;
import java.util.Scanner;

/**
 * Main Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Main {

    /**
     * Main entry for A3
     *
     * @param args String array that holds command line arguments
     */

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("How many shapes do you want to generate? ");
        int count = scanner.nextInt();
        Shape[] shapes = new Shape[count];

        for (int i = 0; i < count; i++) {
            int t = random.nextInt(5);
            switch (t) {
                case 0:
                    shapes[i] = new Circle(random.nextFloat() * 20f);
                    break;
                case 1:
                    shapes[i] = new Oval(random.nextFloat() * 20f, random.nextFloat() * 20f);
                    break;
                case 2:
                    shapes[i] = new Square(random.nextFloat() * 20f);
                    break;
                case 3:
                    shapes[i] = new Rectangle(random.nextFloat() * 20f, random.nextFloat() * 20f);
                    break;
                case 4:
                    shapes[i] = new RightTriangle(random.nextFloat() * 20f, random.nextFloat() * 20f);
            }
        }

        for (Shape s : shapes) {
            System.out.println(s);
        }
    }
}
