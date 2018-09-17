package com.akhilamadari.android.searchjobs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;

    private EditText description;

    private EditText location;

    private boolean fTimeCheck = false;

    public static final String DESCRIPTION = "mDescription";
    public static final String LOCATION = "mLocation";
    public static final String FULLTIME = "fullTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.search_jobs);

        description = findViewById(R.id.job_description_entry);

        location = findViewById(R.id.job_location_entry);


        mButton.setOnClickListener(new View.OnClickListener(
        ) {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString(DESCRIPTION, description.getText().toString());
                editor.putString(LOCATION, location.getText().toString());
                editor.putBoolean(FULLTIME,fTimeCheck);


                Intent i = new Intent(MainActivity.this, JobListings.class);
                startActivity(i);
            }
        });

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_full_time:
                if (checked) {
                    fTimeCheck = true;
                }
        }

    }

}