package com.example.mikol.portfolio;
import android.app.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.ipaulpro.afilechooser.utils.FileUtils;

public class ActivityAdd extends Activity {

    private static final int REQUEST_CODE = 6384;

    private String pathToCode;
    TextView newtext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        newtext= (TextView) findViewById(R.id.pathToCode);
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

}