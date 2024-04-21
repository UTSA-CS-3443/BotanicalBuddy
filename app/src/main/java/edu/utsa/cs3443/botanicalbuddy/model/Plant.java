package edu.utsa.cs3443.botanicalbuddy.model;

/**
 * This class represents a plant object with information about its common name, latin name, photo filename, and a brief description.
 *
 * @author Paul Dutton hyf750
 */
public class Plant {



    public enum Category {
        SHADE_PLANTS,
        COOL_TONES,
        SHALLOW_SOILS,
    }//TODO: implement



    private final String commonName;
    private final String latinName;
    private final String photoName;

    /**
     * Creates a new Plant object with the specified common name, latin name, photo filename, and description.
     *
     * @param commonName The common name of the plant.
     * @param latinName The scientific (Latin) name of the plant.
     * @param photoName The filename of the plant's image (optional validation might be applied).
     */

    public Plant(String commonName, String latinName, String photoName) {
        this.commonName = commonName;
        this.latinName = latinName;
        this.photoName = photoName.trim();
    }

    /**
     * Creates a new Plant object with the specified common name and latin name.
     * The photo filename is set to "main_logo_flower_only" and the description is set to an empty string.
     *
     * @param commonName The common name of the plant.
     * @param latinName The scientific (Latin) name of the plant.
     */
    public Plant(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
        this.photoName = "main_logo_flower_only";
    }

    /**
     * Gets the common name of the plant.
     *
     * @return The common name of the plant.
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * Gets the Latin name of the plant.
     *
     * @return The scientific (Latin) name of the plant.
     */
    public String getLatinName() {
        return latinName;
    }

    /**
     * Gets the filename of the plant's image.
     *
     * @return The filename of the plant's image (after potential validation).
     */
    public String getPhotoName() {
        return photoName.trim();
    }

    @Override
    public String toString() {
        return String.format("Plant[commonName=%s, latinName=%s, imageName=%s]", commonName, latinName, photoName);

    }
}
