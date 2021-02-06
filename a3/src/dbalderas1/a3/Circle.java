package dbalderas1.a3;

/**
 * Circle Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Circle extends Shape {
    private float radius;
    private double pi = Math.PI;

    /**
     * Default constructor sets type and radius
     */

    public Circle() {
        super();
        this.setType(Type.CIRCLE);
        this.radius = 0.0f;
    }

    /**
     * Override constructor sets type and radius
     *
     * @param radius float representing the radius of the circle
     */

    public Circle(float radius) {
        this.setType(Type.CIRCLE);
        this.setRadius(radius);
    }

    /**
     * Override constructor gets the radius of the circle
     *
     * @return float representing the radius of the circle
     */

    public float getRadius() {

        return this.radius;
    }

    /**
     * Override constructor sets the radius of the circle
     *
     * @param radius float representing the radius of the circle
     */

    public void setRadius(float radius) {

        this.radius = Math.max(0.0f, radius);
    }

    /**
     * Override constructor gets the area of the circle
     *
     * @return double representing the area of the circle
     */

    public double getArea() {

        return ((pi) * (this.getRadius() * this.getRadius()));
    }

    /**
     * Override gets the string of the circle
     *
     * @return string representing the result of the circle
     */

    @Override
    public String toString() {
        return String.format("%s |Area: %-10.2f |Radius: %-10.2f", super.toString(), this.getArea(), this.getRadius());
    }

}
