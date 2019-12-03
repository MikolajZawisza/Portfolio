package com.example.mikol.portfolio;
import android.app.Activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ipaulpro.afilechooser.utils.FileUtils;


import static com.example.mikol.portfolio.ProjectTable.DB_NAME;
import static com.example.mikol.portfolio.ProjectTable.DB_VERSION;

public class ActivityAdd extends Activity {
    private static final int REQUEST_CODE = 6384;
    private String pathToCode="";
    TextView newtext;
    EditText editNameProject;
    EditText editDescriptionProjects;
    String name;
    String description;
    private SharedPreferences pref;

    private BoundService myService;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        startMyService();

        newtext= (TextView) findViewById(R.id.pathToCode);
        editNameProject= (EditText) findViewById(R.id.editNameProject) ;
        editDescriptionProjects= (EditText) findViewById(R.id.editDescriptionProjects);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        editDescriptionProjects.setText(pref.getString("input1", ""));
        editNameProject.setText(pref.getString("input", ""));

    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("name", editNameProject.getText().toString());
        editor.putString("description",editDescriptionProjects.getText().toString());
        editor.apply();
    }

    public void showChooser(View view) {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser( target, getString(R.string.chooser_title));
        try {

            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {// If the file selection was successful
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    // Get the URI of the selected file
                    final Uri uri = data.getData();
                    Log.i("Activity", "Uri = " + uri.toString());
                    try {
                        // Get the file path from the URI
                        final String path = FileUtils.getPath(this, uri);
                        Toast.makeText(ActivityAdd.this,
                                "File Selected: " + path, Toast.LENGTH_LONG).show();
                        pathToCode=path;
                        newtext.setText(pathToCode);
                        System.out.print(path);
                    } catch (Exception e) {
                        Log.e("Activity", "File select error", e);
                    }
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addProject(View view)
    {
        name =editNameProject.getText().toString();
        description=editDescriptionProjects.getText().toString();

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editNameProject = findViewById(R.id.editNameProject);
        editDescriptionProjects = findViewById(R.id.editDescriptionProjects);

        if((!name.matches(""))&&(!description.matches(""))&&(!pathToCode.matches("")))
        {
            OpenHelper openHelper = new OpenHelper(getApplicationContext(), DB_NAME, null, DB_VERSION);
            ProjectDao projectDao = new ProjectDao(openHelper.getWritableDatabase());
            Project project=new Project(0,name,description,pathToCode);
            projectDao.save(project);
            editor.clear();
            editor.apply();
            stopMyService();
            finish();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Check if all fields are filled",Toast.LENGTH_SHORT);
            toast.show();

            editor.putString("input", editNameProject.getText().toString());
            editor.putString("input1", editDescriptionProjects.getText().toString());
            editor.apply();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder binder = (BoundService.MyBinder) service;
            myService = binder.getMyService();
        }
    };


    private void startMyService() {
        Intent serviceIntent = new Intent(ActivityAdd.this, BoundService.class);
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void stopMyService() {
        Intent serviceIntent = new Intent(this, BoundService.class);
        unbindService(serviceConnection);
        stopService(serviceIntent);
    }

}