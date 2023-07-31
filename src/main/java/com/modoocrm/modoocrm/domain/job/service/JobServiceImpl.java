package com.modoocrm.modoocrm.domain.job.service;

import com.modoocrm.modoocrm.domain.job.entity.Job;
import com.modoocrm.modoocrm.domain.job.repository.JobRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job findJob(String job){
        return jobRepository.findByJobGroup(job)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.JOB_NOT_FOUND));
    }
}
