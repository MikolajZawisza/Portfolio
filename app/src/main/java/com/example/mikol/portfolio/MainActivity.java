package com.example.mikol.portfolio;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.List;


import static com.example.mikol.portfolio.ProjectTable.DB_NAME;
import static com.example.mikol.portfolio.ProjectTable.DB_VERSION;



public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Project> adapter;
    Dao<Project> projectDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenHelper openHelper = new OpenHelper(getApplicationContext(), DB_NAME, null, DB_VERSION);
        projectDao = new ProjectDao(openHelper.getWritableDatabase());
        ListView listView = findViewById(R.id.projectList);
        List<Project> projects = Arrays.asList
                (
            //    new Project(0, "Portfolio", "Ala ma kota kot ma ale", "patt yo code","path to photo"),
             //   new Project(0,"Kotek LUAN", "Luan ma Ale,Ala ma Luan","patt yo code","path to photo")
                 );
        for(int i = 0; i < projects.size(); i++){
            projectDao.save(projects.get(i));
        }

        List<Project> studentsToShow = projectDao.getAll();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentsToShow);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Project project = (Project) adapterView.getItemAtPosition(i);

                String msg=project.getName();
                goToDetailsActivity(view,msg);

                /*Toast toast = Toast.makeText(getApplicationContext(),
                        project.getName(),
                        Toast.LENGTH_SHORT);
                toast.show();*/
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
