package dbalderas1.a4;

import dbalderas1.a3.Shape;

/**
 * MergeSort Driver class for A4
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class MergeSort extends Thread {
    private Shape[] shapes1;
    private Shape[] shapes2;
    private Shape[] sortedShapes;

    /**
     * Override constructor sets sort and shape
     * @param s1 representing the sorting of the shape
     * @param s2 representing the sorting of the shape
     */

    public MergeSort(Shape[] s1, Shape[] s2) {
        this.shapes1 = s1;
        this.shapes2 = s2;
        this.sortedShapes = new Shape[s1.length + s2.length];
    }


    /**
     * Override gets the sort of the shape
     * @return string representing the result/sort of the shape
     */


    @Override
    public void run() {
        System.out.println("Merge Thread Started");
        int i=0; // Current Index of shape1
        int j=0; // Current Index of shape2
        int k=0; // Current Index of sortedShapes

        while(i < shapes1.length && j < shapes2.length) {
            if(this.shapes1[i].getArea() < this.shapes2[j].getArea()) {
                this.sortedShapes[k++] = this.shapes1[i++];
            } else {
                this.sortedShapes[k++] = this.shapes2[j++];
            }
        }

        while(i < this.shapes1.length) {
            this.sortedShapes[k++] = this.shapes1[i++];
        }

        while(j < this.shapes2.length) {
            this.sortedShapes[k++] = this.shapes2[j++];
        }
        System.out.println("Merge Thread Complete\n");
    }

    public Shape[] getSortedShapes() {
        return sortedShapes;
    }
}
