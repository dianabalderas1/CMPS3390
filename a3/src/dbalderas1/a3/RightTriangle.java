package dbalderas1.a3;
/**
 * Right Triangle Driver class for A3
 *
 * @author Diana Balderas
 * @version 1.0
 */

public class RightTriangle extends Rectangle {
    private float height;

    /**
     * Default constructor sets type and height
     */

    public RightTriangle() {
        super();
        this.setType(Type.RIGHT_TRIANGLE);
        this.setHeight(0.0f);
    }

    /**
     * Override constructor sets type, width, and height
     *
     * @param width  float representing the width of the right triangle
     * @param height float representing the height of the right triangle
     */

    public RightTriangle(float width, float height) {
        super();
        this.setType(Type.RIGHT_TRIANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Override gets the height of the right triangle
     *
     * @return float representing the height of the right triangle
     */

    @Override
    public float getHeight() {

        return this.height;
    }

    /**
     * Override sets the height of the right triangle
     *
     * @param height float representing the height of the right triangle
     */

    @Override
    public void setHeight(float height) {

        this.height = Math.max(0.0f, height);
    }

    /**
     * Override gets the area of the right triangle
     *
     * @return double representing the area of the right triangle
     */

    @Override
    public double getArea() {

        return (((this.getWidth() * this.getHeight()) / 2));
    }

}
