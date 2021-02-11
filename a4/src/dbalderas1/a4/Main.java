package dbalderas1.a4;

import dbalderas1.a3.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Main Driver class for A4
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Main {

    /**
     * Main entry for A4
     * @param args String array that holds command line arguments
     */

    public static void main(String[] args) throws Exception {
	    Scanner scanner = new Scanner(System.in);
	    Random random = new Random();
	    System.out.print("Do you want a [S]ingle Thread or [D]ual Thread or [Q]uad Thread? ");
	    char selection = scanner.next().charAt(0);

	    System.out.print("How many shapes do you want to sort? ");
	    int count = scanner.nextInt();
	    Shape[] shapes = new Shape[count];

	    for(int i=0; i<count; i++) {
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

	    switch(selection) {
            case 's':
            case 'S':
                singleSort(shapes);
                break;
            case 'd':
            case 'D':
                dualSort(shapes);
                break;
            case 'q':
            case 'Q':
              quadSort(shapes);

        }

    }

    private static void singleSort(Shape[] shapes) throws InterruptedException {
        ThreadSort threadSort = new ThreadSort(shapes, 0, shapes.length);
        long startTime = System.nanoTime();
        threadSort.start();
        threadSort.join();
        long endTime = System.nanoTime();

        long duration = (endTime-startTime) / 1000000;

        for(Shape s : threadSort.gettShapes()) {
            System.out.println(s);
        }
        System.out.println("\nSingle Thread Sort Time: " + duration);
    }

    private static void dualSort(Shape[] shapes) throws InterruptedException {
        int mid = Math.round(shapes.length / 2);

        ThreadSort t1 = new ThreadSort(shapes, 0, mid);
        ThreadSort t2 = new ThreadSort(shapes, mid, shapes.length);
        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m = new MergeSort(t1.gettShapes(), t2.gettShapes());
        m.start();
        m.join();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime / 1000000);

        for(Shape s : m.getSortedShapes()) {
            System.out.println(s);
        }
        System.out.println("\nDual Thread Sort Time: " + duration);
    }

    private static void quadSort(Shape[] shapes) throws InterruptedException {
        int shapeCount = shapes.length / 4;
        int shapesRemaining = shapes.length - (shapeCount * 3);

        ThreadSort[] sortThread = new ThreadSort[4];

        long startTime = System.nanoTime();
        for(int i=0; i<3; i++) {
            sortThread[i] = new ThreadSort(shapes, i * shapeCount, i * shapeCount + shapeCount);
            sortThread[i].start();
        }
        sortThread[3] = new ThreadSort(shapes, shapes.length - shapesRemaining, shapes.length);
        sortThread[3].start();


        for(ThreadSort t : sortThread) {
            t.join();
        }

        // If we make here all four sorts are done
        MergeSort m1 = new MergeSort(sortThread[0].gettShapes(), sortThread[1].gettShapes());
        MergeSort m2 = new MergeSort(sortThread[2].gettShapes(), sortThread[3].gettShapes());

        m1.start();
        m2.start();

        m1.join();
        m2.join();

        MergeSort m3 = new MergeSort(m1.getSortedShapes(), m2.getSortedShapes());
        m3.start();
        m3.join();

        long endTme = System.nanoTime();
        long duration = (endTme - startTime) / 1000000;

        for(Shape s : m3.getSortedShapes()) {
            System.out.println(s);
        }
        System.out.println("\nQuad Thread Sort Time: " + duration);
    }
}
