package com.example.mikol.portfolio;

public class Project {
    private int id;
    private String name;
    private String description;
    private String pathToCode;

    public Project(int id, String name, String description,String pathToCode) {
        this.id = id;
        this.name = name;
        this.description=description;
        this.pathToCode=pathToCode;

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


    @Override
    public String toString() {
        return name + " " ;
    }


}
