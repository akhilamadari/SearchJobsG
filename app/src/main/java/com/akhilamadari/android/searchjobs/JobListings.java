package com.akhilamadari.android.searchjobs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akhilamadari.android.searchjobs.Models.Job;
import com.akhilamadari.android.searchjobs.Retrofit.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.akhilamadari.android.searchjobs.R.string.No_item;
import static com.akhilamadari.android.searchjobs.R.string.No_network;

// displays all the job listing
public class JobListings extends AppCompatActivity implements JobListingsAdapter.JobItemClickListener {

    private RecyclerView mRecyclerView;

    private JobListingsAdapter mAdapter;

    private TextView error_mes;

    private String jobDescription;

    private String jobLocation;

    private boolean isFulltime;

    private static final String POSITION_NAME = "pName";
    private static final String POSITION_LOCATION = "pLocation";
    private static final String POSITION_DESCRIPTION = "pDescription";
    private static final String POSITION_COMPANY = "pCompany";

    private static final String TAG = JobListings.class.getSimpleName();

    List<Job> jobList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listings);


        // extracting editor inputs from main activity
        Intent i = getIntent();
        error_mes = (TextView) findViewById(R.id.error_mes);

        jobDescription = i.getStringExtra("mDescription");

        jobLocation = i.getStringExtra("mLocation");

        isFulltime =  i.getExtras().getBoolean("fullTime");

        mRecyclerView = (RecyclerView) findViewById(R.id.jobs_list_recycler_view);


        // Listing all the jobs in recyler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        ApiUtils.getServiceClass().getJobListings(jobDescription,jobLocation,isFulltime).enqueue(new Callback<List<Job>>() {

            ProgressBar progressBar =  (ProgressBar) findViewById(R.id.loader);



            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {


                progressBar.setVisibility(View.INVISIBLE);


                if (response.isSuccessful()) {
                    jobList = response.body();
                    Log.d(TAG, "Returned count " + jobList.size());
                    if(jobList.size() == 0){
                        error_mes.setText(No_item);
                        error_mes.setVisibility(View.VISIBLE);
                    }else {
                        mAdapter = new JobListingsAdapter(getApplicationContext(), jobList, JobListings.this);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                }
            }

            // on error
            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                //showErrorMessage();
                progressBar.setVisibility(View.INVISIBLE);

                error_mes.setText(R.string.No_network);
                error_mes.setVisibility(View.VISIBLE);

                Log.d(TAG, "error loading from API");
            }
        });
    }

    // when an item is clicked from the list > takes to detail activity
    @Override
    public void onJobItemClicked(int clickedJobIndex) {
         Job job = jobList.get(clickedJobIndex);
         Intent i = new Intent(this, JobDetails.class);
         i.putExtra(POSITION_NAME,job.getJobTitle());
         i.putExtra(POSITION_LOCATION,job.getJobLocation());
         i.putExtra(POSITION_COMPANY,job.getCompany());
         i.putExtra(POSITION_DESCRIPTION,job.getJobDescription());
         startActivity(i);
    }
}
