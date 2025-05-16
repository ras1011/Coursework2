/* **********************
 * CSC-20004 COURSEWORK *
 * Component 2         * 
 * 2024/25 First sit    *
 * **********************/
package uk.ac.keele.csc20004.robots.parts;

/**
 * This class implements RobotPart, but is abstract.
 * Its only purpose is to provide a definition for a frame as
 * a type (different from Motor, Sensor or Actuator) that can be further instantiated.
 * 
 * @author marcoortolani
 */
public abstract class Frame extends AbstractRobotPart {
    public static final int ALUMINIUM = 0;
    public static final int STEEL = 1;
    public static final int CARBON_FIBER = 2;
    public static final int TITANIUM = 3;
    public static final int PLASTIC = 4;

    // Array mapping material ID to names
    private static final String[] MATERIAL_NAMES = {
        "Aluminium", "Steel", "Carbon Fiber", "Titanium", "Plastic"
    };

    // Array mapping material ID to energy coefficients
    private static final double[] MATERIAL_ENERGY_COEFFS = {
        0.5, 0.7, 0.3, 0.4, 0.8
    };

    private final int material;

    /**
     * A constructor initialising the resistance coefficient for
     * a frame (just by falling back on the super class). 
     * 
     * @param material the material of the frame
     */
    public Frame(int material) {
        super(validateMaterialAndGetCoeff(material));        
        
        this.material = material;
    }

    /**
     * Returns the name of the material of this frame.
     * 
     * @return the name of the material of this frame
     */
    protected String getMaterialName() {
        return MATERIAL_NAMES[material];
    }

    // Static helper method for validation
    private static double validateMaterialAndGetCoeff(int material) {
        if (material < 0 || material >= MATERIAL_ENERGY_COEFFS.length) {
            throw new IllegalArgumentException("Invalid material ID: " + material);
        }
        return MATERIAL_ENERGY_COEFFS[material];
    }
}
