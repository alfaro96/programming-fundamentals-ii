package shapes;

/**
 * Represents an abstract geometric figure.
 * <p>
 * This class serves as the base blueprint for specific figures in the system.
 * It encapsulates common styling attributes and defines the contract for
 * mathematical calculations through abstract methods.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public abstract class GeometricFigure {

    /** The internal fill color of the figure. */
    private String fillColor;

    /** The outline or border color of the figure. */
    private String borderColor;

    /**
     * Constructs a new {@link GeometricFigure} with only a fill color.
     *
     * @param fillColor The color used to fill the figure.
     */
    protected GeometricFigure(String fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Constructs a new {@link GeometricFigure} specifying both fill and border colors.
     *
     * @param fillColor The color used to fill the figure.
     * @param borderColor The color used for the figure's outline.
     */
    protected GeometricFigure(String fillColor, String borderColor) {
        this(fillColor); // Explicit call to the previous constructor
        this.borderColor = borderColor;
    }

    /**
     * Calculates the perimeter of the geometric figure.
     *
     * @return The perimeter of the figure as a {@code double}.
     */
    public abstract double calculatePerimeter();

    /**
     * Calculates the total area of the geometric figure.
     *
     * @return The area of the figure as a {@code double}.
     */
    public abstract double calculateArea();

    /**
     * Retrieves the fill color.
     *
     * @return A {@code String} representing the fill color.
     */
    public String getFillColor() {
        return fillColor;
    }

    /**
     * Updates the fill color.
     *
     * @param fillColor The new fill color to apply.
     */
    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * Retrieves the border color.
     *
     * @return A {@code String} representing the border color.
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * Updates the border color.
     *
     * @param borderColor The new border color to apply.
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }
}
