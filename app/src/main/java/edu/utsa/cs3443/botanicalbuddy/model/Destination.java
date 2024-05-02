package edu.utsa.cs3443.botanicalbuddy.model;

/**
 * @author Jesus Torres
 * This class represents a Destination object with the destination's id, name, and description.
 */
public class Destination {

    private int id;
    private String name;
    private String description;

    /**
     * Initializes a Destination object.
     * @param id The id of the destination.
     * @param name The name of the destination.
     * @param description The description of the destination.
     */
    public Destination(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Destination(int id, String name) {
        this.id = id;
        this.name = name;
        this.description = "Description not provided.";
    }

    /**
     * Gets the name of the destination
     * @return String, the name of the destination
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the id of the destination
     * @return int, the id of the destination
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the description of the destination
     * @return String, the description of the destination
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * sets the destination name
     * @param name, (String)
     */
    public void setName(String name) {this.name = name;}

    /**
     * sets the destination's id
     * @param id, (int)
     */
    public void setId(int id) {this.id = id;}

    /**
     * sets the destination's description
     * @param description, (String)
     */
    public void setDescription(String description) {this.description = description;}



}