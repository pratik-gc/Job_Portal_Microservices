package com.myfirstmicroservices.firstjobapp.job.impl;

import com.myfirstmicroservices.firstjobapp.job.Job;
import com.myfirstmicroservices.firstjobapp.job.JobRespository;
import com.myfirstmicroservices.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    
    JobRespository jobRespository;

    public JobServiceImpl(JobRespository jobRespository) {
        this.jobRespository = jobRespository;
    }

    @Override
    public List<Job> findAll() {
        return jobRespository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRespository.save(job);
    }

    @Override
    public Job getJobById(long jobId) {
       return jobRespository.findById(jobId)
               .orElse(null);
    }

    @Override
    public boolean deleteJobById(Long jobId) {
        try {
            jobRespository.deleteById(jobId);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJob(Long jobId, Job updatedJob) {
        Optional<Job> jobOptional = jobRespository.findById(jobId);

            if (jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                jobRespository.save(job);
                return true;
            }
        return false;
    }
}
