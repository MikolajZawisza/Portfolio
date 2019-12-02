package com.example.mikol.portfolio;

import java.util.List;

public interface Dao<T> {
    void save(T item);
    List<T> getAll();
    Project getProject(String name);
}
