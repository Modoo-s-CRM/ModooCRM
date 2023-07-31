package com.modoocrm.modoocrm.global.util;

import com.modoocrm.modoocrm.domain.counselor.entity.Counselor;
import com.modoocrm.modoocrm.domain.counselor.repository.CounselorRepository;
import com.modoocrm.modoocrm.domain.job.entity.Job;
import com.modoocrm.modoocrm.domain.job.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StubDataLoader implements CommandLineRunner {

    private final CounselorRepository counselorRepository;
    private final JobRepository jobRepository;

    public StubDataLoader(CounselorRepository counselorRepository, JobRepository jobRepository) {
        this.counselorRepository = counselorRepository;
        this.jobRepository = jobRepository;
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


        Job job1 = Job.builder()
                .jobGroup("전문직")
                .build();
        jobRepository.save(job1);

        Job job2 = Job.builder()
                .jobGroup("기술직")
                .build();
        jobRepository.save(job2);

        Job job3 = Job.builder()
                .jobGroup("특수직")
                .build();
        jobRepository.save(job3);

        Job job4 = Job.builder()
                .jobGroup("서비스직")
                .build();
        jobRepository.save(job4);

        Job job5 = Job.builder()
                .jobGroup("영업직")
                .build();
        jobRepository.save(job5);

        Job job6 = Job.builder()
                .jobGroup("사무직")
                .build();
        jobRepository.save(job6);

        Job job7 = Job.builder()
                .jobGroup("공무원")
                .build();
        jobRepository.save(job7);

        Job job8 = Job.builder()
                .jobGroup("교직원")
                .build();
        jobRepository.save(job8);

        Job job9 = Job.builder()
                .jobGroup("취업준비생")
                .build();
        jobRepository.save(job9);

        Job job10 = Job.builder()
                .jobGroup("알바")
                .build();
        jobRepository.save(job10);

        Job job11 = Job.builder()
                .jobGroup("대학생")
                .build();
        jobRepository.save(job11);

        Job job12 = Job.builder()
                .jobGroup("고등학생")
                .build();
        jobRepository.save(job1);

        Job job13 = Job.builder()
                .jobGroup("중학생")
                .build();
        jobRepository.save(job13);

        Job job14 = Job.builder()
                .jobGroup("초등학생")
                .build();
        jobRepository.save(job14);

        Job job15 = Job.builder()
                .jobGroup("주부")
                .build();
        jobRepository.save(job15);

        Job job16 = Job.builder()
                .jobGroup("군인")
                .build();
        jobRepository.save(job16);

        Job job17 = Job.builder()
                .jobGroup("무직")
                .build();
        jobRepository.save(job17);
    }
}

