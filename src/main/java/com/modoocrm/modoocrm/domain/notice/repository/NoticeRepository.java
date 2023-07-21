package com.modoocrm.modoocrm.domain.notice.repository;

import com.modoocrm.modoocrm.domain.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
