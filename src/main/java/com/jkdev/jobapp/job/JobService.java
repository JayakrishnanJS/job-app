package com.jkdev.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void addJob(Job job);

    Job getJobById(Long id);
    boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
