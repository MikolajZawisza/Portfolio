package com.example.mikol.portfolio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;


import static com.example.mikol.portfolio.ProjectTable.DB_NAME;
import static com.example.mikol.portfolio.ProjectTable.DB_VERSION;



public class MainActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    Dao<Project> projectDao;
    ListView listView;
    OpenHelper openHelper;
    List<Project> studentsToShow;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new OpenHelper(getApplicationContext(), DB_NAME, null, DB_VERSION);
        projectDao = new ProjectDao(openHelper.getWritableDatabase());
        listView= findViewById(R.id.projectList);

        //studentsToShow.clear();
        studentsToShow=projectDao.getAll();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, studentsToShow);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Project project = (Project) adapterView.getItemAtPosition(i);
                String msg=project.getName();
                int id=project.getId();
                goToDetailsActivity(view,msg,id);
            }
        });

    }

    public void goToDetailsActivity(View view,String msg,int id) {
        Intent intent = new Intent(this, ActivityDetails.class);
        intent.putExtra("name",msg);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void goToAddActivity(View view)
    {
        Intent intent = new Intent(this, ActivityAdd.class);
        startActivity(intent);
    }



}
