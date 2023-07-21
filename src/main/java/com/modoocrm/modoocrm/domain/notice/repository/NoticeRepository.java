package com.modoocrm.modoocrm.domain.notice.repository;

import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByNoticeDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
