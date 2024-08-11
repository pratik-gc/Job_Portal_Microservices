package com.myfirstmicroservices.firstjobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(long jobId);

    boolean deleteJobById(Long jobId);

    boolean updateJob(Long jobId, Job updatedJob);
}
