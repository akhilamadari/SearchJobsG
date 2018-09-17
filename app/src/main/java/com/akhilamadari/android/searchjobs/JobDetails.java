package com.akhilamadari.android.searchjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


// Detail screen for job listing clicked
public class JobDetails extends AppCompatActivity {

    private TextView titleTV;
    private TextView locationTV;
    private TextView descriptionTV;
    private TextView companyTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        titleTV = findViewById(R.id.position_title);

        locationTV = findViewById(R.id.position_location);

        descriptionTV = findViewById(R.id.position_description);

        companyTV = findViewById(R.id.position_company);

        Intent intent = getIntent();
        // taking  inputs from joblisting activity and  displaying them in a detailed screen> activity
        String pTitle = intent.getStringExtra("pName");
        String pLocation = intent.getStringExtra("pLocation");
        String pDescription = intent.getStringExtra("pDescription");
        String pCompany = intent.getStringExtra("pCompany");

        // removing html tags > Filtering
        pDescription = pDescription.replaceAll("\\<.*?\\>", "");

        titleTV.setText(pTitle);
        locationTV.setText(pLocation);
        descriptionTV.setText(pDescription);
        companyTV.setText(pCompany);


    }


}
