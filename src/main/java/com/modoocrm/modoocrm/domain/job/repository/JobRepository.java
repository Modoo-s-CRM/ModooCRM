package com.modoocrm.modoocrm.domain.job.repository;

import com.modoocrm.modoocrm.domain.job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Long> {

    Optional<Job> findByJobGroup(String job);
}
