package dbalderas1.a3;
/**
 * Shape Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Shape {
    private Type type;
    private float height;
    private float width;
    private float radius;
    private float radius2;
    private double area;

    /**
     * Default constructor sets type, radius, etc.
     */

    public Shape() {
        this.type = Type.SHAPE;
        this.width = 0.0f;
        this.height = 0.0f;
        this.radius = 0.0f;
        this.radius2 = 0.0f;
        this.area = 0.0f;

    }

    /**
     * Override constructor gets the type of the shape
     *
     * @return type representing the type of the shape
     */

    public Type getType() {

        return this.type;
    }

    /**
     * Override constructor sets the type of the shape
     *
     * @param type type representing the type of the shape
     */

    public void setType(Type type) {

        this.type = type;
    }

    /**
     * Override constructor gets the height of the shape
     *
     * @return float representing the height of the shape
     */

    public float getHeight() {

        return height;
    }

    /**
     * Override constructor sets the height of the shape
     *
     * @param height float representing the height of the shape
     */

    public void setHeight(float height) {

        this.height = height;
    }

    /**
     * Override constructor gets the width of the shape
     *
     * @return float representing the width of the shape
     */

    public float getWidth() {

        return width;
    }

    /**
     * Override constructor sets the width of the shape
     *
     * @param width float representing the width of the shape
     */


    public void setWidth(float width) {

        this.width = width;
    }

    /**
     * Override constructor gets the radius of the shape
     *
     * @return float representing the radius of the shape
     */

    public float getRadius() {

        return radius;
    }

    /**
     * Override constructor sets the radius of the shape
     *
     * @param radius float representing the radius of the shape
     */


    public void setRadius(float radius) {

        this.radius = radius;
    }

    /**
     * Override constructor gets the radius of the shape
     *
     * @return float representing the radius of the shape
     */

    public float getRadius2() {

        return radius2;
    }

    /**
     * Override constructor sets the radius of the shape
     *
     * @param radius2 float representing the radius of the shape
     */


    public void setRadius2(float radius2) {

        this.radius2 = radius2;
    }

    /**
     * Override constructor gets the area of the shape
     *
     * @return double representing the area of the shape
     */

    public double getArea() {

        return area;
    }

    /**
     * Override constructor sets the area of the shape
     *
     * @param area double representing the area of the shape
     */


    public void setArea(double area) {

        this.area = area;
    }

    /**
     * Override gets the string of the shape
     *
     * @return string representing the result of the shape
     */

    @Override
    public String toString() {
        return String.format("Type: %-14s", this.type);

    }
}

