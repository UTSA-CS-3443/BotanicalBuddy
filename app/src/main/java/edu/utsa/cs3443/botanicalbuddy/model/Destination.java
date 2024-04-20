package edu.utsa.cs3443.botanicalbuddy.model;

public class Destination {

    private int id;
    private String name;
    private String description;

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
    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }
    public String getDescription() {
        return this.description;
    }
    public void setName(String name) {this.name = name;}
    public void setId(int id) {this.id = id;}
    public void setDescription(String description) {this.description = description;}



}