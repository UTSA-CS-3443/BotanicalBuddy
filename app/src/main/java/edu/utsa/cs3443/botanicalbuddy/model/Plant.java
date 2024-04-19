package edu.utsa.cs3443.botanicalbuddy.model;

public class Plant {



    public enum Category {
        SHADE_PLANTS,
        COOL_TONES,
        SHALLOW_SOILS,
    }//TODO: implement

    public String getCommonName() {
        return commonName;
    }

    public String getLatinName() {
        return latinName;
    }

    public String getPhotoName() {
        return photoName.trim();
    }

    private final String commonName;
    private final String latinName;
    private final String photoName;


    public Plant(String commonName, String latinName, String photoName) {
        this.commonName = commonName;
        this.latinName = latinName;
        this.photoName = photoName.trim();
    }

    public Plant(String commonName, String latinName) {
        this.commonName = commonName;
        this.latinName = latinName;
        this.photoName = "main_logo_flower_only";
    }



    @Override
    public String toString() {
        return String.format("Plant[commonName=%s, latinName=%s, imageName=%s]", commonName, latinName, photoName);

    }
}
