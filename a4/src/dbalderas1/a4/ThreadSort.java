package dbalderas1.a4;

import dbalderas1.a3.Shape;

/**
 * ThreadSort Driver class for A4
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class ThreadSort extends Thread {
    private final Shape[] tShapes;

    public ThreadSort(Shape[] shapes, int lowBounds, int upperBounds) {
        this.tShapes = new Shape[upperBounds - lowBounds];

        System.arraycopy(shapes, lowBounds, this.tShapes, 0, (upperBounds - lowBounds));

    }

    @Override
    public void run() {
        System.out.print("\nThread Started\n");
        int n = this.tShapes.length;
        Shape tmp;
        for(int i=0; i<n; i++) {
            for(int j=1; j<n; j++) {
                if(this.tShapes[j-1].getArea() > this.tShapes[j].getArea()) {
                    // Swap
                    tmp = this.tShapes[j-1];
                    this.tShapes[j-1] = this.tShapes[j];
                    this.tShapes[j] = tmp;
                }
            }
        }
        System.out.println("Thread Complete\n");

    }

    public Shape[] gettShapes() {
        return tShapes;
    }
}
