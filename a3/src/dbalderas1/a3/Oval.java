package dbalderas1.a3;

/**
 * Oval Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class Oval extends Circle {
    private final Exception nonOvalException = new Exception("if radius and radius2 are the same use Circle class");
    private float radius2;
    private double pi = Math.PI;

    /**
     * Default constructor sets type and radius2
     */

    public Oval() {
        super();
        this.setType(Type.CIRCLE);
        this.radius2 = 0.0f;
    }

    /**
     * Override constructor sets type and radius
     *
     * @param radius  float representing the radius of the oval
     * @param radius2 float representing the radius of the oval
     */

    public Oval(float radius, float radius2) throws Exception {
        super();

        if (radius == radius2) {
            throw nonOvalException;
        }

        this.setType(Type.OVAL);
        this.setRadius(radius);
        this.setRadius2(radius2);
    }

    /**
     * Override constructor gets the radius of the oval
     *
     * @return float representing the radius of the oval
     */

    public float getRadius2() {

        return this.radius2;
    }

    /**
     * Override constructor sets the radius of the oval
     *
     * @param radius2 float representing the radius of the oval
     */

    public void setRadius2(float radius2) {

        this.radius2 = Math.max(0.0f, radius2);
    }

    /**
     * Override gets the area of the oval
     *
     * @return double representing the area of the oval
     */

    @Override
    public double getArea() {

        return ((pi) * (this.getRadius() * this.getRadius2()));
    }


    /**
     * Override gets the string of the oval
     *
     * @return string representing the result of the oval
     */

    @Override
    public String toString() {
        return String.format("%s|Radius2: %-10.2f", super.toString(), this.getRadius2());
    }


}