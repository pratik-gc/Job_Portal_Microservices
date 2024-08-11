package com.myfirstmicroservices.firstjobapp.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    //@RequestMapping(value = "/jobs/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId){
        Job job = jobService.getJobById(jobId);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId){
        Boolean deleted = jobService.deleteJobById(jobId);
        if (deleted)
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Job could not be found!!!", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long jobId, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(jobId, updatedJob);
        if (updated)
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Job could not be updated!!!", HttpStatus.NOT_FOUND);
    }
}
