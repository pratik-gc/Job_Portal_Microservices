package com.myfirstmicroservices.firstjobapp.job.impl;

import com.myfirstmicroservices.firstjobapp.job.Job;
import com.myfirstmicroservices.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(long jobId) {
        for(Job job : jobs){
            if (job.getId()==jobId) //OR    if(job.getId().equals(jobId))
                return job;
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long jobId) {
        Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext()){
            Job job = iterator.next();
            if (job.getId().equals(jobId)){
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updateJob(Long jobId, Job updatedJob) {
        for (Job job : jobs){
            if (job.getId().equals(jobId)){
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        }
        return false;
    }
}
