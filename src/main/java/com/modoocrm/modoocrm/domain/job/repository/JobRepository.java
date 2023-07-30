package com.modoocrm.modoocrm.domain.job.repository;

import com.modoocrm.modoocrm.domain.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
