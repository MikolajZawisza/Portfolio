package com.example.mikol.portfolio;

public class Project {
    private int id;
    private String name;
    private String description;
    private String pathToCode;
    private String pathToPhoto;

    public Project(int id, String name, String description,String pathToCode,String pathToPhoto) {
        this.id = id;
        this.name = name;
        this.description=description;
        this.pathToCode=pathToCode;
        this.pathToPhoto=pathToPhoto;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPathToCode(){
        return pathToCode;
    }

    public String getPathToPhoto(){
        return pathToPhoto;
    }

    @Override
    public String toString() {
        return name + " " ;
    }


}
