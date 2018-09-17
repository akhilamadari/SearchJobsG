package com.akhilamadari.android.searchjobs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akhilamadari.android.searchjobs.Models.Job;

import java.util.List;

public class JobListingsAdapter extends RecyclerView.Adapter <JobListingsAdapter.JobViewHolder> {

    private Context context;
    private List<Job> jobsList;

    final private JobItemClickListener mJobItemClickListener;


    public interface JobItemClickListener{
        void onJobItemClicked(int clickedJobIndex);
    }


    public JobListingsAdapter(Context context , List<Job> jobsList, JobItemClickListener listener) {
        this.context = context;
        this.jobsList = jobsList;
        this.mJobItemClickListener = listener;
    }


    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_job_item, parent,false);

        return  new JobViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {

        Job job = jobsList.get(position);
        holder.title.setText(job.getJobTitle());
        holder.location.setText(job.getJobLocation());
        holder.company.setText(job.getCompany());

    }


    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView company;
        private TextView location;

        public JobViewHolder(View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.job_title);
            company = itemView.findViewById(R.id.job_company);
            location = itemView.findViewById(R.id.job_location);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mJobItemClickListener.onJobItemClicked(clickedPosition);

        }
    }

}
