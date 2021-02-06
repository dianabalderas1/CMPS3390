package dbalderas1.a3;
/**
 * Square Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Square extends Shape {
    private float width;

    /**
     * Default constructor sets type and width
     */

    public Square() {
        super();
        this.setType(Type.SQUARE);
        this.width = 0.0f;
    }

    /**
     * Override constructor sets type and width
     *
     * @param width float representing the width of the square
     */

    public Square(float width) {
        this.setType(Type.SQUARE);
        this.setWidth(width);
    }

    /**
     * Override constructor gets the width of the square
     *
     * @return float representing the width of the square
     */

    public float getWidth() {
        return this.width;
    }

    /**
     * Override constructor sets the width of thr square
     *
     * @param width float representing the width of the square
     */

    public void setWidth(float width) {

        this.width = Math.max(0.0f, width);
    }

    /**
     * Override constructor gets the area of the square
     *
     * @return double representing the area of the square
     */

    public double getArea() {

        return (this.getWidth() * this.getWidth());
    }

    /**
     * Override gets the string of the square
     *
     * @return string representing the result of the square
     */

    @Override
    public String toString() {
        return String.format("%s |Area: %-10.2f |Width: %-10.2f", super.toString(), this.getArea(), this.getWidth());
    }

}

