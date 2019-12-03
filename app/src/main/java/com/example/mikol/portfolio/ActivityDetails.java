package com.example.mikol.portfolio;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mikol.portfolio.ProjectTable.DB_NAME;
import static com.example.mikol.portfolio.ProjectTable.DB_VERSION;

public class ActivityDetails extends AppCompatActivity implements View.OnClickListener {

    String name;
    FragmentManager fragmentManager;
    String des="";
    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        TextView newtext = (TextView) findViewById(R.id.textView2);

        name=intent.getStringExtra("name");
        newtext.setText(name);


        Button button1=findViewById(R.id.description_bn);
        button1.setOnClickListener(this);

        Button button2=findViewById(R.id.code_bn);
        button2.setOnClickListener(this);

        wez(name);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment =null;
        Bundle bundle = new Bundle();

        switch (view.getId()){
            case R.id.description_bn:
                bundle.putString("name",des);
                fragment=new DescriptionFragment();
                fragmentManager=getSupportFragmentManager();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
                break;
            case R.id.code_bn:
                bundle.putString("path",path);
                fragment=new FileFragment();
                fragmentManager=getSupportFragmentManager();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
                break;
        }
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame1,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void wez(String name){
        Project project=null;
        OpenHelper openHelper = new OpenHelper(getApplicationContext(), DB_NAME, null, DB_VERSION);
        ProjectDao projectDao = new ProjectDao(openHelper.getReadableDatabase());
        project=projectDao.getProject(name);
        des=project.getDescription();
        path=project.getPathToCode();
    }
}


