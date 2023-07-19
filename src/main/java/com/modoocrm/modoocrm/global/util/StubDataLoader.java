package com.modoocrm.modoocrm.global.util;

import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.counselor.repository.CounselorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StubDataLoader implements CommandLineRunner {

    private final CounselorRepository counselorRepository;

    public StubDataLoader(CounselorRepository counselorRepository) {
        this.counselorRepository = counselorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Counselor counselor = Counselor.builder()
                .counselorId(1L)
                .counselorName("김선생")
                .counselorGender("남자")
                .build();
        counselorRepository.save(counselor);

        Counselor counselor1 = Counselor.builder()
                .counselorId(2L)
                .counselorName("박선생")
                .counselorGender("여자")
                .build();
        counselorRepository.save(counselor1);
    }
}

