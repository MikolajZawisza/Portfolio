package com.example.mikol.portfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Arrays;
import java.util.List;


import static com.example.mikol.portfolio.ProjectTable.DB_NAME;
import static com.example.mikol.portfolio.ProjectTable.DB_VERSION;



public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Project> adapter;
    Dao<Project> projectDao;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenHelper openHelper = new OpenHelper(getApplicationContext(), DB_NAME, null, DB_VERSION);
        projectDao = new ProjectDao(openHelper.getWritableDatabase());
        listView= findViewById(R.id.projectList);

        List<Project> studentsToShow = projectDao.getAll();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentsToShow);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Project project = (Project) adapterView.getItemAtPosition(i);

                String msg=project.getName();
                goToDetailsActivity(view,msg);
            }
        });


    }

    public void goToDetailsActivity(View view,String msg) {
        Intent intent = new Intent(this, ActivityDetails.class);
        intent.putExtra("name",msg);
        startActivity(intent);
    }

    public void goToAddActivity(View view)
    {
        Intent intent = new Intent(this, ActivityAdd.class);
        startActivity(intent);
    }

}
