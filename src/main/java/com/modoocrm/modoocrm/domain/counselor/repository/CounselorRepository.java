package com.modoocrm.modoocrm.domain.counselor.repository;

import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CounselorRepository extends JpaRepository <Counselor, Long> {
    Optional<Counselor> findByCounselorName(String counselorName);
}
