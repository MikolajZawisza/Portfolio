package com.example.mikol.portfolio;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.mikol.portfolio.ProjectTable.*;

public class ProjectDao implements Dao<Project> {
    private SQLiteDatabase database;
    private static final String INSERT = "INSERT INTO " + ProjectTable.TABLE_NAME +
            " (" + PROJECT_NAME + ", " + PROJECT_DESCRIPTION + ", " + PROJECT_PATHTOCODE + ") VALUES ('%s', '%s', '%s')";

    public ProjectDao(SQLiteDatabase database) {
        this.database = database;
    }

    @Override
    public void save(Project item) {
        database.execSQL(String.format(INSERT, item.getName(), item.getDescription(), item.getPathToCode()));
    }

    @Override
    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        Cursor cursor = database.query(
                TABLE_NAME,
                new String[]{ID, PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_PATHTOCODE},
                null,
                null,
                null,
                null,
                null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                projects.add(new Project(
                        cursor.getInt(ID_COLUMN),
                        cursor.getString(NAME_COLUMN),
                        cursor.getString(DESCRIPTION_COLUMN),
                        cursor.getString(PATHTOCODE_COLUMN))
                );
            } while (cursor.moveToNext());
        }
        return projects;
    }

    @Override
    public Project getProject(String name) {
            Project project = null;
            Cursor c =
                    database.query(
                            TABLE_NAME,
                            new String[]{ID, PROJECT_NAME, PROJECT_DESCRIPTION, PROJECT_PATHTOCODE},
                            PROJECT_NAME + " = ?", new String[] { name },
                            null,
                            null,
                            null,
                            "1");
            if (c.moveToFirst()) {
                project=new Project(
                        c.getInt(ID_COLUMN),
                        c.getString(NAME_COLUMN),
                        c.getString(DESCRIPTION_COLUMN),
                        c.getString(PATHTOCODE_COLUMN));
            }
            if (!c.isClosed()) {
                c.close();
            }
            return project;

        }
}

