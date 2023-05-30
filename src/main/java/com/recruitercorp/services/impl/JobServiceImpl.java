package com.recruitercorp.services.impl;

import com.recruitercorp.dto.JobDto;
import com.recruitercorp.dto.ResponseData;
import com.recruitercorp.entity.Job;
import com.recruitercorp.repository.JobRepository;
import com.recruitercorp.services.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    @Transactional
    public ResponseData<List<JobDto>> getPopularJob() {
        Page<Job> jobList = jobRepository.findAll(Pageable.ofSize(6));
        return ResponseData.responseOK(jobList.get().map(item -> JobDto.builder()
                .id(item.getId())
                .salaryTo(item.getSalaryTo().toString())
                .salaryFrom(item.getSalaryFrom().toString())
                .title(item.getTitle())
                .tag(item.getTag())
                .description(item.getDescription())
                .reward(item.getReward().toString())
                .build()).collect(Collectors.toList()));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseData<String> test() {
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Job job = Job.builder()
                    .title("Tuyển dụng Java developer " + i)
                    .description("Mô tả " + i)
                    .salaryFrom(BigDecimal.valueOf(100000))
                    .salaryTo(BigDecimal.valueOf(200000))
                    .tag("URGENT")
                    .reward(BigDecimal.valueOf(16000000))
                    .build();
            jobList.add(job);
        }
        jobRepository.saveAll(jobList);
        return ResponseData.responseOK("OK");
    }
}
