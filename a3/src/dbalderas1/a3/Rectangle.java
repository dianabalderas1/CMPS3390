package dbalderas1.a3;

/**
 * Rectangle Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Rectangle extends Square {
    private final Exception nonRectangleException = new Exception("if width and height are the same use Square class");
    private float height;

    /**
     * Default constructor sets type and height
     */

    public Rectangle() {
        super();
        this.setType(Type.SQUARE);
        this.height = 0.0f;
    }

    /**
     * Override constructor sets type, width, and height
     *
     * @param width  float representing the width of the rectangle
     * @param height float representing the height of the rectangle
     */

    public Rectangle(float width, float height) throws Exception {
        super();

        if (width == height) {
            throw nonRectangleException;
        }

        this.setType(Type.RECTANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Override constructor gets the height of the rectangle
     *
     * @return float representing the height of the rectangle
     */

    public float getHeight() {

        return this.height;
    }

    /**
     * Override constructor sets the height of the rectangle
     *
     * @param height float representing the height of the rectangle
     */


    public void setHeight(float height) {
        this.height = Math.max(0.0f, height);
    }

    /**
     * Override gets the area of the rectangle
     *
     * @return double representing the area of the rectangle
     */

    @Override
    public double getArea() {

        return (this.getWidth() * this.getHeight());
    }

    /**
     * Override gets the string of the rectangle
     *
     * @return string representing the result of the rectangle
     */

    @Override
    public String toString() {
        return String.format("%s |Height: %-10.2f ", super.toString(), this.getHeight());
    }


}
